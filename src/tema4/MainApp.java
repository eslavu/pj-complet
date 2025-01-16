package tema4;

import java.util.*;

public class MainApp
{
    private static Character controlCnp(String cnp)
    {
        final String control = "279146358279";
        String ch_control, ch_cnp;
        Integer suma = 0;
        try
        {
            for (Integer i = 0; i < 12; i++)
            {
                ch_control = String.valueOf(control.charAt(i));
                ch_cnp = String.valueOf(cnp.charAt(i));
                suma += Integer.parseInt(ch_control) * Integer.parseInt(ch_cnp);
            }
            suma %= 11;
            if (suma == 10) suma = 0;
            return Character.forDigit(suma, 10);
        }
        catch (Exception exception) { return 'n'; }
    }
    private static Boolean verificareCnp(String cnp)
    {
        final String sex = "1256";
        if (cnp.length() != 13) return false;
        for (Integer i = 0; i < cnp.length(); i++)
            if (Character.isAlphabetic(Character.valueOf(cnp.charAt(i)))) return false;
        if (!sex.contains(String.valueOf(cnp.charAt(0)))) return false;
        if (controlCnp(cnp) != cnp.charAt(12)) return false;
        return true;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String cnp, nume;
        Integer nr;

        do
        {
            System.out.print("nr de persoane?: ");
            nr = scanner.nextInt();
        } while (nr <= 0);
        System.out.println();
        Persoana[] persoane = new Persoana[nr];
        for (Integer i = 0; i < nr; i++)
        {
            do
            {
                System.out.print("cnp " + (i + 1) + "?: ");
                cnp = scanner.next();
            } while (!verificareCnp(cnp));
            do
            {
                System.out.print("nume persoana " + (i + 1) + "?: ");
                nume = scanner.next();
            } while (nume.isBlank());
            persoane[i] = new Persoana(nume, cnp);
            System.out.println();
        }
        System.out.println("~~ lista persoane:");
        for (Integer i = 0; i < nr; i++) System.out.println(persoane[i].toString());
    }
}
