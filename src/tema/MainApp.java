package tema;

import java.util.*;
import java.io.*;

/** Programul principal
 * @author Emilia Slavu
 * @since 2024
 */
public class MainApp
{
    /** Afișare meniu interactiv
     */
    private static void meniu()
    {
        System.out.println("1) afisare colectie List completa echipamente");
        System.out.println("2) afisare imprimante");
        System.out.println("3) afisare copiatoare");
        System.out.println("4) afisare sisteme de calcul");
        System.out.println("5) modificare stare echipament");
        System.out.println("6) modificare mod tiparire imprimanta");
        System.out.println("7) modificare format copiator");
        System.out.println("8) modificare sistem de operare");
        System.out.println("9) afisare echipamente vandute");
        System.out.println("10) demonstratie serializare/deserializare colectie List");
        System.out.println("0) iesire");
        System.out.println();
        System.out.print("optiunea ta: ");
    }

    /** Metodă pentru citirea listei de echipamente dintr-un fișier .csv
     * @param file_path calea către fișierul .cvs
     * @return colecția List cu echipamente (sau listă goală în cazul în care nu există fișierul sau acesta este gol)
     */
    private static List<Echipament> citireEchipamente(String file_path)
    {
        List<Echipament> echipamente = new ArrayList<>();
        try
        {
            File fisier_in = new File(file_path);
            Scanner scanner = new Scanner(fisier_in);
            while (scanner.hasNextLine())
            {
                try
                {
                    String line = scanner.nextLine();
                    String[] split = line.split(";");

                    String denumire = split[0];
                    Integer nr_inv = Integer.parseInt(split[1]);
                    Double pret = Double.parseDouble(split[2]);
                    String zona_mag = split[3],
                            stare = split[4];

                    TipEchipament tip = TipEchipament.valueOf(split[5].toUpperCase()
                            .replace(' ', '_').replace('-', '_'));
                    switch(tip)
                    {
                        case IMPRIMANTA:
                            Integer ppm = Integer.parseInt(split[6]);
                            String rezolutie = split[7];
                            Integer p_car = Integer.parseInt(split[8]);
                            String mod_tiparire = split[9];
                            echipamente.add(new Echipament.Imprimanta(denumire, nr_inv, pret, zona_mag, stare, ppm, rezolutie, p_car, mod_tiparire));
                            break;
                        case COPIATOR:
                            Integer p_ton = Integer.parseInt(split[6]);
                            String format = split[7];
                            echipamente.add(new Echipament.Copiator(denumire, nr_inv, pret, zona_mag, stare, p_ton, format));
                            break;
                        case SISTEM_DE_CALCUL:
                            String tip_mon = split[6];
                            Double vit_proc = Double.parseDouble(split[7]);
                            Integer c_hdd = Integer.parseInt(split[8]);
                            String so = split[9];
                            echipamente.add(new Echipament.SistemCalcul(denumire, nr_inv, pret, zona_mag, stare, tip_mon, vit_proc, c_hdd, so));
                            break;
                        case null:
                        default: break;
                    }
                }
                catch (Exception exception) {}
            }
            System.out.println("~~ colectie List cu echipamente citita cu succes!");
        }
        catch (FileNotFoundException exception) { System.out.println("~~ fisierul " + file_path + " nu poate fi accesat!"); }

        return echipamente;
    }

    /** Metodă pentru afișarea pe ecran ale echipamentelor de un anumit tip
     * @param echipamente colecția List din care să se afișeze
     * @param tip tipul de echipament de căutat
     */
    private static void afisareTip(List<Echipament> echipamente, TipEchipament tip)
    {
        Boolean gasit = false;
        for (var ent : echipamente)
            if (ent.getTipEnum() == tip)
            {
                if (!gasit)
                {
                    gasit = true;
                    System.out.println("~~ echipamentele de tip " + ent.getTip().toUpperCase() + " din magazin:");
                }
                System.out.println(ent.toStringOut());
            }
        if (!gasit) System.out.println("~~ nu exista echipamente de tip " + tip.toString().replace('_', ' ') + " in magazin!");
    }

