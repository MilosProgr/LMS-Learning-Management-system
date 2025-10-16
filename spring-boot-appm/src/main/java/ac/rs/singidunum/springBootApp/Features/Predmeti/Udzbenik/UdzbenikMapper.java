package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.UdzbenikDTO.UdzbenikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class UdzbenikMapper implements Mapper<UdzbenikDTORecord, Udzbenik> {

    @Override
    public UdzbenikDTORecord map(Udzbenik e) {
        if (e == null) return null;

        GodinaStudijaDTORecord godinaDTO = e.getGodinaStudija() != null
                ? new GodinaStudijaDTORecord(
                        e.getGodinaStudija().getId(),
                        e.getGodinaStudija().getGodina(), null, null
                )
                : null;

        PredmetDTORecord predmetDTO = e.getPredmet() != null
                ? new PredmetDTORecord(
                        e.getPredmet().getId(),
                        e.getPredmet().getNaziv(),
                        e.getPredmet().getEsbn(),
                        e.getPredmet().getObavezan(),
                        e.getPredmet().getBrojPredavanja(),
                        e.getPredmet().getBrojVezbi(),
                        e.getPredmet().getDrugiObliciNastave(),
                        e.getPredmet().getIstrazivackiRad(),
                        e.getPredmet().getOstaliCasovi(), null, null, null, godinaDTO, null, null
                )
                : null;

        return new UdzbenikDTORecord(
                e.getId(),
                godinaDTO,
                e.getNaziv(),
                predmetDTO
        );
    }

//    @Override
//    public List<UdzbenikDTORecord> map(List<Udzbenik> e) {
//        if (e == null) return null;
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
