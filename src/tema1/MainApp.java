package tema1;

import java.util.Scanner;

public class MainApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("lungime dreptunghi? = ");
        int x = scanner.nextInt();
        System.out.print("latime dreptunghi? = ");
        int y = scanner.nextInt();
        System.out.println();

        Dreptunghi dreptunghi = new Dreptunghi(x, y);
        System.out.println("~~ dreptunghi " + dreptunghi.getLungime() + "x" + dreptunghi.getLatime() + ":");
        System.out.println("arie = " + dreptunghi.getArie());
        System.out.println("perimetru = " + dreptunghi.getPerimetru());

        scanner.close();
    }
}
