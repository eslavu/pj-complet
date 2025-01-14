package tema2;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

/** Clasă ce reprezintă un corp de mobilier
 * @author Emilia Slavu
 * @since 2025
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Mobilier
{
    private String nume;
    /** Getter pentru nume
     * @return numele mobilierului
     */
    String getNume() { return this.nume; }
    /** Setter pentru nume
     * @param nume numele mobilierului
     */
    void setNume(String nume) { this.nume = nume; }

    private List<Placa> placi = new ArrayList<>();
    /** Getter pentru lista de plăci
     * @return colecția List de plăci
     */
    List<Placa> getPlaci() { return this.placi; }
    /** Setter pentru lista de plăci
     * @param placi lista de plăci ce compun corpul de mobilier
     */
    void setPlaci(List<Placa> placi) { this.placi = placi; }

    /** Constructor cu parametri pentru corpul de mobilier
     * @param nume numele mobilierului
     * @param placi lista de plăci ce îl compun
     */
    Mobilier(String nume, List<Placa> placi)
    {
        this.nume = nume;
        this.placi = placi;
    }

    /** Suprascriere a metodei implicite toString
     * @return numele mobilierului și atributele plăcilor acestuia
     */
    @Override
    public String toString()
    {
        String ret = "MOBILIER: " + this.nume + "\n";
        ret += "PLACI:" + "\n" + "[" + "\n";
        for (var ent : this.placi) ret += ent + "\n";
        ret += "]";

        return ret;
    }
}