package problema;
import problema.ExceptiaMea.*;

import java.time.LocalDate;
import java.util.*;
import java.sql.*;

class MainApp
{
    private static void meniu()
    {
        System.out.println("1) adaugare persoana in tabela [persoane]");
        System.out.println("2) adaugare excursie in tabela [excursii] cu verificare a existentei persoanei apartinatoare");
        System.out.println("3) afisare persoane si fiecare excusie a fiecareia");
        System.out.println("4) afisare excursiile unei anumite persoane");
        System.out.println("5) afisare persoane care au vizitat o anumita destinatie");
        System.out.println("6) afisare persoane care au facut excursii pe parcursul unui anumit an");
        System.out.println("7) stergerea unei excursii");
        System.out.println("8) stergerea unei persoane (inclusiv excursiile acesteia)");
        System.out.println("0) iesire");
        System.out.println();
    }

    private static void adaugare(Connection connection, String tabela, Scanner scanner)
    {
        try
        {
            String sql = "INSERT INTO " + tabela + " VALUES(";
            if (tabela.equals("persoane"))
            {
                String nume;
                Integer varsta = 0;
                Boolean valid;

                do
                {
                    valid = true;
                    System.out.print("nume persoana? = ");
                    scanner.nextLine();
                    nume = scanner.nextLine();
                    if (nume.isBlank()) valid = false;
                } while (!valid);
                System.out.println();

                do
                {
                    valid = true;
                    try
                    {
                        System.out.print("varsta? = ");
                        varsta = scanner.nextInt();
                        if (varsta < 0) throw new ExceptieVarsta("(varsta invalida!)");
                    }
                    catch (ExceptieVarsta exceptie)
                    {
                        valid = false;
                        System.out.println(exceptie.getMessage());
                    }
                } while (!valid);

                sql += "NULL, " + "'" + nume + "', " + varsta + ")";
            }
            if (tabela.equals("excursii"))
            {
                String destinatie;
                Integer id_persoana,
                        an = LocalDate.now().getYear();
                Boolean valid;

                Statement test = connection.createStatement();
                ResultSet rs_test;
                System.out.print("id persoanei? = ");
                id_persoana = scanner.nextInt();
                rs_test = test.executeQuery("SELECT * FROM persoane");
                valid = false;
                while (rs_test.next()) if (rs_test.getInt("id") == id_persoana) valid = true;
                if (!valid)
                {
                    System.out.println("~~ persoana cu id-ul " + id_persoana + " nu exista!");
                    return;
                }

                do
                {
                    valid = true;
                    System.out.print("destinatie? = ");
                    scanner.nextLine();
                    destinatie = scanner.nextLine();
                    if (destinatie.isBlank()) valid = false;
                } while (!valid);
                System.out.println();

                do
                {
                    valid = true;
                    try
                    {
                        System.out.print("anul excursiei? = ");
                        an = scanner.nextInt();
                        if (an < 1900) throw new ExceptieAnExcursie("(an invalid!)");
                    }
                    catch (ExceptieAnExcursie exceptie)
                    {
                        valid = false;
                        System.out.println(exceptie.getMessage());
                    }
                } while (!valid);

                sql += id_persoana + ", " + "NULL, " + "'" + destinatie + "', " + an + ")";
            }

            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("~~ tabela actualizata cu succes!");
        }
        catch (SQLException exception) { System.out.println("~~ eroare la adaugare in tabela!"); }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        try
        {
            String url = "jdbc:mysql://localhost:3306/pj-lab8";
            Connection connection = DriverManager.getConnection(url, "root", "");
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM ?");
            ResultSet rs_persoana = statement.executeQuery("SELECT * FROM persoane"),
                    rs_excursie = statement.executeQuery("SELECT * FROM excursii");
            Boolean gasit1,
                    gasit2;

            Boolean running = true;
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
                        running = false;
                        break;
                    case 1:
                        adaugare(connection, "persoane", scanner);
                        break;
                    case 2:
                        adaugare(connection, "excursii", scanner);
                        break;
                    case 3:
                        statement = connection.createStatement();
                        rs_persoana = statement.executeQuery("SELECT *" +
                                " FROM persoane");
                        Integer persoana;
                        gasit1 = true;
                        gasit2 = false;
                        while (rs_persoana.next())
                        {
                            gasit1 = true;
                            persoana = rs_persoana.getInt("id");
                            System.out.println("PERSOANA " + rs_persoana.getInt("id") + ": " + rs_persoana.getString("nume") + ", " + rs_persoana.getInt("varsta") + " ani");

                            gasit2 = false;
                            statement = connection.createStatement();
                            rs_excursie = statement.executeQuery("SELECT *" +
                                    " FROM excursii");
                            while (rs_excursie.next())
                                if (rs_excursie.getInt("id_persoana") == persoana)
                                {
                                    if (!gasit2)
                                    {
                                        gasit2 = true;
                                        System.out.println("EXCURSII:");
                                    }
                                    System.out.println(rs_excursie.getInt("id_excursie") + ") " + rs_excursie.getString("destinatie") + ", " + rs_excursie.getInt("an"));
                                }
                            System.out.println();
                        }
                        if (!gasit1) System.out.println("~~ nu exista persoane inregistrate in baza de date!");
                        System.out.println();
                        break;
                    case 4:
                        Integer id;
                        do
                        {
                            System.out.print("id-ul persoanei cautate? = ");
                            id = scanner.nextInt();
                        } while (id <= 0);
                        System.out.println();

                        statement = connection.createStatement();
                        rs_persoana = statement.executeQuery("SELECT * FROM persoane");
                        gasit1 = false;
                        gasit2 = false;
                        while (rs_persoana.next())
                            if (rs_persoana.getInt("id") == id)
                            {
                                gasit1 = true;
                                System.out.println("EXCURSIILE PERSOANEI " + id + " (" + rs_persoana.getString("nume") + "):");
                                break;
                            }
                        if (gasit1)
                        {
                            gasit2 = false;
                            statement = connection.createStatement();
                            rs_excursie = statement.executeQuery("SELECT *" +
                                    " FROM excursii");
                            while (rs_excursie.next())
                                if (rs_excursie.getInt("id_persoana") == id)
                                {
                                    gasit2 = true;
                                    System.out.println(rs_excursie.getInt("id_excursie") + ") " + rs_excursie.getString("destinatie") + ", " + rs_excursie.getInt("an"));
                                }
                            if (!gasit2) System.out.println("~~ persoana cautata nu are excursii inregistrate!");
                            System.out.println();
                        }
                        else System.out.println("~~ persoana cautata nu exista in baza de date!");
                        System.out.println();
                        break;
                    case 5:
                        String destinatie;
                        List<Integer> iduri = new ArrayList<>();
                        do
                        {
                            System.out.print("destinatie de cautat? = ");
                            scanner.nextLine();
                            destinatie = scanner.nextLine();
                        } while (destinatie.isBlank());
                        System.out.println();

                        gasit1 = false;
                        statement = connection.createStatement();
                        rs_excursie = statement.executeQuery("SELECT * FROM excursii" +
                                " WHERE destinatie LIKE '" + destinatie + "'");
                        while (rs_excursie.next())
                        {
                            gasit1 = true;
                            iduri.add(rs_excursie.getInt("id_persoana"));
                        }

                        if (gasit1)
                        {
                            for (var ent : iduri)
                            {
                                statement = connection.createStatement();
                                rs_persoana = statement.executeQuery("SELECT * FROM persoane" +
                                        " WHERE id = " + ent);
                                rs_persoana.next();
                                System.out.println("PERSOANA " + rs_persoana.getInt("id") + ": " + rs_persoana.getString("nume") + ", " + rs_persoana.getInt("varsta") + " ani");
                            }
                            System.out.println();
                        }
                        else System.out.println("~~ nu exista excursii desfasurate in destinatia cautata!");
                        System.out.println();
                        iduri.clear();
                        break;
                    case 6:
                        Integer an = LocalDate.now().getYear();
                        iduri = new ArrayList<>();
                        gasit1 = true;
                        do
                        {
                            gasit1 = true;
                            try
                            {
                                System.out.print("anul de cautat? = ");
                                an = scanner.nextInt();
                                if (an < 1900) throw new ExceptieAnExcursie("(an invalid!)");
                            }
                            catch (ExceptieAnExcursie exceptie)
                            {
                                gasit1 = false;
                                System.out.println(exceptie.getMessage());
                            }
                        } while (!gasit1);
                        System.out.println();

                        gasit1 = false;
                        statement = connection.createStatement();
                        rs_excursie = statement.executeQuery("SELECT * FROM excursii" +
                                " WHERE an = " + an);
                        while (rs_excursie.next())
                        {
                            gasit1 = true;
                            iduri.add(rs_excursie.getInt("id_persoana"));
                        }

                        if (gasit1)
                        {
                            for (var ent : iduri)
                            {
                                statement = connection.createStatement();
                                rs_persoana = statement.executeQuery("SELECT * FROM persoane" +
                                        " WHERE id = " + ent);
                                rs_persoana.next();
                                System.out.println("PERSOANA " + rs_persoana.getInt("id") + ": " + rs_persoana.getString("nume") + ", " + rs_persoana.getInt("varsta") + " ani");
                            }
                            System.out.println();
                        }
                        else System.out.println("~~ nu exista excursii desfasurate pe parcursul anului cautat!");
                        System.out.println();
                        iduri.clear();
                        break;
                    case 7:
                        do
                        {
                            System.out.print("id-ul excursiei de sters? = ");
                            id = scanner.nextInt();
                        } while (id <= 0);
                        System.out.println();

                        statement = connection.createStatement();
                        Integer count = statement.executeUpdate("DELETE FROM excursii" +
                                " WHERE id_excursie = " + id);
                        if (count > 0) System.out.println("~~ excursie stearsa cu succes!");
                        else System.out.println("~~ excursia cautata nu exista in baza de date!");
                        System.out.println();
                        break;
                    case 8:
                        do
                        {
                            System.out.print("id-ul persoanei cautate? = ");
                            id = scanner.nextInt();
                        } while (id <= 0);
                        System.out.println();

                        statement = connection.createStatement();
                        count = statement.executeUpdate("DELETE FROM persoane" +
                                " WHERE id = " + id);
                        if (count > 0) System.out.println("~~ persoana stearsa cu succes!");
                        else System.out.println("~~ persoana cautata nu exista in baza de date!");
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

            rs_excursie.close();
            rs_persoana.close();
            statement.close();
            connection.close();
            scanner.close();
        }
        catch (SQLException exception) { System.out.println("~~ eroare la prelucrarea bazei de date!"); exception.printStackTrace(); }

        scanner.close();
    }
}