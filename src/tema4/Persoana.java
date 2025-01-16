package tema4;

import java.time.*;
import java.time.format.*;

public class Persoana
{
    private String nume, cnp;

    public Persoana(String nume, String cnp)
    {
        this.nume = nume;
        this.cnp = cnp;
    }

    @Override
    public String toString()
    {
        return nume + ", " + cnp + ", " + this.getVarsta() + " ani";
    }

    public String getNume() { return this.nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getCnp() { return this.cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }

    public Integer getVarsta()
    {
        LocalDate local = LocalDate.now(),
                nastere;
        local.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Integer varsta = -1;
        Boolean nou = this.cnp.charAt(0) == '5' || this.cnp.charAt(0) == '6';
        try
        {
            String data,
                    zi, luna, an;
            if (nou) an = "20";
            else an = "19";
            an += this.cnp.substring(1, 3);
            luna = this.cnp.substring(3, 5);
            zi = this.cnp.substring(5, 7);
            data = zi + "." + luna + "." + an;
            nastere = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        catch (Exception exception) { return -1; }

        varsta = local.getYear() - nastere.getYear();
        if (local.getDayOfYear() < nastere.getDayOfYear()) varsta--;
        return varsta;
    }
}
