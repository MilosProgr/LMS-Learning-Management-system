import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';


import { NgFor, NgIf } from '@angular/common';
import { Univerzitet } from '../../../../models/univerzitetModel';
import { UniverzitetService } from '../../../../Services/univerzitet.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-univerzitet-detalji',
  standalone: true,
  imports: [NgIf, NgFor, RouterLink, CommonModule, DatePipe],
  templateUrl: './univerzitet-detalji.component.html',
  styleUrl: './univerzitet-detalji.component.css'
})
export class UniverzitetDetaljiComponent  implements OnInit {

  routeSub: Subscription | undefined;
  fakultetSub: Subscription | undefined;
  univerzitet: Univerzitet | undefined;
  univerziteti: Univerzitet[] = [];

  constructor(private route: ActivatedRoute, private univerzitetService: UniverzitetService, private location: Location) { }

  ngOnInit(): void {
    // Preuzmite listu fakulteta
    this.univerzitetService.getAll().subscribe(data => {
      this.univerziteti = data;

// Slušajte promene parametara rute
this.routeSub = this.route.paramMap.subscribe(params => {
  const id = Number(params.get('id'));
  // console.log('ID:', id); 
  this.univerzitet = this.univerziteti.find(f => f.id === id);
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

  goBack() {
    this.location.back();
  }
}

