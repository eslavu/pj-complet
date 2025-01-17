package pj.lab9;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import jakarta.transaction.*;

public interface MasinaSpringDataJpaRepository extends JpaRepository<Masina, Integer>
{
	@Query("SELECT m FROM Masina m WHERE m.nr_inmatriculare = ?1")
	List<Masina> findByNr_inmatriculare(String nr_inmatriculare);
	
	@Query("SELECT m FROM Masina m WHERE m.an_fabricatie >= ?1")
	List<Masina> getByAn_fabricatie(Integer an_fabricatie);
	
	Integer countByMarca(String marca);
	
	@Query("SELECT COUNT(m) FROM Masina m WHERE m.nr_km < ?1")
	Integer getByNr_km(Integer nr_km);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Masina m WHERE m.nr_inmatriculare LIKE ?1")
    Integer deleteByNr_inmatriculare(String nr_inmatriculare);
}