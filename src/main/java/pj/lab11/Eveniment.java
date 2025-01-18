package pj.lab11;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name="evenimente")
public class Eveniment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String denumire;
	
	@Column
	private String locatie;
	
	@Column
	private LocalDate data;
	
	@Column
	private LocalTime timp;
	
	@Column
	private Float pret_bilet;
	
	public Eveniment() {}
	public Eveniment(String denumire, String locatie, LocalDate data, LocalTime timp, Float pret_bilet)
	{
		super();
		this.denumire = denumire;
		this.locatie = locatie;
		this.data = data;
		this.timp = timp;
		this.pret_bilet = pret_bilet;
	}
	
	@Override
	public String toString()
	{ return this.id + ", " + this.denumire + ", " + this.locatie + ", " + this.data + ", " + this.timp + ", " + this.pret_bilet; }
}
