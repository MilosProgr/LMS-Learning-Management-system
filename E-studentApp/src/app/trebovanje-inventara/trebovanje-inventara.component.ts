import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { NgxPaginationModule } from 'ngx-pagination';

import { NastavniMaterijal } from '../../models/nastavni-materijal.model';
import { NastavniMaterijalService } from '../../Services/nastavni-materijal.service';
import { LoginService } from '../../Services/login.service';
import { ProfileService } from '../../Services/profile-service.service';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-trebovanje-inventara',
  standalone: true,
  templateUrl: './trebovanje-inventara.component.html',
  styleUrls: ['./trebovanje-inventara.component.css'],
  imports: [CommonModule, FormsModule, TranslateModule, NgxPaginationModule, MatExpansionModule],
})
export class TrebovanjeInventaraComponent implements OnInit {
  materijali: NastavniMaterijal[] = [];
  filteredRequests: NastavniMaterijal[] = [];
  newRequest: Partial<NastavniMaterijal> = { naziv: '', kolicina: 0 };

  userRoles: string[] = [];
  userId: number = 0;

  searchText: string = '';
  statusFilter: string = 'all';
  isLoading: boolean = false;
  error: string | null = null;

  itemsPerPage: number = 10;
  currentPage: number = 1;

  constructor(
    private materijalService: NastavniMaterijalService,
    private loginService: LoginService,
    private profileService: ProfileService
  ) {}

  ngOnInit(): void {
    this.loadUserData();
    this.loadRequests();
  }

  private loadUserData(): void {
    const user = this.loginService.user;
    if (user && Array.isArray(user.roles)) {
      this.userRoles = user.roles;
      this.userId = user.id;
    }
  }

  private loadRequests(): void {
    this.isLoading = true;
    this.error = null;

    this.materijalService.getAll().subscribe({
      next: (data) => {
        this.materijali = data;

        this.filterRequests();
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Greška prilikom učitavanja zahteva', err);
        this.error = 'Došlo je do greške prilikom učitavanja zahteva.';
        this.isLoading = false;
      },
    });
  }


  createRequest(): void {
    if (!this.newRequest.naziv || !this.newRequest.kolicina) return;

    const request: NastavniMaterijal = {
      id: 0,
      naziv: this.newRequest.naziv!,
      kolicina: this.newRequest.kolicina!,
      odobreno: null,
      podnosilac_zahteva_id: this.userId,
      autorizator_id: null,
    };

    this.materijalService.create(request).subscribe({
      next: () => {
        this.newRequest = { naziv: '', kolicina: 0 };
        this.loadRequests();
      },
      error: (err) => console.error('Greška prilikom kreiranja zahteva', err),
    });
  }

  approveRequest(materijal: NastavniMaterijal, approved: boolean): void {
    if (!this.isRole('ROLE_ADMIN')) return;

    const updated: NastavniMaterijal = {
      ...materijal,
      odobreno: approved,
      autorizator_id: this.userId,
    };

    this.materijalService.update(materijal.id, updated).subscribe({
      next: () => this.loadRequests(),
      error: (err) => console.error('Greška prilikom odobravanja zahteva', err),
    });
  }

  filterRequests(): void {
    this.filteredRequests = this.materijali.filter((req) => {
      const matchesSearch =
        !this.searchText ||
        req.naziv.toLowerCase().includes(this.searchText.toLowerCase());

      let matchesStatus = true;
      if (this.statusFilter === 'pending') {
        matchesStatus = req.odobreno === null;
      } else if (this.statusFilter === 'approved') {
        matchesStatus = req.odobreno === true;
      } else if (this.statusFilter === 'rejected') {
        matchesStatus = req.odobreno === false;
      }

      return matchesSearch && matchesStatus;
    });
  }

  refreshData(): void {
    this.loadRequests();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
  }

  isRole(role: string): boolean {
    return this.userRoles.includes(role);
  }

  get hasProcessedRequests(): boolean {
    return this.materijali?.some(m => m.odobreno !== null) ?? false;
  }

  get hasNoProcessedRequests(): boolean {
    return !this.hasProcessedRequests;
  }

  showForm: boolean = false;
}
