package com.project.translatorapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;


@RestController
@RequestMapping("/translate")
public class TranslationResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    String url = "https://translation.googleapis.com/language/translate/v2";

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/")
    public TranslationsListResponse getTranslation(@RequestBody String text) throws GeneralSecurityException, IOException {
        Translate t = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport()
                , GsonFactory.getDefaultInstance(), null)
                .setApplicationName("Translator-API")
                .build();

        Translate.Translations.List list = t.new Translations().list(
                Arrays.asList(
                        // Pass in list of strings to be translated
                        text),
                // Target language
                "ES");

        list.setKey(apiKey);
        TranslationsListResponse response = list.execute();
        for (TranslationsResource translationsResource : response.getTranslations()) {
            System.out.println(translationsResource.getTranslatedText());
        }
        return response;
    }


    // Example request body coming in from front end

//    {
//        "q": "I am a great target, thank you. Good morning",
//            "source": "en",
//            "target": "es",
//            "format": "text"
//    }





}
