package suhiprojects.translator;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

public class Main
{
    private static final String fileName = "source.json";
    private static String randomLanguage(String sourceLanguage, List<String> languages)
    {
        Random rnd = new Random();
        String targetLanguage = sourceLanguage;

        while (targetLanguage.equals(sourceLanguage))
        {
            targetLanguage = languages.get(rnd.nextInt(languages.size()));
        }
        return targetLanguage;
    }
    public static String bouncing(String text, String sourceLanguage, int translations)
    {
        List<String> languages = Translator.supportedLanguages();

        System.out.println(text);

        String translatedText = text;
        String source = sourceLanguage;
        String target = null;

        for (int i = 0; i < translations; i++)
        {
            target = randomLanguage(source, languages);
            translatedText = Translator.translate(translatedText, source, target);

            String resultMessage = MessageFormat.format("[{0}] {1}", target, translatedText);
            System.out.println(resultMessage);
            source = target;
        }
        translatedText = Translator.translate(translatedText, source, sourceLanguage);

        return translatedText;
    }

    public static void main(String[] args)
    {
        try {
            sourceFile source = JsonConfig.readJson(fileName);
            Translator translator = new Translator(source.getApikey(), source.getServiceUrl());
            String translatedText = bouncing(source.getText(), source.getLanguage(), source.getTranslations());
            System.out.println(translatedText);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}