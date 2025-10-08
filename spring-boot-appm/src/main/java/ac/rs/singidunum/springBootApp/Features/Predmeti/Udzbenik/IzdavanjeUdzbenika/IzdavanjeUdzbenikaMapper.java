package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.UdzbenikDTO;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class IzdavanjeUdzbenikaMapper implements Mapper<IzdavanjeUdzbenikaDTO, IzdavanjeUdzbenika> {

	@Override
	public IzdavanjeUdzbenikaDTO map(IzdavanjeUdzbenika e) {
		if (e == null) {
			return null;
		}

		IzdavanjeUdzbenikaDTO iDto = new IzdavanjeUdzbenikaDTO(e.getId(), e.getKolicina());

		iDto.setOdobreno(e.getOdobreno());

		if (e.getPodnosilacZahteva() != null) {
			iDto.setPodnosilacZahtevaId(e.getPodnosilacZahteva().getId());
		}

		if (e.getUdzbenik() != null) {
			iDto.setUdzbenik(new UdzbenikDTO(e.getUdzbenik().getId(), e.getUdzbenik().getNaziv()));
		}

		if (e.getAutorizator() != null) {
			iDto.setAutorizator(
					new SluzbenikStudentskeDTO(
							e.getAutorizator().getId(),
							e.getAutorizator().getJmbg(),
							e.getAutorizator().getTelefon(),
							e.getAutorizator().getNalogAktivan()
					)
			);
		}

		if (e.getStudijskiProgram() != null) {
			iDto.setStudijskiProgram(
					new StudijskiProgramDTO(
							e.getStudijskiProgram().getId(),
							e.getStudijskiProgram().getNaziv()
					)
			);
		}


		if (e.getPodnosilacZahteva() != null) {
			iDto.setPodnosilacZahteva(
					new StudentNaGodiniDTO(
							e.getPodnosilacZahteva().getId(),
							e.getPodnosilacZahteva().getDatumUpisa(),
							e.getPodnosilacZahteva().getBrojIndeksa(),
							e.getPodnosilacZahteva().getProsek()
					)
			);
		}

		return iDto;
	}

	@Override
	public List<IzdavanjeUdzbenikaDTO> map(List<IzdavanjeUdzbenika> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}
}