    /** Partea executabilă a codului
     */
    public static void main(String[] args)
    {
        List<Echipament> echipamente = citireEchipamente("src/tema/echipamente.txt");
        System.out.println();

        Boolean running = true;
        Scanner scanner = new Scanner(System.in);
        Integer optiune;
        while (running)
        {
            meniu();
            optiune = scanner.nextInt();
            System.out.println();

            switch (optiune)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        System.out.println("~~ colectia List cu echipamentele din magazin:");
                        for (var ent : echipamente) System.out.println(ent.toStringOut());
                    }
                    System.out.println();
                    break;
                case 2: case 3: case 4:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        if (optiune == 2) afisareTip(echipamente, TipEchipament.IMPRIMANTA);
                        if (optiune == 3) afisareTip(echipamente, TipEchipament.COPIATOR);
                        if (optiune == 4) afisareTip(echipamente, TipEchipament.SISTEM_DE_CALCUL);
                    }
                    System.out.println();
                    break;
                case 5:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        String denumire;
                        Integer stare;
                        System.out.print("echipament de cautat?: ");
                        scanner.nextLine();
                        denumire = scanner.nextLine();
                        System.out.println();
                        do
                        {
                            System.out.print("stare noua? (0 = ACHIZITIONAT / 1 = EXPUS / 2 = VANDUT): ");
                            stare = scanner.nextInt();
                        } while (stare != 0 && stare != 1 && stare != 2);

