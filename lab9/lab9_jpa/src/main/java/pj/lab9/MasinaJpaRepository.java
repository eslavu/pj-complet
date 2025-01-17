package pj.lab9;

import java.util.List;

import org.springframework.stereotype.*;
import jakarta.persistence.*;
import jakarta.transaction.*;

@Repository
@Transactional
public class MasinaJpaRepository
{
	@PersistenceContext
	EntityManager manager;
	
	public List<Masina> findAll()
	{
		String sql = "SELECT m FROM Masina m";
		TypedQuery<Masina> query = manager.createQuery(sql, Masina.class);
		return query.getResultList();
	}
	public List<Masina> findByNr_inmatriculare(String nr_inmatriculare)
	{
		String sql = "SELECT m FROM Masina m" +
				" WHERE m.nr_inmatriculare = :x";
		TypedQuery<Masina> query = manager.createQuery(sql, Masina.class);
		query.setParameter("x", nr_inmatriculare);
		return query.getResultList();
	}
	public List<Masina> findByAn_fabricatie(Integer mod, Integer an_fabricatie)
	{
		String sql;
		
		if (mod == -2) sql = "SELECT m FROM Masina m" +
				" WHERE m.an_fabricatie < :x";
		else if (mod == -1) sql = "SELECT m FROM Masina m" +
				" WHERE m.an_fabricatie <= :x";
		else if (mod == 0) sql = "SELECT m FROM Masina m" +
				" WHERE m.an_fabricatie = :x";
		else if (mod == 1) sql = "SELECT m FROM Masina m" +
				" WHERE m.an_fabricatie >= :x";
		else sql = "SELECT m FROM Masina m" +
				" WHERE m.an_fabricatie > :x";
		
		TypedQuery<Masina> query = manager.createQuery(sql, Masina.class);
		query.setParameter("x", an_fabricatie);
		return query.getResultList();
	}
	
	public Integer countByMarca(String marca)
	{
		String sql = "SELECT m FROM Masina m" +
				" WHERE m.marca LIKE :x";
		TypedQuery<Masina> query = manager.createQuery(sql, Masina.class);
		query.setParameter("x", marca);
		return query.getResultList().size();
	}
	public Integer countByNr_km(Integer mod, Integer nr_km)
	{
		String sql;
		
		if (mod == -2) sql = "SELECT m FROM Masina m" +
				" WHERE m.nr_km < :x";
		else if (mod == -1) sql = "SELECT m FROM Masina m" +
				" WHERE m.nr_km <= :x";
		else if (mod == 0) sql = "SELECT m FROM Masina m" +
				" WHERE m.nr_km = :x";
		else if (mod == 1) sql = "SELECT m FROM Masina m" +
				" WHERE m.nr_km >= :x";
		else sql = "SELECT m FROM Masina m" +
				" WHERE m.nr_km > :x";
		
		TypedQuery<Masina> query = manager.createQuery(sql, Masina.class);
		query.setParameter("x", nr_km);
		return query.getResultList().size();
	}
	
	public Integer deleteByNr_inmatriculare(String nr_inmatriculare)
	{
		var masina = findByNr_inmatriculare(nr_inmatriculare);
		if (masina.size() > 0) manager.remove(masina.get(0));
		return masina.size();
	}
	
	public Masina insert(Masina masina)
	{ return manager.merge(masina); }
	
	public Masina update(Masina masina)
	{ return insert(masina); }
}
