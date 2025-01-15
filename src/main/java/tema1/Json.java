package tema1;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.*;

public class Json
{
    public static void scriereJson(Map<Integer, Carte> carti)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File fisier_json = new File("src/main/resources/carti.json");

            mapper.writeValue(fisier_json, carti);
        }
        catch (Exception exception) { exception.printStackTrace(); }
    }

    public static Map<Integer, Carte> citireJson()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File fisier_json = new File("src/main/resources/carti.json");

            Map<Integer, Carte> carti = mapper.readValue(fisier_json, new TypeReference<>(){});
            return carti;
        }
        catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }
}