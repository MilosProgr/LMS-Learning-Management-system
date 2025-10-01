package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Udzbenici;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Udzbenici.IzdavanjeUdzbenika;

@Repository
public interface IzdavanjeUdzbenikaRepository extends CrudRepository<IzdavanjeUdzbenika, Long> {

}
