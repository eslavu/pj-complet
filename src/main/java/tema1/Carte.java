package tema1;

public record Carte(String titlu, String autor, Integer an_aparitie)
{
    @Override
    public String toString() { return titlu + ", " + autor + ", " + an_aparitie; }
}