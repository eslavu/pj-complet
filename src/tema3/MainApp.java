package tema3;

import java.io.*;
import java.util.*;

public class MainApp
{
    private static String inserare(Scanner scanner, String path)
    {
        try
        {
            File file_in = new File(path);
            Scanner scanner_file = new Scanner(file_in);
            StringBuilder builder;

            String sir, insert;
            Integer pozitie;
            sir = scanner_file.nextLine();
            builder = new StringBuilder(sir);
            System.out.println("sir de caractere original: " + sir);
            System.out.print("sir de caractere de inserat?: ");
            insert = scanner.nextLine();
            do
            {
                System.out.print("de inserat la pozitia?: ");
                pozitie = scanner.nextInt();
                if (pozitie < 0) System.out.println("~~ pozitie invalida! (nr negativ)");
                if (pozitie > sir.length()) System.out.println("~~ pozitie invalida! (nr mai mare decat lungimea sirului original!");
            } while (pozitie < 0 || pozitie > sir.length());
            System.out.println();
            sir = builder.insert(pozitie, insert).toString();
            System.out.println("sir nou: " + sir);

            scanner_file.close();
            return sir;
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("~~ fisierul " + path + " nu poate fi accesat!");
            return "";
        }
    }
    private static String stergere(Scanner scanner, String sir)
    {
        if (sir.isEmpty() || sir.isBlank())
        {
            System.out.println("~~ nu exista caractere de sters! (sir gol/vid)");
            return "";
        }

        StringBuilder builder = new StringBuilder(sir);

        String sir_nou;
        Integer start, end;
        do
        {
            System.out.print("pozitie de inceput pentru a sterge caractere?: ");
            start = scanner.nextInt();
            if (start < 0) System.out.println("~~ pozitie invalida! (nr negativ)");
            if (start >= sir.length()) System.out.println("~~ pozitie invalida! (nr mai mare decat lungimea sirului original!");
        } while (start < 0 || start >= sir.length());
        do
        {
            System.out.print("lungimea sirului de sters?: ");
            end = scanner.nextInt();
            if (end <= 0) System.out.println("~~ lungime invalida! (nr negativ/nul)");
            if (end > sir.length() - start) System.out.println("~~ lungime invalida! (lungimea sirului original depasita)!");
        } while (end <= 0 || end > sir.length() - start);
        end += start;
        System.out.println();
        sir_nou = builder.delete(start, end).toString();
        System.out.println("sir nou: " + sir_nou);

        return sir_nou;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String sir;

        System.out.println("~~ inserare caractere:");
        sir = inserare(scanner, "string.txt");
        System.out.println();
        System.out.println("~~ stergere caractere:");
        sir = stergere(scanner, sir);

        scanner.close();
    }
}
