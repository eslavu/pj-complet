package tema1;

import java.io.*;
import java.util.*;

public class MainApp
{
    public static final Integer NR_JUDETE = 41;
    public static void main(String[] args)
    {
        try
        {
            File fisier_in = new File("judete_in.txt");
            Scanner scanner = new Scanner(fisier_in);

            String[] judete = new String[NR_JUDETE + 1];
            Integer nr_judete = -1;
            while (scanner.hasNext())
            {
                nr_judete++;
                judete[nr_judete] = scanner.next().toUpperCase();
            }
            Arrays.sort(judete);
            System.out.println("~~ lista judete sortata cu succes!");
            System.out.println();
            scanner.close();

            Integer pozitie_find = -1;
            scanner = new Scanner(System.in);
            System.out.print("judet de cautat?: ");
            String find = scanner.nextLine();
            pozitie_find = Arrays.binarySearch(judete, find.toUpperCase());
            if (pozitie_find < 0) System.out.println("~~ judetul nu poate fi gasit! (ati tastat numele corect?)");
            else System.out.println("~~ judetul " + find + " se afla pe pozitia " + pozitie_find + "!");
            scanner.close();
        }
        catch (FileNotFoundException exception) { System.out.println("~~ fisierul judete_in.txt nu poate fi accesat!"); }
    }
}