                        Boolean gasit_denumire = false,
                                egal_stare = false;
                        Echipament gasit = null;
                        for (var ent : echipamente)
                            if (ent.getDenumire().equals(denumire))
                            {
                                gasit_denumire = true;
                                if (ent.getStareEnum() == Stare.values()[stare]) egal_stare = true;
                                gasit = ent;
                                break;
                            }
                        System.out.println();
                        if (!gasit_denumire) System.out.println("~~ nu exista echipamentul cautat!");
                        else if (egal_stare) System.out.println("~~ nu este necesara schimbarea starii echipamentului!");
                        else
                        {
                            gasit.setStare(Stare.values()[stare].toString());
                            System.out.println("~~ stare echipament schimbata cu succes!");
                        }
                    }
                    System.out.println();
                    break;
                case 6:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        String denumire;
                        Integer mod;
                        System.out.print("imprimanta de cautat?: ");
                        scanner.nextLine();
                        denumire = scanner.nextLine();
                        System.out.println();
                        do
                        {
                            System.out.print("mod de tiparire nou? (0 = COLOR / 1 = ALB-NEGRU): ");
                            mod = scanner.nextInt();
                        } while (mod != 0 && mod != 1);

                        Boolean gasit_denumire = false,
                                egal_mod = false;
                        Echipament.Imprimanta gasit = null;
                        for (var ent : echipamente)
                            if (ent.getTipEnum() == TipEchipament.IMPRIMANTA && ent.getDenumire().equals(denumire))
                            {
                                gasit_denumire = true;
                                gasit = (Echipament.Imprimanta)ent;
                                if (gasit.getMod_tiparireEnum() == ModTiparire.values()[mod]) egal_mod = true;
                                break;
                            }
                        System.out.println();
                        if (!gasit_denumire) System.out.println("~~ nu exista imprimanta cautata!");
                        else if (egal_mod) System.out.println("~~ nu este necesara schimbarea modului de tiparire!");
                        else
                        {
                            gasit.setMod_tiparire(ModTiparire.values()[mod].toString());
                            System.out.println("~~ mod de tiparire schimbat cu succes!");
                        }
                    }
                    System.out.println();
                    break;
                case 7:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        String denumire;
                        Integer format;
                        System.out.print("copiator de cautat?: ");
                        scanner.nextLine();
                        denumire = scanner.nextLine();
                        System.out.println();
                        do
                        {
                            System.out.print("format nou? (0 = A3 / 1 = A4): ");
                            format = scanner.nextInt();
                        } while (format != 0 && format != 1);

                        Boolean gasit_denumire = false,
                                egal_format = false;
                        Echipament.Copiator gasit = null;
                        for (var ent : echipamente)
                            if (ent.getTipEnum() == TipEchipament.COPIATOR && ent.getDenumire().equals(denumire))
                            {
                                gasit_denumire = true;
                                gasit = (Echipament.Copiator)ent;
                                if (gasit.getFormatEnum() == FormatCopiere.values()[format]) egal_format = true;
                                break;
                            }
                        System.out.println();
                        if (!gasit_denumire) System.out.println("~~ nu exista copiatorul cautat!");
                        else if (egal_format) System.out.println("~~ nu este necesara schimbarea formatului copiatorului!");
                        else
                        {
                            gasit.setFormat(FormatCopiere.values()[format].toString());
                            System.out.println("~~ formatul copiatorului schimbat cu succes!");
                        }
                    }
                    System.out.println();
                    break;
                case 8:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        String denumire;
                        Integer so;
                        System.out.print("sistem de calcul de cautat?: ");
                        scanner.nextLine();
                        denumire = scanner.nextLine();
                        System.out.println();
                        do
                        {
                            System.out.print("sistem de operare nou? (0 = WINDOWS / 1 = LINUX): ");
                            so = scanner.nextInt();
                        } while (so != 0 && so != 1);

                        Boolean gasit_denumire = false,
                                egal_so = false;
                        Echipament.SistemCalcul gasit = null;
                        for (var ent : echipamente)
                            if (ent.getTipEnum() == TipEchipament.SISTEM_DE_CALCUL && ent.getDenumire().equals(denumire))
                            {
                                gasit_denumire = true;
                                gasit = (Echipament.SistemCalcul)ent;
                                if (gasit.getSoEnum() == SistemOperare.values()[so]) egal_so = true;
                                break;
                            }
                        System.out.println();
                        if (!gasit_denumire) System.out.println("~~ nu exista sistemul de calcul cautat!");
                        else if (egal_so) System.out.println("~~ nu este necesara schimbarea sistemului de operare!");
                        else
                        {
                            gasit.setSo(SistemOperare.values()[so].toString());
                            System.out.println("~~ sistem de operare schimbat cu succes!");
                        }
                    }
                    System.out.println();
                    break;
                case 9:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        Boolean gasit = false;
                        for (var ent : echipamente)
                            if (ent.getStareEnum() == Stare.VANDUT)
                            {
                                if (!gasit)
                                {
                                    gasit = true;
                                    System.out.println("~~ echipamentele vandute ale magazinului:");
                                }
                                System.out.println(ent.toStringOut());
                            }
                        if (!gasit) System.out.println("~~ nu au fost vandute echipamente in magazin!");
                    }
                    System.out.println();
                    break;
                case 10:
                    if (echipamente == null || echipamente.isEmpty()) System.out.println("~~ nu exista echipamente in magazin!");
                    else
                    {
                        Boolean ok = true;
                        System.out.println("~~ demonstratie serializare colectie List:");
                        try
                        {
                            Echipament.serializareLista(echipamente, "src/tema/echipamente.bin");
                            System.out.println("colectie List serializata cu succes!");
                        }
                        catch (Exception exception)
                        {
                            System.out.println("eroare la serializare!");
                            ok = false;
                        }
                        System.out.println();

                        if (ok)
                        {
                            var aux = echipamente;
                            System.out.println("~~ deserializare:");
                            try
                            {
                                echipamente.clear();
                                echipamente = Echipament.deserializareLista("src/tema/echipamente.bin");
                                System.out.println("lista echipamente actualizata cu succes!");
                            }
                            catch (Exception exception)
                            {
                                System.out.println("eroare la deserializare!");
                                echipamente = aux;
                            }
                        }
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("optiune invalida!");
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

        if (echipamente != null && !echipamente.isEmpty()) echipamente.clear();
        scanner.close();
    }
}
