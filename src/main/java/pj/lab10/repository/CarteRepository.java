package pj.lab10.repository;
import pj.lab10.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import jakarta.transaction.*;

public interface CarteRepository extends JpaRepository<Carte, Integer>
{
	@Query("SELECT c FROM Carte c WHERE c.isbn LIKE ?1")
	List<Carte> findByIsbn(String isbn);
	
	@Query("SELECT c FROM Carte c WHERE c.autor LIKE ?1")
	List<Carte> findByAutor(String autor);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Carte c WHERE c.isbn LIKE ?1")
    Integer deleteByIsbn(String isbn);
}