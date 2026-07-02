package com.vhre.auth_boilerplate.core.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.Normalizer;

@JsonComponent
public class GlobalStringNormalizer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getValueAsString();

        if (value == null) return null;

        // Remove leading and trailing spaces
        String cleanString = value.trim();

        // Remove accents using Unicode normalization
        String withoutAccents = Normalizer.normalize(cleanString, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Return in uppercase
        return withoutAccents.toUpperCase();
    }
}
