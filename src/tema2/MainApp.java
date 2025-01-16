package tema2;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class MainApp
{
    private static void meniu()
    {
        System.out.println("1) afisare colectie List de produse");
        System.out.println("2) afisare produse expirate");
        System.out.println("3) vanzare produs");
        System.out.println("4) afisare produse cu pret minim");
        System.out.println("5) salvare in fisier produse cu cantitate mai mica decat una data");
        System.out.println("0) iesire");
        System.out.println();
        System.out.print("~~ optiunea ta: ");
    }

    public static void main(String[] args)
    {
        try
        {
            File fisier_in = new File("produse.csv");
            Scanner scanner_fisier = new Scanner(fisier_in);
            Scanner scanner_tastatura = new Scanner(System.in);

            List<Produs> produse = new ArrayList<>();
            while (scanner_fisier.hasNextLine())
            {
                try
                {
                    String line = scanner_fisier.nextLine();
                    String[] split = line.split(", ");

                    String denumire = split[0];
                    Double pret = Double.parseDouble(split[1]);
                    Integer cantitate = Integer.parseInt(split[2]);
                    LocalDate data_expirare = LocalDate.parse(split[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    produse.add(new Produs(denumire, pret, cantitate, data_expirare));
                }
                catch (Exception exception) {}
            }
            System.out.println("~~ colectie List cu produse citita cu succes!");
            System.out.println();

            Boolean running = true;
            Integer optiune;
            while (running)
            {
                meniu();
                optiune = scanner_tastatura.nextInt();
                System.out.println();
                switch (optiune)
                {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        if (produse == null || produse.isEmpty()) System.out.println("~~ nu exista produse in magazin!");
                        else
                        {
                            System.out.println("~~ colectie List cu produse:");
                            for (var ent : produse) System.out.println(ent);
                        }
                        System.out.println();
                        break;
                    case 2:
                        if (produse == null || produse.isEmpty()) System.out.println("~~ nu exista produse in magazin!");
                        else
                        {
                            Boolean gasit = false;
                            for (var ent : produse)
                                if (ent.getData_expirare().compareTo(LocalDate.now()) <= 0)
                                {
                                    if (!gasit)
                                    {
                                        System.out.println("~~ produse expirate:");
                                        gasit = true;
                                    }
                                    System.out.println(ent);
                                }
                            if (!gasit) System.out.println("~~ nu exista produse expirate!");
                        }
                        System.out.println();
                        break;
                    case 3:
                        if (produse == null || produse.isEmpty()) System.out.println("~~ nu exista produse in magazin!");
                        else
                        {
                            String denumire_vandut;
                            Integer cantitate_vandut;
                            System.out.print("produs de vandut?: ");
                            scanner_tastatura.nextLine();
                            denumire_vandut = scanner_tastatura.nextLine();
                            System.out.println();
                            do
                            {
                                System.out.print("cantitate de vandut?: ");
                                cantitate_vandut = scanner_tastatura.nextInt();
                            } while (cantitate_vandut <= 0);

                            Boolean gasit_denumire = false,
                                    gasit_cantitate = false;
                            Produs vandut = null;
                            Integer i;
                            for (i = 0; i < produse.size(); i++)
                                if (produse.get(i).getDenumire().equals(denumire_vandut))
                                {
                                    gasit_denumire = true;
                                    vandut = produse.get(i);
                                    if (vandut.getCantitate().compareTo(cantitate_vandut) >= 0) gasit_cantitate = true;
                                    break;
                                }
                            System.out.println();
                            if (!gasit_denumire) System.out.println("~~ nu exista produsul cautat!");
                            else if (!gasit_cantitate) System.out.println("~~ cantitate de vandut prea mare!");
                            else
                            {
                                Produs.setIncasari(Produs.getIncasari() + (cantitate_vandut * vandut.getPret()));
                                vandut.setCantitate(vandut.getCantitate() - cantitate_vandut);
                                produse.set(i, vandut);
                                if (vandut.getCantitate() == 0) produse.remove(vandut);
                                System.out.println("~~ produs vandut cu succes!");
                                System.out.println("~~ incasari curente = " + Produs.getIncasari() + " lei");
                            }
                        }
                        System.out.println();
                        break;
                    case 4:
                        if (produse == null || produse.isEmpty()) System.out.println("~~ nu exista produse in magazin!");
                        else
                        {
                            Double minim = Double.MAX_VALUE;
                            System.out.println("~~ produs(e) cu pret minim:");
                            for (var ent : produse)
                                if (ent.getPret() < minim) minim = ent.getPret();
                            for (var ent : produse)
                                if (ent.getPret().equals(minim)) System.out.println(ent);
                        }
                        System.out.println();
                        break;
                    case 5:
                        if (produse == null || produse.isEmpty()) System.out.println("~~ nu exista produse in magazin!");
                        else
                        {
                            Integer cautat;
                            do
                            {
                                System.out.print("cantitate de cautat?: ");
                                cautat = scanner_tastatura.nextInt();
                            } while (cautat <= 0);

                            File fisier_out = new File("cantitatemica.csv");
                            PrintStream print = new PrintStream(fisier_out);
                            Boolean gasit = false;
                            for (var ent : produse)
                                if (ent.getCantitate().compareTo(cautat) < 0)
                                {
                                    gasit = true;
                                    print.println(ent.toCsv());
                                }
                            if (gasit) System.out.println("~~ produs(e) sub " + cautat + " bucati salvat(e) cu succes in fisierul " + fisier_out.getName() + "!");
                            else System.out.println("~~ nu exista produse sub " + cautat + " bucati!");
                            print.close();
                        }
                        System.out.println();
                        break;
                    default:
                        System.out.println("~~ optiune invalida!");
                        break;
                }
                if (running)
                {
                    try
                    {
                        System.out.print("(apasati ENTER pentru a continua)");
                        System.in.read();
                        System.out.println();
                    }
                    catch (IOException exception) {}
                }
            }

            if (produse != null && !produse.isEmpty()) produse.clear();
            scanner_fisier.close();
            scanner_tastatura.close();
        }
        catch (FileNotFoundException exception) { System.out.println("~~ fisierul produse.csv nu poate fi accesat!"); }
    }
}