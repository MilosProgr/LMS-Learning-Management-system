import { Component, OnInit } from '@angular/core';
import { RezervacijaService } from '../../Services/rezervacija.service';
import { NastavnikService } from '../../Services/nastavnik.service';
import { Rezervacija } from '../../models/rezervacija.model';
import { GenericReusableTableComponent } from '../generics/generic-reusable-table/generic-reusable-table.component';
import { Nastavnik } from '../../models/nastavnik.model';
import { CommonModule } from '@angular/common';
import { StatusOdmora } from '../../models/status-odmora.enum';

@Component({
  selector: 'app-prikaz-rezervacija',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prikaz-rezervacija.component.html',
  styleUrls: ['./prikaz-rezervacija.component.css']
})
export class PrikazRezervacijaComponent implements OnInit {

  rezervacije: Rezervacija[] = [];
  nastavnici: Nastavnik[] = [];
  rezervacijeWithNastavnik: any[] = [];
  kljucevi: string[] = [];

  constructor(private rezervacijaService: RezervacijaService, private nastavniciService: NastavnikService) { }

  ngOnInit(): void {
    this.loadRezervacije();
    this.loadNastavnici();
  }

  loadRezervacije(): void {
    this.rezervacijaService.getAll().subscribe(rezervacije => {
      this.rezervacije = rezervacije;

      this.nastavniciService.getAll().subscribe(nastavnici => {
        this.nastavnici = nastavnici;

        this.rezervacijeWithNastavnik = this.rezervacije.map(rezervacija => {
          const nastavnik = this.nastavnici.find(n => n.id === rezervacija.nastavnikId);

          const statusOdmora = rezervacija.statusOdmora !== undefined
            ? rezervacija.statusOdmora
            : false;

          console.log(`Rezervacija ID: ${rezervacija.id}, Status Odmora: ${statusOdmora}`);

          return {
            ...rezervacija,
            nastavnikIme: nastavnik ? nastavnik.korisnik?.ime : 'Unknown',
            nastavnikPrezime: nastavnik ? nastavnik.korisnik?.prezime : 'Unknown',
            statusOdmora: statusOdmora
          };
        });

        if (this.rezervacijeWithNastavnik.length > 0) {
          this.kljucevi = Object.keys(this.rezervacijeWithNastavnik[0]);
        }
      });
    });
  }

  private loadNastavnici(): void {
    this.nastavniciService.getAll().subscribe(nastavnik => {
      this.nastavnici = nastavnik;
      console.log("Nastavnici: ", nastavnik);
    });
  }

  private calculateWorkdays(startDate: string, endDate: string): number {
    const start = new Date(startDate);
    const end = new Date(endDate);
    let workdays = 0;

    while (start <= end) {
      const dayOfWeek = start.getDay();
      if (dayOfWeek !== 0 && dayOfWeek !== 6) {  
        workdays++;
      }
      start.setDate(start.getDate() + 1);
    }

    return workdays;
  }

  handleOdobri(id: number, rezervacija: Rezervacija): void {
    const selectedNastavnik = this.nastavnici.find(n => n.id === rezervacija.nastavnikId);
    if (selectedNastavnik) {
      if (rezervacija.datumOd && rezervacija.datumDo) {
        const workdays = this.calculateWorkdays(rezervacija.datumOd.toString(), rezervacija.datumDo.toString());
        console.log('Workdays:', workdays);

        selectedNastavnik.brojSlobodnihDana = selectedNastavnik.brojSlobodnihDana ?? 0;
        selectedNastavnik.brojIskoristenihDana = selectedNastavnik.brojIskoristenihDana ?? 0;

        selectedNastavnik.brojSlobodnihDana -= workdays;
        selectedNastavnik.brojIskoristenihDana += workdays;

        console.log('Selected Nastavnik before update:', selectedNastavnik);

        const payload = {
          id: rezervacija.id,
          opis: rezervacija.opis,
          datumOd: rezervacija.datumOd,
          datumDo: rezervacija.datumDo,
          nastavnik: {
            id: selectedNastavnik.id,
            brojSlobodnihDana: selectedNastavnik.brojSlobodnihDana,
            brojIskoristenihDana: selectedNastavnik.brojIskoristenihDana
          },
          statusOdmora: StatusOdmora.APPROVED
        };

        console.log('Payload for update:', payload);
        
        this.rezervacijaService.updateRezervacija(id, payload).subscribe(response => {
          console.log('Update response:', response);
          // alert(`Vacation request for ${selectedNastavnik.ime} has been approved.`);

          //PROVERI OVDE DA LI SAM DOBAR ID DODAO!
          this.nastavniciService.update(selectedNastavnik.id!, selectedNastavnik).subscribe(updatedNastavnik => {
            console.log('Nastavnik updated:', updatedNastavnik);
            this.loadRezervacije();
          }, error => {
            console.error('Error updating Nastavnik:', error);
          });
        }, error => {
          console.error('Update error:', error);
        });
      } else {
        console.error('Invalid dates provided:', rezervacija.datumOd, rezervacija.datumDo);
      }
    } else {
      console.error('Nastavnik not found for id:', rezervacija.nastavnikId);
    }
  }

  handleOdbi(id: number, rezervacija: Rezervacija): void {
    const selectedNastavnik = this.nastavnici.find(n => n.id === rezervacija.nastavnikId);
    if (selectedNastavnik) {
      const payload = {
        id: rezervacija.id,
        opis: rezervacija.opis,
        datumOd: rezervacija.datumOd,
        datumDo: rezervacija.datumDo,
        nastavnik: {
          id: selectedNastavnik.id
        },
        statusOdmora: StatusOdmora.DENIED
      };

      this.rezervacijaService.updateRezervacija(id, payload).subscribe(() => {
        // alert(`Vacation request for ${selectedNastavnik.ime} has been denied.`);
        this.loadRezervacije();
      });
    } else {
      console.error('Nastavnik not found for id:', rezervacija.nastavnikId);
    }
  }
}
