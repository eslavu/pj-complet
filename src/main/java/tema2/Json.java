package tema2;

import java.util.*;
import java.io.*;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;

/** Clasă ce se ocupă cu prelucrarea fișierelor .json
 * @author Emilia Slavu
 * @since 2025
 */
class Json
{
    /** Metodă pentru serializare mobilier în .json
     * @param mobilier colecția List de serializat
     */
    static void scriereMobilier(List<Mobilier> mobilier)
    {
        try
        {
            File fisier_out = new File("src/main/resources/mobilier.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            mapper.writeValue(fisier_out, mobilier);
        }
        catch (IOException e) {}
    }
    /** Metodă pentru deserializare mobilier din .json
     * @return colecția List rezultată
     */
    static List<Mobilier> citireMobilier()
    {
        try
        {
            File fisier_in = new File("src/main/resources/mobilier.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            return mapper.readValue(fisier_in, new TypeReference<>(){});
        }
        catch (IOException e) { return new ArrayList<>(); }
    }

    /** Metodă pentru serializare plăci de pal în .json
     * @param placi colecția List de serializat
     */
    static void scrierePlaci(List<Placa> placi)
    {
        try
        {
            File fisier_out = new File("src/main/resources/placi.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            mapper.writeValue(fisier_out, placi);
        }
        catch (IOException e) {}
    }
    /** Metodă pentru deserializare plăci de pal din .json
     * @return colecția List rezultată
     */
    static List<Placa> citirePlaci()
    {
        try
        {
            File fisier_in = new File("src/main/resources/placi.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            return mapper.readValue(fisier_in, new TypeReference<>(){});
        }
        catch (IOException e) { return new ArrayList<>(); }
    }
}