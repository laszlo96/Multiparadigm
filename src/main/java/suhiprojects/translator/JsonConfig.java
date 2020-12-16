package suhiprojects.translator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonConfig
{

    public static sourceFile readJson(String fileName)
    {
        JSONParser parser = new JSONParser();
        sourceFile source = null;

        try (FileReader reader = new FileReader(fileName))
        {
            JSONObject obj = (JSONObject) parser.parse(reader);

            String apikey = (String) obj.get("apikey");
            String serviceUrl = (String) obj.get("serviceUrl");
            String language = (String) obj.get("language");
            String text = (String) obj.get("text");
            int translations = ((Long) obj.get("translations")).intValue();
            source = new sourceFile(apikey,serviceUrl,language,text,translations);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return source;
    }
}
