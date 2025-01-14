package tema2;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

enum Orientare
{
    LUNGIME,
    LATIME,
    ORICARE
}

/** Clasă ce reprezintă o placă de pal
 * @author Emilia Slavu
 * @since 2025
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Placa
{
    private String descriere;
    /** Getter pentru descriere
     * @return descrierea plăcii
     */
    String getDescriere() { return this.descriere; }
    /** Setter pentru descriere
     * @param descriere descrierea plăcii
     */
    void setDescriere(String descriere) { this.descriere = descriere; }

    private Integer lungime;
    /** Getter pentru lungime
     * @return lungimea plăcii
     */
    Integer getLungime() { return this.lungime; }
    /** Setter pentru lungime
     * @param lungime lungimea plăcii
     */
    void setLungime(Integer lungime) { this.lungime = lungime; }

    private Integer latime;
    /** Getter pentru lățime
     * @return lățimea plăcii
     */
    Integer getLatime() { return this.latime; }
    /** Setter pentru lățime
     * @param latime lățimea plăcii
     */
    void setLatime(Integer latime) { this.latime = latime; }

    private Orientare orientare;
    /** Getter pentru orientare (LUNGIME / LATIME / ORICARE)
     * @return orientarea plăcii (ca și enum Orientare)
     */
    Orientare getOrientare() { return this.orientare; }
    /** Setter pentru orientare (ca și enum Orientare)
     * @param orientare orientarea plăcii (LUNGIME / LATIME / ORICARE)
     */
    void setOrientare(Orientare orientare) { this.orientare = orientare; }

    private Boolean[] canturi = new Boolean[4];
    /** Getter pentru canturile plăcii
     * @return true / false pentru fiecare latură a plăcii (șir Boolean)
     */
    Boolean[] getCanturi() { return this.canturi; }
    /** Setter pentru canturile plăcii
     * @param canturi șir Boolean pentru cele patru muchii
     */
    void setCanturi(Boolean[] canturi) { this.canturi = Arrays.stream(canturi)
            .limit(4)
            .toArray(Boolean[]::new); }

    private Integer nr_bucati;
    /** Getter pentru numărul de plăci
     * @return numărul de bucăți
     */
    Integer getNr_bucati() { return this.nr_bucati; }
    /** Setter pentru numărul de plăci
     * @param nr_bucati numărul de bucăți
     */
    void setNr_bucati(Integer nr_bucati) { this.nr_bucati = nr_bucati; }

    /** Constructor cu parametri pentru corpul de mobilier
     * @param descriere descrierea plăcii
     * @param lungime lungimea plăcii
     * @param latime lățimea plăcii
     * @param orientare orientarea plăcii (LUNGIME / LATIME / ORICARE)
     * @param canturi șir Boolean pentru cele patru muchii
     * @param nr_bucati numărul de bucăți
     */
    Placa(String descriere, Integer lungime, Integer latime, Orientare orientare, Boolean[] canturi, Integer nr_bucati)
    {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }

    /** Suprascriere a metodei implicite toString
     * @return atributele plăcii (ca și un singur String)
     */
    @Override
    public String toString()
    {
        String ret = this.descriere + ", ";
        ret += this.lungime + "mm, " + this.latime + "mm, ";
        ret += "orientare: " + this.orientare + ", ";
        ret += "canturi:[ ";
        for (var ent : this.canturi) ret += ent + " ";
        ret += "], ";
        ret += "nr_bucati: " + this.nr_bucati + " bucăți";

        return ret;
    }
}