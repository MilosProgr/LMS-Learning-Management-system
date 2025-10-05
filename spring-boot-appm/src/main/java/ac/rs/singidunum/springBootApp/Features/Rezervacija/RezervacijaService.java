package ac.rs.singidunum.springBootApp.Features.Rezervacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikRepository;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class RezervacijaService extends GenericCrudService<RezervacijaDTO, Rezervacija, Long> {

    @Autowired
    private NastavnikRepository nastavnikRepository;

    protected RezervacijaService(CrudRepository<Rezervacija, Long> repository, Mapper<RezervacijaDTO, Rezervacija> mapper) {
        super(repository, mapper);
    }

    private int calculateWorkdays(LocalDate startDate, LocalDate endDate) {
        int workdays = 0;
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                workdays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return workdays;
    }

//    public void updateVacationDays(Long nastavnikId, LocalDate startDate, LocalDate endDate) {
//        int workdays = calculateWorkdays(startDate, endDate);
//
//        Nastavnik nastavnik = nastavnikRepository.findById(nastavnikId)
//                .orElseThrow(() -> new IllegalArgumentException("Nastavnik not found"));
//
//        if (nastavnik.getBrojSlobodnihDana() >= workdays) {
//            nastavnik.setBrojSlobodnihDana(nastavnik.getBrojSlobodnihDana() - workdays);
//            nastavnik.setBrojIskoristenihDana(nastavnik.getBrojIskoristenihDana() + workdays);
//            nastavnikRepository.save(nastavnik);
//        } else {
//            throw new IllegalArgumentException("Nedovoljno dana za odmor");
//        }
//    }

//    @Override
//    public RezervacijaDTO save(Rezervacija rezervacija) {
//        updateVacationDays(rezervacija.getNastavnik().getId(), rezervacija.getDatumOd(), rezervacija.getDatumDo());
//        return super.save(rezervacija);
//    }
}

