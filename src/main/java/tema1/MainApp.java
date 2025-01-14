package tema1;

import java.util.*;

/** Programul principal
 * @author Emilia Slavu
 * @since 2025
 */
public class MainApp
{
    /** Partea executabilă a codului
     */
    public static void main(String[] args)
    {
        List<Persoana> persoane = Json.citirePersoane();
        List<PerecheNumere> perechi_numere = Json.citirePerechiNumere();

        persoane.add(new Persoana("Maria", 33));
        perechi_numere.add(new PerecheNumere(1, 2));
        Json.scrierePersoane(persoane);
        Json.scrierePerechiNumere(perechi_numere);

        System.out.println(persoane);
        for (Persoana ent : persoane) System.out.println(ent);
        System.out.println();
        for (PerecheNumere ent : perechi_numere) System.out.println(ent);
    }
}