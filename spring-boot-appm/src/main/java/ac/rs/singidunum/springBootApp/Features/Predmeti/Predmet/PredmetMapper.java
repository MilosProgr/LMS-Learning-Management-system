package ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs.KursDTO.KursDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Sifarnik.SifraDTO.SifraDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class PredmetMapper implements Mapper<PredmetDTORecord, Predmet> {

	@Override
	public PredmetDTORecord map(Predmet e) {
		if (e == null) return null;

        SifraDTORecord sifra = e.getSifra() != null
                ? new SifraDTORecord(e.getSifra().getId(), e.getSifra().getTekst())
                : null;

        GodinaStudijaDTORecord godinaStudija = e.getGodinaStudija() != null
                ? new GodinaStudijaDTORecord(e.getGodinaStudija().getId(), e.getGodinaStudija().getGodina(), null, null)
                : null;

        Set<IshodDTORecord> silabus = e.getSilabus() != null
                ? e.getSilabus().stream()
                    .map(i -> new IshodDTORecord(i.getId(), i.getOpis(), i.isPolozeno(), null))
                    .collect(Collectors.toSet())
                : null;

        Set<StudijskiProgramDTORecord> studijskiProgrami = e.getStudijskiProgrami() != null
                ? e.getStudijskiProgrami().stream()
                    .map(sp -> new StudijskiProgramDTORecord(sp.getId(), sp.getNaziv(), null, null, null, null))
                    .collect(Collectors.toSet())
                : null;

//        RealizacijaPredmetaDTORecord realizacijaPredmeta = e.getRealizacijaPredmeta() != null
//                ? new RealizacijaPredmetaDTORecord(
//                        e.getRealizacijaPredmeta().getId(),
//                        e.getRealizacijaPredmeta().getDatumPocetka(),
//                        e.getRealizacijaPredmeta().getDatumZavrsetka(),
//                        null,
//                        null,
//                        null
//                )
//                : null;
        Set<KursDTORecord> kursevi = e.getKursevi() != null
        		? e.getKursevi().stream()
        				.map(ks -> new KursDTORecord(
        						ks.getId(),
        						ks.getNaziv(),
        						ks.getTrajanje(),
        						ks.getOznaka(),
        						ks.getDatumPocetka(),
        						ks.getDatumKraja(),
        						Set.of()))
        				.collect(Collectors.toSet())
        				: null;

        return new PredmetDTORecord(
                e.getId(),
                e.getNaziv(),
                e.getEsbn(),
                e.getObavezan(),
                e.getBrojPredavanja(),
                e.getBrojVezbi(),
                e.getDrugiObliciNastave(),
                e.getIstrazivackiRad(),
                e.getOstaliCasovi(),
                silabus,
                sifra,
                studijskiProgrami,
                godinaStudija,
                null,
                kursevi
        );
    }

//	@Override
//	public List<PredmetDTORecord> map(List<Predmet> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());	
//
//	}


}
