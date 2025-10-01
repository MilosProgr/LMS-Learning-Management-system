import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatExpansionModule } from '@angular/material/expansion';

import { IzdavanjeUdzbenikService, IzdavanjeUdzbenika } from '../../Services/izdavanje-udzbenika-service.service';
import { PredmetService } from '../../Services/predmet.service';
import { UdzbenikService } from '../../Services/udzbenik.service';
import { Predmet } from '../../models/predmetModel';
import { LoginService } from '../../Services/login.service';
import { forkJoin } from 'rxjs';

interface ProgramGroup {
  id: number;
  naziv: string;
  predmeti: PredmetGroup[];
  selected: boolean;
  expanded?: boolean;
}

interface PredmetGroup {
  id: number;
  naziv: string;
  udzbenici: { id: number; naziv: string }[];
}

@Component({
  selector: 'app-izdavanje-udzbenika',
  standalone: true,
  templateUrl: './izdavanje-udzbenika.component.html',
  styleUrls: ['./izdavanje-udzbenika.component.css'],
  imports: [CommonModule, FormsModule, NgxPaginationModule, MatExpansionModule]
})
export class IzdavanjeUdzbenikaComponent implements OnInit {
  studijskiProgrami: ProgramGroup[] = [];
  userId: number = 0;

  izdavanja: IzdavanjeUdzbenika[] = [];
  filteredIzdavanja: IzdavanjeUdzbenika[] = [];
  itemsPerPage = 10;
  currentPage = 1;
  isLoading = false;
  error: string | null = null;
  searchText: string = '';
  statusFilter: string = 'all';

  constructor(
    private predmetService: PredmetService,
    private udzbenikService: UdzbenikService,
    private izdavanjeService: IzdavanjeUdzbenikService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.loadUser();
    this.loadIzdavanja();
  }

  private loadUser() {
    const user = this.loginService.user;
    if (user) {
      this.userId = user.id;
      this.loadPredmeti();
    } else {
      console.error('No logged-in student found in loginService');
    }
  }

  private programMapById: Map<number, string> = new Map();

  private loadPredmeti() {
    this.predmetService.getAll().subscribe(predmeti => {
      const programMap = new Map<number, ProgramGroup>();

      predmeti.forEach(p => {
        p.studijskiProgrami?.forEach(sp => {
          if (!sp.id) return;
          if (!programMap.has(sp.id)) {
            programMap.set(sp.id, {
              id: sp.id,
              naziv: sp.naziv || 'N/A',
              predmeti: [],
              selected: false,
              expanded: false
            });
          }

          const program = programMap.get(sp.id)!;
          program.predmeti.push({
            id: p.id!,
            naziv: p.naziv || '',
            udzbenici: []
          });

          // store mapping from program id to name
          this.programMapById.set(sp.id, sp.naziv || 'N/A');
        });
      });

      this.studijskiProgrami = Array.from(programMap.values());

      // Assign udzbenici to each predmet
      this.udzbenikService.getAll().subscribe(udzbenici => {
        const udzbenikMap = new Map<number, { id: number; naziv: string }[]>();
        udzbenici.forEach(u => {
          if (!u.predmet?.id) return;
          if (!udzbenikMap.has(u.predmet.id)) udzbenikMap.set(u.predmet.id, []);
          udzbenikMap.get(u.predmet.id)?.push({ id: u.id ?? 0, naziv: u.naziv ?? '' });
        });

        this.studijskiProgrami.forEach(program => {
          program.predmeti.forEach(predmetGroup => {
            predmetGroup.udzbenici = udzbenikMap.get(predmetGroup.id) ?? [];
          });
        });
      });
    }, err => {
      this.error = 'Greška pri učitavanju predmeta.';
      console.error(err);
    });
  }

  submitRequest() {
    // collect all selected programs
    const requests: Partial<IzdavanjeUdzbenika>[] = this.studijskiProgrami
      .filter(program => program.selected)
      .map(program => ({
        podnosilac_zahteva_id: this.userId,
        odobreno: null
      }));

    if (requests.length === 0) return;

    // map each request to the create observable
    const observables = requests.map(req => this.izdavanjeService.create(req as IzdavanjeUdzbenika));

    // forkJoin waits until all create requests finish
    forkJoin(observables).subscribe({
      next: () => {
        console.log('Svi zahtevi poslati');
        // clear selections
        this.studijskiProgrami.forEach(p => p.selected = false);
        // reload requests table
        this.loadIzdavanja();
      },
      error: err => {
        console.error('Greška pri slanju zahteva:', err);
      }
    });
  }


  private loadIzdavanja() {
    this.isLoading = true;
    this.izdavanjeService.getAll().subscribe({
      next: data => {
      this.izdavanja = data.map(d => ({
        ...d,
        odobreno: d.odobreno === null ? null : Boolean(d.odobreno) 
      }));

        this.filteredIzdavanja = [...this.izdavanja];
        this.isLoading = false;
      },
      error: err => {
        this.error = 'Greška pri učitavanju zahteva.';
        this.isLoading = false;
        console.error(err);
      }
    });
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }

  isRole(role: string): boolean {
    return this.loginService.validateRoles([role]);
  }

  // Simplified: use the program name stored in the request
  getProgramName(request: IzdavanjeUdzbenika): string {
    // find programs where this student has requested
    const programs = this.studijskiProgrami
      .filter(p => request.podnosilac_zahteva_id === this.userId && p.selected);
    
    // combine names if multiple
    return programs.map(p => p.naziv).join(', ') || 'N/A';
  }


  approveAdmin(request: IzdavanjeUdzbenika, approve: boolean): void {
    if (!this.isRole('ROLE_ADMIN') && !this.isRole('ROLE_SLUZBA')) return;

    const updatedRequest: Partial<IzdavanjeUdzbenika> = {
      ...request,
      odobreno: approve
    };

    this.izdavanjeService.update(request.id, updatedRequest as IzdavanjeUdzbenika).subscribe({
      next: () => this.loadIzdavanja(),
      error: err => console.error('Greška pri odobravanju/odbijanju zahteva', err)
    });
  }

getStatusText(request: IzdavanjeUdzbenika): string {
  if (request.odobreno === true) return 'Odobreno';
  if (request.odobreno === false) return 'Odbijeno';
  return 'Na čekanju';
}


  getStatusClass(request: IzdavanjeUdzbenika): string {
    if (request.odobreno === true) return 'status-approved';
    if (request.odobreno === false) return 'status-rejected';
    return 'status-pending';
  }

  hasSelectedPrograms(): boolean {
    return this.studijskiProgrami.some(program => program.selected);
  }

    filterIzdavanja() {
    const term = this.searchText.toLowerCase().trim();

    this.filteredIzdavanja = this.izdavanja.filter(r => {
      let matchesSearch = true;
      let matchesStatus = true;

      // filter by status
      if (this.statusFilter === 'pending') matchesStatus = r.odobreno === null;
      else if (this.statusFilter === 'approved') matchesStatus = r.odobreno === true;
      else if (this.statusFilter === 'rejected') matchesStatus = r.odobreno === false;

      // FILTER PO IMENU I PREZIMENU STUDENTA POPRAVI!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // if (term) {
      //   const student = r.podnosilac_zahteva_id;
      //   if (student) {
      //     const fullName = `${student.ime ?? ''} ${student.prezime ?? ''}`.toLowerCase();
      //     matchesSearch = fullName.includes(term);
      //   } else {
      //     matchesSearch = false;
      //   }
      // }

      return matchesSearch && matchesStatus;
    });
  }
}
