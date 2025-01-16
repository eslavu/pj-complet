package tema1;

import java.util.*;
import java.io.*;

public class MainApp
{
    public static void main(String[] args)
    {
        try
        {
            File fisier_in;
            fisier_in = new File("in.txt");
            Scanner scanner = new Scanner(fisier_in);

            List<Parabola> parabole = new ArrayList<>();
            while (scanner.hasNext())
            {
                try
                {
                    String line = scanner.nextLine();
                    String[] parametri = line.split(" ");
                    Integer a = Integer.parseInt(parametri[0]),
                            b = Integer.parseInt(parametri[1]),
                            c = Integer.parseInt(parametri[2]);
                    parabole.add(new Parabola(a, b, c));
                }
                catch (NumberFormatException exception) {}
            }
            System.out.println("~~ lista parabole citita!");
            System.out.println();

            if (parabole == null || parabole.isEmpty()) System.out.println("~~ lista parabole goala!");
            else
                for (Integer i = 0; i < parabole.size(); i++)
                {
                    System.out.println("~~ parabola " + i + ":");
                    System.out.println(parabole.get(i));
                    System.out.println("cu varful (" + parabole.get(i).getVarf()[0] + ", " + parabole.get(i).getVarf()[1] + ")");
                    System.out.println();
                }


            if (parabole.size() >= 2)
            {
                System.out.println("lungimea segmentului dintre varful primei si ultimei parabole = " + Parabola.getDistanta(parabole.getFirst(), parabole.getLast()));
                Double[] mijloc = parabole.getFirst().getMijloc(parabole.getLast());
                System.out.println("mijlocul segmentului: (" + mijloc[0] + ", " + mijloc[1] + ")");
            }

            if (parabole != null && !parabole.isEmpty()) parabole.clear();
            scanner.close();
        }
        catch (FileNotFoundException exception) { System.out.println("~~ fisierul in.txt nu poate fi accesat!"); }
    }
}
