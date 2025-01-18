package pj.lab11;

import java.util.*;
import java.time.*;

import org.springframework.data.jpa.repository.*;
import jakarta.transaction.*;

public interface EvenimentRepository extends JpaRepository<Eveniment, Integer>
{
	@Query("SELECT e FROM Eveniment e WHERE e.locatie LIKE ?1")
	List<Eveniment> findByLocatie(String locatie);
	
	@Query("SELECT e FROM Eveniment e WHERE e.data = ?1")
	List<Eveniment> findByData(LocalDate data);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Eveniment e WHERE e.id = ?1")
    void deleteById(Long id);
}