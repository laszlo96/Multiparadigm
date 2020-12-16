package suhiprojects.translator;

import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.*;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class Translator
{
    Authenticator authenticator;
    private static LanguageTranslator service;

    public Translator(String apikey, String serviceUrl)
    {
        this.authenticator = new IamAuthenticator(apikey);
        this.service = new LanguageTranslator("2018-05-01", authenticator);

        this.service.setServiceUrl(serviceUrl);
    }

    static String translate(String text, String source, String target)
    {
        if (source.equals(target))
            return text;
        TranslateOptions translateOptions = new TranslateOptions.Builder()
                .addText(text)
                .source(source)
                .target(target)
                .build();
        TranslationResult result = service.translate(translateOptions).execute().getResult();
        List<Translation> translations = result.getTranslations();

        return translations.get(0).getTranslation();
    }

    protected static List<String> supportedLanguages()
    {
        List<String> supportedLanguages = new ArrayList<>();
        Languages languages = service.listLanguages().execute().getResult();

        JSONObject json = new JSONObject(languages);
        JSONArray arrayLanguages = json.getJSONArray("languages");


        for (int i = 0; i < arrayLanguages.length(); i++)
        {
            JSONObject obj = arrayLanguages.getJSONObject(i);
            if (obj.getBoolean("supportedAsSource") && obj.getBoolean("supportedAsTarget")
                    && (obj.getString("language").equals("es") || obj.getString("language").equals("de")
                    || obj.getString("language").equals("hu") ||obj.getString("language").equals("en")
                    || obj.getString("language").equals("it") || obj.getString("language").equals("tr")))
            {
                supportedLanguages.add(obj.getString("language"));
            }
        }

        return supportedLanguages;
    }
}
