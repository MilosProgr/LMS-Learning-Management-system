import { Routes } from '@angular/router';
import { PredmetListComponent } from './predmet/predmet-list/predmet-list.component';
import { ObavestenjaPrikazComponent } from './obavestenja/obavestenja-prikaz.component';
import { KursListComponent } from './kurs-list/kurs-list.component';
import { UniverzitetListComponent } from './univerzitet/univerzitet-list/univerzitet-list.component';
import { AdresaListComponent } from './adresa-list/adresa-list.component';
import { StudijskiprogramComponent } from './studijskiprogram-list/studijskiprogram.component';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { StudentComponent } from './student/student.component';
import { StudentEditComponent } from './student-edit/student-edit.component';
import { NastavnikComponent } from './nastavnik/nastavnik.component';
import { NastavnikEditComponent } from './nastavnik-edit/nastavnik-edit.component';
import { authGuard } from './guards/auth.guard';
import { UniverzitetEditComponent } from './univerzitet/univerzitet-edit/univerzitet-edit.component';
import { MestoEditComponent } from './adresa-list/mesto-list/mesto-edit/mesto-edit.component';
import { AdresaEditComponent } from './adresa-list/adresa-edit/adresa-edit.component';
import { DrzavaEditComponent } from './adresa-list/drzava-list/drzava-edit/drzava-edit.component';
import { AktivnostiComponent } from './aktivnosti/aktivnosti.component';
import { ZahtevZaGodisnjiOdmorComponent } from './zahtev-za-godisnji-odmor/zahtev-za-godisnji-odmor.component';
import { PrikazRezervacijaComponent } from './prikaz-rezervacija/prikaz-rezervacija.component';
import { MojProfilComponent } from './moj-profil/moj-profil.component';
import { UlogaListComponent } from './prava-pristupa/uloga-list/uloga-list.component';
import { UlogaEditComponent } from './prava-pristupa/uloga-edit/uloga-edit.component';
import { KorisnikUlogaComponent } from './prava-pristupa/korisnik-uloga.component';
import { SifarnikEditComponent } from './sifarnik-list/sifarnik-edit/sifarnik-edit.component';
import { SifarnikListComponent } from './sifarnik-list/sifarnik-list.component';
import { RasporedIspitaComponent } from './studentska-sluzba/rasporedi-dogadjaja/raspored-ispita/raspored-ispita.component';
// import { RasporedNastaveComponent } from './studentska-sluzba/rasporedi-dogadjaja/raspored-nastave/raspored-nastave.component';
import { UredjivanjeSilabusaComponent } from './uredjivanje-silabusa/uredjivanje-silabusa.component';
import { PredmetiAngazovaniComponent } from './predmet/predmeti-angazovani/predmeti-angazovani.component';
import { StudijskiProgramEditComponent } from './studijskiprogram-list/studijski-program-edit/studijski-program-edit.component';
import { RegistrovaniKorisniciEditComponent } from './login-register/registrovani-korisnici/registrovani-korisnici-edit/registrovani-korisnici-edit.component';
import { RegistrovaniKorisniciComponent } from './login-register/registrovani-korisnici/registrovani-korisnici.component';
import { KursEditComponent } from './kurs-list/kurs-edit/kurs-edit.component';
import { UpisiSeComponent } from './upisi-se/upisi-se.component';
import { DodelaIndeksaComponent } from './upisi-se/dodela-indeksa/dodela-indeksa.component';
import { IzdavanjeDokumenataComponent } from './izdavanje-dokumenata/izdavanje-dokumenata.component';
import { KreiranjeIspitniRokComponent } from './studentska-sluzba/kreiranje-ispitni-rok/kreiranje-ispitni-rok.component';
import { PrijavaIspitaComponent } from './prijava-ispita/prijava-ispita.component';
import { UpisOcenaComponent } from './upis-ocena/upis-ocena.component';
import { EvaluacijaZnanjaComponent } from './evaluacija-znanja/evaluacija-znanja.component';
import { UniverzitetDetaljiComponent } from './univerzitet/univerzitet-page-home/univerzitet-detalji/univerzitet-detalji.component';
import { MainPageComponent } from './univerzitet/univerzitet-page-home/main-page/main-page.component';
import { FakultetListComponent } from './fakultet/fakultet-list/fakultet-list.component';
import { FakultetEditComponent } from './fakultet/fakultet-edit/fakultet-edit.component';
import { FakultetDetaljiComponent } from './fakultet/fakultet-detalji/fakultet-detalji.component';
import { FakultetPrikazComponent } from './fakultet/fakultet-prikaz/fakultet-prikaz.component';
import { BiranjePredmetaComponent } from './biranje-predmeta/biranje-predmeta.component';
import { GodinaStudijaListComponent } from './godinaStudija/godina-studija-list/godina-studija-list.component';
import { StudentPredmetiComponent } from './predmet/student-predmeti/student-predmeti.component';
import { ObavestenjaListComponent } from './obavestenja/obavestenja-list/obavestenja-list.component';
import { ObavestenjaAktivnostiListComponent } from './obavestenja-aktivnosti/obavestenja-aktivnosti-list/obavestenja-aktivnosti-list.component';
import { UplataNaRacunComponent } from './studentska-sluzba/uplata-na-racun/uplata-na-racun.component';
import { DodelaNastavnikaNaRealizacijiComponent } from './dodela-nastavnika-na-realizaciji/dodela-nastavnika-na-realizaciji.component';
import { DodelaPredmetaRealizacijaComponent } from './dodela-nastavnika-na-realizaciji/dodela-predmeta/dodela-predmeta.component';
import { SemestarListComponent } from './studentska-sluzba/semestar-list/semestar-list.component';
import { InstrumentEvaluacijeListComponent } from './instrumentEvaluacije-File/instrument-evaluacije-list/instrument-evaluacije-list.component';
import { TrebovanjeInventaraComponent } from './trebovanje-inventara/trebovanje-inventara.component';
import { IzdavanjeUdzbenikaComponent } from './izdavanje-udzbenika/izdavanje-udzbenika.component';
import { UdzbeniciListComponent } from './udzbenici-list/udzbenici-list.component';
import { TipEvaluacijeListComponent } from './tip-evaluacije/tip-evaluacije-list/tip-evaluacije-list.component';

