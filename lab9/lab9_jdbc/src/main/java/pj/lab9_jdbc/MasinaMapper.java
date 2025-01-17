package pj.lab9_jdbc;

import java.sql.*;
import org.springframework.jdbc.core.*;

public class MasinaMapper implements RowMapper<Masina>
{
	@Override
	public Masina mapRow(ResultSet rs, int row_num) throws SQLException
	{ return new Masina(rs.getString("nr_inmatriculare"),
			rs.getString("marca"), rs.getInt("an_fabricatie"),
			rs.getString("culoare"),
			rs.getInt("nr_km")); }
}