package pj.lab9_jdbc;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

@Repository
public class MasinaDao
{
	@Autowired
	JdbcTemplate jdbc_template;
	
	public List<Masina> findAll()
	{
		String sql = "SELECT * FROM masini";
		return jdbc_template.query(sql, new MasinaMapper());
	}
	public List<Masina> findByNr_inmatriculare(String nr_inmatriculare)
	{
		String sql = "SELECT * FROM masini" +
				" WHERE nr_inmatriculare LIKE ?";
		return jdbc_template.query(sql, new MasinaMapper(), nr_inmatriculare);
	}
	public List<Masina> findByAn_fabricatie(Integer mod, Integer an_fabricatie)
	{
		String sql;
		
		if (mod == -2) sql = "SELECT * FROM masini" +
				" WHERE an_fabricatie < ?";
		else if (mod == -1) sql = "SELECT * FROM masini" +
				" WHERE an_fabricatie <= ?";
		else if (mod == 0) sql = "SELECT * FROM masini" +
				" WHERE an_fabricatie = ?";
		else if (mod == 1) sql = "SELECT * FROM masini" +
				" WHERE an_fabricatie >= ?";
		else sql = "SELECT * FROM masini" +
				" WHERE an_fabricatie > ?";
		
		return jdbc_template.query(sql, new MasinaMapper(), an_fabricatie);
	}
	
	public Integer countByMarca(String marca)
	{
		String sql = "SELECT * FROM masini" +
				" WHERE marca LIKE ?";
		return jdbc_template.query(sql, new MasinaMapper(), marca).size();
	}
	public Integer countByNr_km(Integer mod, Integer nr_km)
	{
		String sql;
		
		if (mod == -2) sql = "SELECT * FROM masini" +
				" WHERE nr_km < ?";
		else if (mod == -1) sql = "SELECT * FROM masini" +
				" WHERE nr_km <= ?";
		else if (mod == 0) sql = "SELECT * FROM masini" +
				" WHERE nr_km = ?";
		else if (mod == 1) sql = "SELECT * FROM masini" +
				" WHERE nr_km >= ?";
		else sql = "SELECT * FROM masini" +
				" WHERE nr_km > ?";
		
		return jdbc_template.query(sql, new MasinaMapper(), nr_km).size();
	}
	
	public Integer deleteByNr_inmatriculare(String nr_inmatriculare)
	{
		String sql = "DELETE FROM masini" +
				" WHERE nr_inmatriculare LIKE ?";
		return jdbc_template.update(sql, nr_inmatriculare);
	}
	
	public Integer insert(Masina masina)
	{
		String sql = "INSERT INTO masini VALUES(?,?,?,?,?)";
		return jdbc_template.update(sql, masina.getNr_inmatriculare(), masina.getMarca(), masina.getAn_fabricatie(), masina.getCuloare(), masina.getNr_km());
	}
	
	public int update(Masina masina)
	{
		String sql = "UPDATE masini" +
				" SET marca = ?, an_fabricatie = ?, culoare = ?, nr_km = ?" +
				" WHERE nr_inmatriculare = ?";
		return jdbc_template.update(sql, masina.getMarca(), masina.getAn_fabricatie(), masina.getCuloare(), masina.getNr_km(), masina.getNr_inmatriculare());
	}
}
