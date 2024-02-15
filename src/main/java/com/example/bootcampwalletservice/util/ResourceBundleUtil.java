package com.example.bootcampwalletservice.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class ResourceBundleUtil {

    private Map<String, String> locales = new HashMap<>() {{
        put("fa", "IR");
        put("en", "US");
    }};

    public String getMessage(String code, String language) {
        String s = locales.get(language);
        if (s == null) {
            s = "IR";
            language = "fa";
        }

        Locale locale = new Locale(language, s);
        ResourceBundle bundle = ResourceBundle.getBundle("exception" + locale);

        String message = "";
        return bundle.getString(code);
    }

}
