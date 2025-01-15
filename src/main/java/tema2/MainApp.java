package tema2;

import java.util.*;

class MainApp
{
    private static void meniu()
    {
        System.out.println("1) inserare initiala (3 chitari + 3 tobe)");
        System.out.println("2) salvare colectie Set in .json");
        System.out.println("3) citire colectie Set din .json");
        System.out.println("4) afisare colectie instrumente");
        System.out.println("5) verificare daca colectia Set permite duplicate");
        System.out.println("6) stergere instrumente cu pret peste 3000 de lei (removeIf())");
        System.out.println("7) afisare chitari (Stream API + instanceof)");
        System.out.println("8) afisare seturi tobe (Stream API + getClass())");
        System.out.println("9) afisare chitara cu cele mai multe corzi (Stream API, lambda, referinte la metode, Optional)");
        System.out.println("10) afisare seturi tobe acustice in ordinea numarului de tobe (Stream API, lambda, referinte la metode)");
        System.out.println("0) iesire");
        System.out.println();
    }

    private static InstrumentMuzical adaugare(Scanner scanner, Integer tip)
    {
        InstrumentMuzical add = null;

        String producator;
        Float pret;
        do
        {
            System.out.print("producator? = ");
            producator = scanner.next();
        } while (producator.isBlank());
        do
        {
            System.out.print("pret? = ");
            pret = scanner.nextFloat();
        } while (pret <= 0);

        if (tip == 1) // chitara
        {
            Integer tip_chitara,
                    nr_corzi;
            do
            {
                System.out.print("tip chitara? (0 = electrica / 1 = acustica / 2 = clasica) = ");
                tip_chitara = scanner.nextInt();
            } while (tip_chitara < 0 || tip_chitara > 2);
            do
            {
                System.out.print("nr corzi? = ");
                nr_corzi = scanner.nextInt();
            } while (nr_corzi <= 0);
            add = new InstrumentMuzical.Chitara(producator, pret, tip_chitara, nr_corzi);
        }
        if (tip == 2) // set tobe
        {
            Integer tip_tobe,
                    nr_tobe, nr_cinele;

            do
            {
                System.out.print("tip set tobe? (0 = electrice / 1 = acustice) = ");
                tip_tobe = scanner.nextInt();
            } while (tip_tobe < 0 || tip_tobe > 1);
            do
            {
                System.out.print("nr tobe? = ");
                nr_tobe = scanner.nextInt();
            } while (nr_tobe <= 0);
            do
            {
                System.out.print("nr cinele? = ");
                nr_cinele = scanner.nextInt();
            } while (nr_cinele <= 0);
            add = new InstrumentMuzical.SetTobe(producator, pret, tip_tobe, nr_tobe, nr_cinele);
        }

        return add;
    }

    public static void main(String[] args)
    {
        Set<InstrumentMuzical> instrumente = new HashSet<>();
        InstrumentMuzical add;

        Boolean running = true;
        Scanner scanner = new Scanner(System.in);
        Integer optiune;
        while (running)
        {
            meniu();
            System.out.print("~~ optiunea ta: ");
            optiune = scanner.nextInt();
            System.out.println();

            switch (optiune)
            {
                case 0:
                    if (instrumente != null) instrumente.clear();
                    running = false;
                    break;
                case 1:
                    /* adaugare clasica
                    for (Integer i = 0; i < 3; i++)
                    {
                        System.out.println("~~ chitara " + (i + 1) + ":");
                        add = adaugare(scanner, 1);
                        if (add != null) instrumente.add(add);
                        System.out.println();
                    }
                    for (Integer i = 0; i < 3; i++)
                    {
                        System.out.println("~~ set tobe " + (i + 1) + ":");
                        add = adaugare(scanner, 2);
                        if (add != null) instrumente.add(add);
                        System.out.println();
                    }
                    */
                    instrumente.add(new InstrumentMuzical.Chitara("a", 3000f, 0, 6));
                    instrumente.add(new InstrumentMuzical.Chitara("b", 1800f, 1, 7));
                    instrumente.add(new InstrumentMuzical.Chitara("c", 4000f, 2, 6));
                    instrumente.add(new InstrumentMuzical.SetTobe("a", 5000f, 0, 5, 2));
                    instrumente.add(new InstrumentMuzical.SetTobe("b", 4000f, 1, 7, 5));
                    instrumente.add(new InstrumentMuzical.SetTobe("c", 8000f, 1, 6, 4));
                    System.out.println("~~ instrumente adaugate cu succes!");
                    System.out.println();
                    break;
                case 2:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else Json.scriere(instrumente);
                    System.out.println();
                    break;
                case 3:
                    instrumente = Json.citire();
                    System.out.println();
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ Set complet instrumente:");
                        for (var ent : instrumente) System.out.println(ent);
                    }
                    System.out.println();
                    break;
                case 4:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ Set complet instrumente:");
                        for (var ent : instrumente) System.out.println(ent);
                    }
                    System.out.println();
                    break;
                case 5:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        add = instrumente.stream().
                                findFirst().get();
                        if (instrumente.add(add))
                        {
                            System.out.println("~~ colectia Set permite duplicate!");

                            System.out.println("~~ impiedicare duplicate prin metoda equals(): ");
                            Boolean insert = true;
                            for (var ent : instrumente)
                                if (add.equals(ent))
                                {
                                    insert = false;
                                    break;
                                }
                            if (insert) System.out.println("elementul exista deja in colectia Set!");
                            else System.out.println("elementul poate fi adaugat in colectia Set!");
                        }
                        else System.out.println("~~ colectia Set NU permite duplicate!");
                    }
                    System.out.println();
                    break;
                case 6:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        instrumente.removeIf((pred) -> (pred.getPret()>3000f));
                        System.out.println("~~ colectie Set instrumente actualizata!");
                    }
                    System.out.println();
                    break;
                case 7:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ chitarile din colectia Set:");
                        instrumente.stream()
                                .filter((pred) -> (pred instanceof InstrumentMuzical.Chitara))
                                .forEach(System.out::println);
                    }
                    System.out.println();
                    break;
                case 8:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ seturile de tobe din colectia Set:");
                        instrumente.stream()
                                .filter((pred) -> (pred.getClass() == InstrumentMuzical.SetTobe.class))
                                .forEach(System.out::println);
                    }
                    System.out.println();
                    break;
                case 9:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ seturile de tobe din colectia Set:");
                        var chitara = instrumente.stream()
                                .filter((pred) -> (pred instanceof InstrumentMuzical.Chitara))
                                .max((pred1, pred2) ->
                                        (((InstrumentMuzical.Chitara)pred1).getNr_corzi().compareTo(((InstrumentMuzical.Chitara)pred2).getNr_corzi())));
                        chitara.ifPresentOrElse(System.out::println,
                                () -> { System.out.println("nu exista chitari in Set!"); });
                    }
                    System.out.println();
                    break;
                case 10:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ seturile de tobe acustice din colectia Set:");
                        instrumente.stream()
                                .filter((pred) -> (pred.getClass() == InstrumentMuzical.SetTobe.class))
                                .filter((pred) -> (((InstrumentMuzical.SetTobe)pred).getTip_tobe() == 1))
                                .sorted((pred1, pred2) ->
                                        (((InstrumentMuzical.SetTobe)pred1).getNr_tobe().compareTo(((InstrumentMuzical.SetTobe)pred2).getNr_tobe())))
                                .forEach(System.out::println);
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
                catch (Exception exception) {}
            }
        }
        scanner.close();
    }
}