package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.UdzbenikDTO.UdzbenikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika.IzdavanjeUdzbenikaDTO.IzdavanjeUdzbenikaDTORecord;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO.SluzbenikStudentskeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class IzdavanjeUdzbenikaMapper implements Mapper<IzdavanjeUdzbenikaDTORecord, IzdavanjeUdzbenika> {

    @Override
    public IzdavanjeUdzbenikaDTORecord map(IzdavanjeUdzbenika e) {
        if (e == null) return null;

        // Map podnosilac zahteva
        StudentNaGodiniDTORecord podnosilacDTO = e.getPodnosilacZahteva() != null
                ? new StudentNaGodiniDTORecord(
                        e.getPodnosilacZahteva().getId(),
                        e.getPodnosilacZahteva().getDatumUpisa(),
                        e.getPodnosilacZahteva().getBrojIndeksa(),
                        null, null, e.getPodnosilacZahteva().getProsek(), null, null, null
                )
                : null;

        // Map autorizator
        SluzbenikStudentskeDTORecord autorizatorDTO = e.getAutorizator() != null
                ? new SluzbenikStudentskeDTORecord(
                        e.getAutorizator().getId(),
                        e.getAutorizator().getJmbg(),
                        e.getAutorizator().getTelefon(),
                        e.getAutorizator().getNalogAktivan(),
                        null
                )
                : null;

        // Map udzbenik
        UdzbenikDTORecord udzbenikDTO = e.getUdzbenik() != null
                ? new UdzbenikDTORecord(
                        e.getUdzbenik().getId(),
                        null, // godinaStudija ako je potrebno
                        e.getUdzbenik().getNaziv(),
                        null  // predmet ako je potrebno
                )
                : null;

        // Map studijski program
        StudijskiProgramDTORecord programDTO = e.getStudijskiProgram() != null
                ? new StudijskiProgramDTORecord(
                        e.getStudijskiProgram().getId(),
                        e.getStudijskiProgram().getNaziv(),
                        null, null, null, null
                )
                : null;

        Long podnosilacId = e.getPodnosilacZahteva() != null ? e.getPodnosilacZahteva().getId() : null;

        return new IzdavanjeUdzbenikaDTORecord(
                e.getId(),
                e.getKolicina(),
                podnosilacDTO,
                autorizatorDTO,
                udzbenikDTO,
                e.getOdobreno(),
                podnosilacId,
                programDTO
        );
    }

    @Override
    public List<IzdavanjeUdzbenikaDTORecord> map(List<IzdavanjeUdzbenika> e) {
        if (e == null) return null;
        return e.stream().map(this::map).collect(Collectors.toList());
    }
}
