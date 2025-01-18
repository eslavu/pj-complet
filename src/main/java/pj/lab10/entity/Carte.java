package pj.lab10.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="carti")
public class Carte
{
	@Id
	private String isbn;
	
	@Column
	private String titlu;
	
	@Column
	private String autor;
	
	public Carte(String isbn, String titlu, String autor)
	{
		super();
		this.isbn = isbn;
		this.titlu = titlu;
		this.autor = autor;
	}
	
	@Override
	public String toString()
	{ return this.isbn + ", " + this.titlu + ", " + this.autor; }
}