package tema2;

import java.util.*;

/** Programul principal
 * @author Emilia Slavu
 * @since 2025
 */
public class MainApp
{
    /** Metodă pentru afișarea caracteristicilor plăcilor care compun o anumită piesă de mobilier
     * @param mobilier colecția List de corpuri de mobilier
     */
    private static void metodaPlaci(Scanner scanner, List<Mobilier> mobilier)
    {
        if (mobilier == null || mobilier.isEmpty()) System.out.println("~~ lista vida!");
        else
        {
            String nume;
            Boolean gasit = false;

            System.out.println();
            System.out.println("mobilier de cautat?:");
            nume = scanner.nextLine().toUpperCase();

            System.out.println();
            for (var ent : mobilier)
                if (ent.getNume().toUpperCase().compareTo(nume) == 0)
                {
                    if (!gasit)
                    {
                        gasit = true;
                        System.out.println("placile mobilierului cautat:");
                    }
                    System.out.println("[");
                    for (var ent2 : ent.getPlaci()) System.out.println(ent2);
                    System.out.println("]");
                }
            if (!gasit) System.out.println("~~ mobilierul cautat nu exista!");
        }
    }
    /** Metodă pentru afișarea numărulului estimativ de coli de pal necesare pentru realizarea unui anumit corp de mobilă
     * @param mobilier colecția List de corpuri de mobilier
     */
    private static void metodaCalcul(Scanner scanner, List<Mobilier> mobilier)
    {
        //2800 x 2070 mm
        if (mobilier == null || mobilier.isEmpty()) System.out.println("~~ lista vida!");
        else
        {
            String nume;
            Boolean gasit = false;
            Float arie_pal = 2800f * 2070f,
                    arie = 0f;

            System.out.println();
            System.out.println("mobilier de cautat?:");
            nume = scanner.nextLine().toUpperCase();

            System.out.println();
            for (var ent : mobilier)
                if (ent.getNume().toUpperCase().compareTo(nume) == 0)
                {
                    gasit = true;
                    for (var ent2 : ent.getPlaci()) arie += (ent2.getLungime() * ent2.getLatime()) * ent2.getNr_bucati();
                }
            if (gasit) System.out.println("sunt necesare " + Math.ceil(arie/arie_pal) + " coli de pal");
            else System.out.println("~~ mobilierul cautat nu exista!");
        }
    }

    /** Partea executabilă a codului
     */
    public static void main(String[] args)
    {
        List<Mobilier> mobilier = new ArrayList<>();
        List<Placa> placi = new ArrayList<>();

        placi.add(new Placa("usa", 50, 200, Orientare.LATIME, new Boolean[]{false, true, true, true}, 3));
        placi.add(new Placa("raft fix", 1000, 400, Orientare.ORICARE, new Boolean[]{true, true, false, false}, 5));
        mobilier.add(new Mobilier("chestie dubioasa", placi));

        Json.scriereMobilier(mobilier);
        System.out.println("~~ fisier .json scris cu succes!");
        System.out.println("citirea din json iar nu merge :(");

        System.out.println();
        System.out.println("~~ colectie List intreaga:");
        for (var ent : mobilier) System.out.println(ent);

        Scanner scanner = new Scanner(System.in);
        metodaPlaci(scanner, mobilier);
        metodaCalcul(scanner, mobilier);
        scanner.close();
    }
}