package ac.rs.singidunum.springBootApp.Features.Student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO.StudentDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class StudentMapper implements Mapper<StudentDTORecord, Student> {

    @Override
    public StudentDTORecord map(Student e) {
        if (e == null) return null;

        // ✅ Korisnik
        RegistrovaniKorisnikDTORecord korisnik = e.getKorisnik() != null
                ? new RegistrovaniKorisnikDTORecord(
                        e.getKorisnik().getId(),
                        e.getKorisnik().getIme(),
                        e.getKorisnik().getPrezime(),
                        e.getKorisnik().getKorisnickoIme(),
                        e.getKorisnik().getLozinka(),
                        e.getKorisnik().getEmail(), null, null
                )
                : null;

        // ✅ Država
        DrzavaDTORecord drzava = e.getDrzava() != null
                ? new DrzavaDTORecord(
                        e.getDrzava().getId(),
                        e.getDrzava().getNaziv(), null
                )
                : null;

        // ✅ Mesto prebivališta
        MestoDTORecord mestoPrebivalista = e.getMestoPrebivalista() != null
                ? new MestoDTORecord(
                        e.getMestoPrebivalista().getId(),
                        e.getMestoPrebivalista().getNaziv(),
                        null // ako mesto ima referencu na državu, možeš dodati ovde
                )
                : null;

        // ✅ Adresa
        AdresaDTORecord adresa = e.getAdresa() != null
                ? new AdresaDTORecord(
                        e.getAdresa().getId(),
                        e.getAdresa().getUlica(),
                        e.getAdresa().getBroj(),
                        null // mesto ako želiš kasnije dodati
                )
                : null;

        // ✅ Upisi (student_na_godini)
        List<StudentNaGodiniDTORecord> upisi = e.getUpisi() != null
                ? e.getUpisi().stream()
                        .map(upis -> new StudentNaGodiniDTORecord(
                                upis.getId(),
                                upis.getDatumUpisa(),
                                upis.getBrojIndeksa(),
                                null, null, upis.getProsek(), null, null, null
                        ))
                        .collect(Collectors.toList())
                : null;

        // ✅ Finalni record
        return new StudentDTORecord(
                e.getId(),
                e.getJmbg(),
                e.getTelefon(),
                e.getStatusStudiranja(),
                e.isPredmetiIzabrani(),
                e.getStanjeNaRacunu(),
                korisnik,
                mestoPrebivalista,
                adresa,
                drzava,
                upisi
        );
    }

    @Override
    public List<StudentDTORecord> map(List<Student> e) {
        return e.stream().map(this::map).collect(Collectors.toList());
    }
}
