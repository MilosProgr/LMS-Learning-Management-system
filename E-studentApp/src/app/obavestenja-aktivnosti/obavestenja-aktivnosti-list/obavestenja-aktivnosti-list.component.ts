import { Component, OnInit } from '@angular/core';
import { ObavestenjeAktivnosti } from '../../../models/obavestenja/obavestenjaAktivnostiModel';
import { MatDialog } from '@angular/material/dialog';
import { ObavestenjaAktivnostiService } from '../../../Services/obavestenja/obavestenjaAktivnosti.service';
import { ObavestenjaAktivnostiEditComponent } from '../obavestenja-aktivnosti-edit/obavestenja-aktivnosti-edit.component';
import { DatePipe, NgFor, NgIf, SlicePipe } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { RegistrovaniKorisnikService } from '../../../Services/registrovaniKorisnici.service';
import { RegistrovaniKorisnik } from '../../../models/registrovaniKorisnik';

@Component({
  selector: 'app-obavestenja-aktivnosti-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule, DatePipe],
  templateUrl: './obavestenja-aktivnosti-list.component.html',
  styleUrl: './obavestenja-aktivnosti-list.component.css'
})
export class ObavestenjaAktivnostiListComponent implements OnInit {
  obavestenjaAktivnosti: ObavestenjeAktivnosti[] = [];
  registrovaniKorisnici: RegistrovaniKorisnik[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private obavestenjeAktivnostiService: ObavestenjaAktivnostiService,
    private dialog: MatDialog,
    private registrovaniKorisniciService: RegistrovaniKorisnikService
  ) {}

  ngOnInit(): void {
    this.load();
    this.loadRegistrovaniKorisnici();
  }

  load() {
    this.obavestenjeAktivnostiService.getAll().subscribe({
      next: (res) => {
        this.obavestenjaAktivnosti = res
      },
      error: (err) => console.error(err)
    });
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(ObavestenjaAktivnostiEditComponent, {
      width: '700px',
      data: { mode: 'add',
        registrovaniKorisnici: this.registrovaniKorisnici
       }
    });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  openEditDialog(row: ObavestenjeAktivnosti) {
    const dialogRef = this.dialog.open(ObavestenjaAktivnostiEditComponent, {
      width: '700px',
      data: { mode: 'edit', obavestenje: row,
        registrovaniKorisnici: this.registrovaniKorisnici
       }
    });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  delete(id: number) {
    this.obavestenjeAktivnostiService.delete(id).subscribe({
      next: () => this.load(),
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Nije moguće obrisati stavku.';
      }
    });
  }

  loadRegistrovaniKorisnici(){
    this.registrovaniKorisniciService.getAll().subscribe({
      next: (res) => {
        this.registrovaniKorisnici = res
        // console.log(this.registrovaniKorisnici);
        
      },
      error: (err) => {

      }
      
    })
  }

  // markRead(o: ObavestenjeAktivnosti) {
  //   if (!o?.id) return;
  //   this.obavestenjeAktivnostiService.markAsRead(o.id).subscribe({
  //     next: (res) => {
  //       const i = this.stavke.findIndex(x => x.id === o.id);
  //       if (i >= 0) this.stavke[i] = res;
  //     }
  //   });
  // }
}
