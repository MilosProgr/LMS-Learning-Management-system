package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IzdavanjeUdzbenikaRepository extends CrudRepository<IzdavanjeUdzbenika, Long> {

}
