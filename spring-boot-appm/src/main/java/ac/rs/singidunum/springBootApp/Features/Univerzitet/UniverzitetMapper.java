package ac.rs.singidunum.springBootApp.Features.Univerzitet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.UniverzitetDTO.UniverzitetDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class UniverzitetMapper implements Mapper<UniverzitetDTORecord, Univerzitet> {

    @Override
    public UniverzitetDTORecord map(Univerzitet e) {
        if (e == null) {
            return null;
        }

        // 🔹 mapiranje adrese
        AdresaDTORecord adresaDto = null;
        if (e.getAdresa() != null) {
            adresaDto = new AdresaDTORecord(
                    e.getAdresa().getId(),
                    e.getAdresa().getUlica(),
                    e.getAdresa().getBroj(),
                    null // ako ima MestoDTORecord, tu se može dodati
            );
        }

        // 🔹 mapiranje rektora
        NastavnikDTORecord rektorDto = null;
        if (e.getRektor() != null) {

            RegistrovaniKorisnikDTORecord korisnikDto = null;
            if (e.getRektor().getKorisnik() != null) {
                korisnikDto = new RegistrovaniKorisnikDTORecord(
                        e.getRektor().getKorisnik().getId(),
                        e.getRektor().getKorisnik().getIme(),
                        e.getRektor().getKorisnik().getPrezime(),
                        e.getRektor().getKorisnik().getKorisnickoIme(),
                        e.getRektor().getKorisnik().getLozinka(),
                        e.getRektor().getKorisnik().getEmail(),
                        null,
                        null
                );
            }

            rektorDto = new NastavnikDTORecord(
                    e.getRektor().getId(),
                    e.getRektor().getBiografija(),
                    e.getRektor().getJmbg(),
                    e.getRektor().getTelefon(),
                    e.getRektor().getPoslovniMail(),
                    e.getRektor().getBrojSlobodnihDana(),
                    e.getRektor().getBrojIskoristenihDana(),
                    korisnikDto,
                    null,
                    null
            );
        }

        // 🔹 mapiranje fakulteta
        List<FakultetDTORecord> fakultetiDto = null;
        if (e.getFakulteti() != null) {
            fakultetiDto = e.getFakulteti().stream()
                    .map(f -> new FakultetDTORecord(
                            f.getId(),
                            f.getNaziv(),
                            null,
                            null,
                            null,
                            f.getTelefon(),
                            f.getOpis(),
                            null
                    ))
                    .collect(Collectors.toList());
        }

        // 🔹 kreiranje univerziteta recorda
        return new UniverzitetDTORecord(
                e.getId(),
                e.getNaziv(),
                e.getOpis(),
                e.getDatumOsnivanja(),
                fakultetiDto,
                adresaDto,
                rektorDto
        );
    }

//    @Override
//    public List<UniverzitetDTORecord> map(List<Univerzitet> e) {
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
