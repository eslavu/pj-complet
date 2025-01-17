package pj.lab9_jdbc;

public class Masina
{
	private String nr_inmatriculare;
	public String getNr_inmatriculare() { return this.nr_inmatriculare; }
	
	private String marca;
	public String getMarca() { return this.marca; }
	
	private Integer an_fabricatie;
	public Integer getAn_fabricatie() { return this.an_fabricatie; }
	
	private String culoare;
	public String getCuloare() { return this.culoare; }
	
	private Integer nr_km;
	public Integer getNr_km() { return this.nr_km; }
	
	public Masina() {}
	public Masina(String nr_inmatriculare, String marca, Integer an_fabricatie, String culoare, Integer nr_km)
	{
		super();
		this.nr_inmatriculare = nr_inmatriculare;
		this.marca = marca;
		this.an_fabricatie = an_fabricatie;
		this.culoare = culoare;
		this.nr_km = nr_km;
	}
	
	@Override
	public String toString()
	{ return this.nr_inmatriculare + ", " + this.marca + ", " + this.an_fabricatie + ", " + this.culoare + ", " + this.nr_km; }
}