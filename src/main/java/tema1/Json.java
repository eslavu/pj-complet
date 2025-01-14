package tema1;

import java.util.*;
import java.io.*;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

/** Clasă ce se ocupă cu prelucrarea fișierelor .json
 * @author Emilia Slavu
 * @since 2025
 */
class Json
{
    /** Metodă pentru serializare persoane în .json
     * @param persoane colecția List de serializat
     */
    public static void scrierePersoane(List<Persoana> persoane)
    {
        try
        {
            File fisier_out = new File("src/main/resources/persoane.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fisier_out, persoane);
        }
        catch (IOException e) {}
    }
    /** Metodă pentru deserializare persoane din .json
     * @return colecția List rezultată
     */
    public static List<Persoana> citirePersoane()
    {
        try
        {
            File fisier_in = new File("src/main/resources/persoane.json");
            return new ObjectMapper()
                    .readValue(fisier_in, new TypeReference<>(){});
        }
        catch (IOException e) { return new ArrayList<>(); }
    }

    /** Metodă pentru serializare perechi de numere în .json
     * @param perechi_numere colecția List de serializat
     */
    public static void scrierePerechiNumere(List<PerecheNumere> perechi_numere)
    {
        try
        {
            File fisier_out = new File("src/main/resources/perechi_numere.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fisier_out, perechi_numere);
        }
        catch (IOException e) {}
    }
    /** Metodă pentru deserializare perechi de numere din .json
     * @return colecția List rezultată
     */
    public static List<PerecheNumere> citirePerechiNumere()
    {
        try
        {
            File fisier_in = new File("src/main/resources/perechi_numere.json");
            return new ObjectMapper()
                    .readValue(fisier_in, new TypeReference<>(){});
        }
        catch (IOException e) { return new ArrayList<>(); }
    }
}