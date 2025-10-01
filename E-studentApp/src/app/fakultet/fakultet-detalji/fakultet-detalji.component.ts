import { Component, OnInit } from '@angular/core';
import { FakultetService } from '../../../Services/fakultet.service';
import { Fakultet } from '../../../models/fakultetModel';
import { ActivatedRoute } from '@angular/router';
import { NgIf } from '@angular/common';
import { Subscription } from 'rxjs/internal/Subscription';
import { FakultetListComponent } from '../fakultet-list/fakultet-list.component';
import { MatIconModule } from '@angular/material/icon';
import { Location } from '@angular/common';

@Component({
  selector: 'app-fakultet-detalji',
  standalone: true,
  imports: [NgIf, FakultetListComponent, MatIconModule],
  templateUrl: './fakultet-detalji.component.html',
  styleUrl: './fakultet-detalji.component.css',

})
export class FakultetDetaljiComponent implements OnInit {
  
  routeSub: Subscription | undefined;
  fakultetSub: Subscription | undefined;
  fakultet: Fakultet | undefined;
  fakulteti: Fakultet[] = [];

  constructor(private route: ActivatedRoute, private fakultetService: FakultetService, private location: Location) { }

  ngOnInit(): void {
    // Preuzmite listu fakulteta
    this.fakultetService.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;

// Slušajte promene parametara rute
this.routeSub = this.route.paramMap.subscribe(params => {
  const id = Number(params.get('id'));
  // console.log('ID:', id); 
  this.fakultet = this.fakulteti.find(f => f.id === id);
  // if (this.fakultet) {
  //   console.log('Fakultet:', this.fakultet); 
  // } else {
  //   console.log('Nema podataka za dati ID');
  // }
});
});
  }


  ngOnDestroy(): void {
    if (this.routeSub) {
      this.routeSub.unsubscribe();
    }
    if (this.fakultetSub) {
      this.fakultetSub.unsubscribe();
    }
  }

  goBack(): void {
    this.location.back();
  }
}



