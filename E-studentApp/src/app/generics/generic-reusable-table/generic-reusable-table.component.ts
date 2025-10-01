import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TabelaKolone } from '../tabela-model';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-generic-reusable-table',
  standalone: true,
  imports: [NgFor, NgClass, NgIf, RouterLink, NgxPaginationModule],
  templateUrl: './generic-reusable-table.component.html',
  styleUrls: ['./generic-reusable-table.component.css']
})
export class GenericReusableTableComponent {
  @Input() podaci: any = [];
  @Input() kljucevi: any[] = [];
  @Input() kljuceviStringovi: string[] = [];
  @Input() editRoute: string = '/';
  @Input() naslov: string = '';

  @Output() obrisiEvent: EventEmitter<number> = new EventEmitter<number>();
  @Output() izmeniEvent: EventEmitter<any> = new EventEmitter<any>();

  currentPage: number = 1;  // trenutna stranica
  itemsPerPage: number = 15;  // broj stavki po stranici

  ukloni(id: any) {
    this.obrisiEvent.emit(id);
  }

  izmena(id: any) {
    this.izmeniEvent.emit(id);
  }

  isArray(value: any): boolean {
    return Array.isArray(value);
  }

  isObject(value: any): boolean {
    return value !== null && typeof value === 'object' && !Array.isArray(value);
  }

  getObjectKeys(obj: any): string[] {
    return Object.keys(obj);
  }

  getJoinedValues(value: any): string {
    if (Array.isArray(value)) {
      return value.map(obj => obj.korisnik?.korisnickoIme || obj.ime || obj.id).join(', ');
    }
    return value;
  }

  getKljucevi(): any[] {
    if (this.kljucevi.length > 0) {
      return this.kljucevi;
    }
    return this.kljuceviStringovi.map(k => ({ imeKolone: k, kljuc: k }));
  }
}

//PRIMERI ZA LISTU KLJUCEVA 
// kljuceviUloga = [
//   { imeKolone: 'Ime', kljuc: 'ime' },
//   { imeKolone: 'Id', kljuc: 'id' },
//   { imeKolone: 'Korisnici', kljuc: 'pravaPristupa' },
// ];

//ILI