export const routes: Routes = [
    { path: 'zahtev-za-godisnji', component: ZahtevZaGodisnjiOdmorComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },
    { path: 'prikaz-rezervacija', component: PrikazRezervacijaComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    //Rute za realizacije

    { path: 'dodela-predmeta', component: DodelaPredmetaRealizacijaComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'dodela-realizacije', component: DodelaNastavnikaNaRealizacijiComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    //Ruta za aktivnosti (predmeti koji se slusaju)
    { path: 'aktivnosti', component: AktivnostiComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_STUDENT'] }, canActivate: [authGuard] },

    //ruta za opsta obavestenja
    { path: "obavestenja", component: ObavestenjaPrikazComponent } , //data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK', 'ROLE_STUDENT', 'ROLE_SLUZBA', 'ROLE_KORISNIK'] }, canActivate: [authGuard] },
    // { path: 'obavestenja/add', component: DodajObavestenjeComponent, data: { allowedRoles: ['ROLE_SLUZBA', 'ROLE_ADMIN'] }, canActivate: [authGuard] },

    { path: 'kurs-list', component: KursListComponent, data: { allowedRoles: ['ROLE_STUDENT', 'ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'kurs/edit/:id', component: KursEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    //rute za univerzitet
    { path: 'univerzitet-list', component: UniverzitetListComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'univerzitet/edit/:id', component: UniverzitetEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'udzbenici-list', component: UdzbeniciListComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },
    // rute za mesto, drzava, adresa
    { path: 'adresa-list', component: AdresaListComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'mesto/edit/:id', component: MestoEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'adresa/edit/:id', component: AdresaEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'drzava/edit/:id', component: DrzavaEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    // rute za fakultet
    { path: 'fakultet-list', component: FakultetListComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'fakultet/edit/:id', component: FakultetEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    //rute za studijski program
    { path: 'studijskiProgram-list', component: StudijskiprogramComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] }, //DODATI ZASTITE PRE Commit
    { path: 'studijskiProgram/edit/:id', component: StudijskiProgramEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'semestar-list', component: SemestarListComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },


    //rute za profile
    { path: 'student-profil', component: StudentComponent },
    //rute za stdudijski program
    { path: 'studijskiProgram-list', component: StudijskiprogramComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'moj-profil', component: MojProfilComponent},
    { path: 'student-lista', component: StudentComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },
    { path: 'edit-profil', component: StudentEditComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_STUDENT'] }, canActivate: [authGuard]},
    { path: 'nastavnik-profil', component: NastavnikComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK'] }, canActivate: [authGuard]},
    { path: 'nastavnik-uredi-profil', component: NastavnikEditComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK'] }, canActivate: [authGuard] },
    { path: 'uredjivanje-silabusa', component: UredjivanjeSilabusaComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK'] }, canActivate: [authGuard] },
    { path: 'predmeti-angazovani', component: PredmetiAngazovaniComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK'] }, canActivate: [authGuard] },

    { path: 'prijava', component: LoginRegisterComponent },

    { path: "", redirectTo: "univerzitet-home", pathMatch: "full" }, //preuzmerenje stranice
    //Rute za sifarnik
    { path: 'sifarnik-list', component: SifarnikListComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'sifra/edit/:id', component: SifarnikEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },

    //rute za Godine studija
    { path: 'godinaStudija-list', component: GodinaStudijaListComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },

    //Rute za prava pristupa
    { path: 'uloga-list', component: UlogaListComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'uloga/edit/:id', component: UlogaEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'uloga/dodajUlogu', component: KorisnikUlogaComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    //Rute za Registrovanog Korisnika
    { path: 'registrovaniKorisnik-list', component: RegistrovaniKorisniciComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'registrovaniKorisnik/edit/:id', component: RegistrovaniKorisniciEditComponent, data: { allowedRoles: ['ROLE_ADMIN'] }, canActivate: [authGuard] },

    // { path: "raspored-predavanja", component: RasporedNastaveComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK', 'ROLE_STUDENT'] }, canActivate: [authGuard] },
    { path: 'raspored-rokova', component: RasporedIspitaComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_NASTAVNIK', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },
    //rute za ne registrovanog korisnika
    { path: 'univerzitet-home', component: MainPageComponent },
    { path: 'fakultet-home/fakulteti/:id', component: FakultetDetaljiComponent },
    { path: 'univerzitet-home/univerziteti/:id', component: UniverzitetDetaljiComponent },
    { path: 'fakultet-home', component: FakultetPrikazComponent },

    { path: 'upisi-se', component: UpisiSeComponent, data: { allowedRoles: ['ROLE_KORISNIK', 'ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'ispitni-rok-list', component: KreiranjeIspitniRokComponent, data: { allowedRoles: ['ROLE_SLUZBA', 'ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'dodela-indeksa', component: DodelaIndeksaComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },
    { path: 'izdavanje-dokumenata', component: IzdavanjeDokumenataComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA'], }, canActivate: [authGuard] },
    { path: 'uplata-na-racun', component: UplataNaRacunComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA'], }, canActivate: [authGuard] },

    { path: 'trebovanje-inventara', component: TrebovanjeInventaraComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA', 'ROLE_NASTAVNIK'] }, canActivate: [authGuard] },
    { path: 'izdavanjeUdzbenika', component: IzdavanjeUdzbenikaComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA', 'ROLE_STUDENT'] }, canActivate: [authGuard] },


    { path: 'prijava-ispita', component: PrijavaIspitaComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_STUDENT'] }, canActivate: [authGuard] },
    { path: 'biranje-predmeta', component: BiranjePredmetaComponent, data: { allowedRoles: ['ROLE_ADMIN','ROLE_STUDENT'] }, canActivate: [authGuard] },
    { path: 'student-predmeti', component: StudentPredmetiComponent, data: { allowedRoles: ['ROLE_ADMIN','ROLE_STUDENT'] }, canActivate: [authGuard] },
    { path: 'predmet-list', component: PredmetListComponent, data: { allowedRoles: ['ROLE_ADMIN','ROLE_STUDENT'] }, canActivate: [authGuard] },

    { path: "obavestenja-list", component: ObavestenjaListComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_SLUZBA'] }, canActivate: [authGuard] },
    { path: "obavestenja-aktivnosti-list", component: ObavestenjaAktivnostiListComponent, data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_NASTAVNIK'] }, canActivate: [authGuard] },
    
    { path: 'upis-ocena', component: UpisOcenaComponent, data: { allowedRoles: ['ROLE_NASTAVNIK', 'ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'evaluacija-znanja', component: EvaluacijaZnanjaComponent, data: { allowedRoles: ['ROLE_NASTAVNIK', 'ROLE_ADMIN'] }, canActivate: [authGuard] },
    { path: 'tipEvaluacije', component: TipEvaluacijeListComponent, data: { allowedRoles: ['ROLE_ADMIN', "ROLE_SLUZBA"] }, canActivate: [authGuard] },
    { path: 'instrument-evaluacije-list', component: InstrumentEvaluacijeListComponent, data: { allowedRoles: ['ROLE_NASTAVNIK', 'ROLE_ADMIN'] }, canActivate: [authGuard] }

];