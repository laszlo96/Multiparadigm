package suhiprojects.translator;

public class sourceFile {
    private String apikey;
    private String serviceUrl;
    private String language;
    private String text;
    private int translations;


    public sourceFile(String apikey, String serviceUrl, String language, String text, int translations) {
        this.apikey = apikey;
        this.serviceUrl = serviceUrl;
        this.language = language;
        this.text = text;
        this.translations = translations;
    }

    public String getApikey() {
        return apikey;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }

    public int getTranslations() {
        return translations;
    }
}
