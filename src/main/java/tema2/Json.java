package tema2;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.*;

class Json
{
    static void scriere(Set<InstrumentMuzical> carti)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File fisier_json = new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            mapper.writeValue(fisier_json, carti);
            System.out.println(fisier_json.getName() + " scris cu succes!");
        }
        catch (Exception exception) { System.out.println("eroare la scrierea in .json!"); }
    }

    static Set<InstrumentMuzical> citire()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File fisier_json = new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            var value = mapper.readValue(fisier_json, new TypeReference<>(){});
            System.out.println("instrumente.json citit cu succes!");
            return (Set<InstrumentMuzical>) value;
        }
        catch (Exception exception)
        {
            System.out.println("eroare la citirea din .json!");
            return new HashSet<>();
        }
    }
}