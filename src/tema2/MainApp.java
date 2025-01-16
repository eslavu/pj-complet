package tema2;

import java.io.*;
import java.util.*;

public class MainApp
{
    private static Boolean scrieVersuri(List<Vers> lista, PrintStream print, String grup)
    {
        try
        {
            Random rng = new Random();
            rng.setSeed(System.currentTimeMillis());
            for (Vers ent : lista)
            {
                print.print(ent.getText() + " ");
                if (ent.hasTerminatie(grup)) print.print("*");
                print.print("[ ");
                print.print(ent.getNrCuvinte() + " cuvinte + " + ent.getNrVocale() + " vocale");
                print.println(" ]");
            }
        }
        catch (Exception exception) { return false; }
        return true;
    }

    public static void main(String[] args)
    {
        try
        {
            File fisier_in = new File("cantec_in.txt"),
                    fisier_out = new File("cantec_out.txt");
            Scanner scanner = new Scanner(fisier_in);
            PrintStream print = new PrintStream(fisier_out);

            List<Vers> versuri = new ArrayList<>();
            String grup;
            System.out.print("grupare de litere?: ");
            grup = new Scanner(System.in).nextLine();
            while (scanner.hasNext())
                versuri.add(new Vers(scanner.nextLine()));
            System.out.println("~~ vector de versuri creat cu succes!");
            if (scrieVersuri(versuri, print, grup)) System.out.println("~~ fisier cantec_out.txt scris cu succes!");
            else System.out.println("~~ fisierul cantec_in.txt nu a putut fi scris!");

            versuri.clear();
            scanner.close();
            print.close();
        }
        catch (FileNotFoundException exception) { System.out.println("~~ fisierul cantec_in.txt nu poate fi accesat!"); }
    }
}