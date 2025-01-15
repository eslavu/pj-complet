package tema1;
import static tema1.Json.*;

import java.util.*;
import java.util.stream.*;

public class MainApp
{
    private static final Comparator<Carte> vechime = new Comparator<>()
    {
        @Override
        public int compare(Carte ent1, Carte ent2)
        { return ent1.an_aparitie().compareTo(ent2.an_aparitie()); }
    };

    private static void meniu()
    {
        System.out.println("1) afisare colectie (inferenta tip variabile locale)");
        System.out.println("2) stergere carte din colectia Map");
        System.out.println("3) adaugare carte in colectia Map (putIfAbsent)");
        System.out.println("4) salvare colectie Map in .json");
        System.out.println("5) creare colectie Set cu cartile lui Yual Noah Harari (Stream + forEach si colectori)");
        System.out.println("6) afisare colectie Set ordonata dupa titlu (Stream + lambda + referinte la metode)");
        System.out.println("7) afisare cea mai veche carte din colectia Set (Stream + Optional)");
        System.out.println("0) iesire");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Map<Integer, Carte> carti = citireJson();
        Set<Carte> set_carti = new HashSet<>();

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
                    if (carti != null) carti.clear();
                    if (!set_carti.isEmpty()) set_carti.clear();
                    running = false;
                    break;
                case 1:
                    if (carti == null || carti.isEmpty()) System.out.println("~~ nu exista carti inregistrate!");
                    else
                    {
                        System.out.println("~~ lista completa carti:");
                        for (var ent : carti.entrySet())
                            System.out.println(ent.getKey() + ": " + ent.getValue());
                    }
                    System.out.println();
                    break;
                case 2:
                    if (carti == null || carti.isEmpty()) System.out.println("~~ nu exista carti inregistrate!");
                    else
                    {
                        Integer mod,
                                del_key = -1;
                        do
                        {
                            System.out.print("~~ stergere carte dupa? (1 = id / 2 = titlu): ");
                            mod = scanner.nextInt();
                        } while (mod != 1 && mod != 2);
                        if (mod == 1)
                        {
                            System.out.print("id de sters?: ");
                            del_key = scanner.nextInt();
                        }
                        if (mod == 2)
                        {
                            String del;
                            System.out.print("titlu de sters?: ");
                            scanner.nextLine();
                            del = scanner.nextLine();
                            for (var ent : carti.entrySet())
                                if (del.compareToIgnoreCase(ent.getValue().titlu()) == 0) del_key = ent.getKey();
                        }
                        if (carti.remove(del_key) == null)
                            System.out.println("~~ nu exista carte cu " + ((mod == 1) ? "id-ul" : "titlul") + " cautat!");
                        else System.out.println("~~ carte stearsa cu succes!");
                    }
                    System.out.println();
                    break;
                case 3:
                    var new_key = carti.keySet().stream()
                            .max(Integer::compareTo)
                            .get() + 1;
                    String titlu, autor;
                    Integer an_aparitie;
                    System.out.println("~~ adaugare cartea " + new_key + ":");
                    System.out.print("titlu carte de adaugat?: ");
                    scanner.nextLine();
                    titlu = scanner.nextLine();
                    System.out.print("autor?: ");
                    autor = scanner.nextLine();
                    System.out.print("anul aparitiei?: ");
                    an_aparitie = scanner.nextInt();
                    System.out.println();
                    if (carti.putIfAbsent(new_key, new Carte(titlu, autor, an_aparitie)) == null) System.out.println("~~ carte adaugata cu succes!");
                    else System.out.println("~~ carte deja existenta!");
                    System.out.println();
                    break;
                case 4:
                    if (carti == null || carti.isEmpty()) System.out.println("~~ nu exista carti inregistrate!");
                    else
                    {
                        scriereJson(carti);
                        System.out.println("carti.json scris cu succes!");
                    }
                    System.out.println();
                    break;
                case 5:
                    if (carti == null || carti.isEmpty()) System.out.println("~~ nu exista carti inregistrate!");
                    else
                    {
                        set_carti = carti.values().stream()
                                .filter((pred) -> (pred.autor().compareToIgnoreCase("Yuval Noah Harari") == 0))
                                .collect(Collectors.toSet());
                        if (set_carti.isEmpty()) System.out.println("~~ nu exista carti scrise de Yuval Noah Harari!");
                        else
                        {
                            System.out.println("~~ colectie Set cu cartile lui Yuval Noah Harari:");
                            set_carti.stream()
                                    .forEach(System.out::println);
                        }
                    }
                    System.out.println();
                    break;
                case 6:
                    if (set_carti.isEmpty()) System.out.println("~~ colectie Set cu cartile scrise de Yuval Noah Harari goala!");
                    else
                    {
                        System.out.println("~~ cartile lui Yuval Noah Harari in ordinea titlurilor:");
                        set_carti.stream()
                                .sorted((pred1, pred2) -> (pred1.titlu().compareTo(pred2.titlu())))
                                .forEach(System.out::println);
                    }
                    System.out.println();
                    break;
                case 7:
                    if (carti == null || carti.isEmpty()) System.out.println("~~ nu exista carti inregistrate!");
                    else
                    {
                        Optional<Map.Entry<Integer, Carte>> mini = carti.entrySet().stream()
                                .min((pred1, pred2) -> (vechime.compare(pred1.getValue(), pred2.getValue())));
                        System.out.println("~~ cartea cea mai veche:");
                        System.out.println(mini.get().getKey() + ": " + mini.get().getValue());
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
