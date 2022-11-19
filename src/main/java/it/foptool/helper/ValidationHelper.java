package it.foptool.helper;

import org.json.JSONObject;

import it.foptool.exception.MissingJsonException;

/**
 * Helper class to validate the parameters and the template.
 * 
 * @author Simone Lungarella
 */
public class ValidationHelper {

    /**
     * Validates the json necessary to perform the PDF generation.
     * 
     * @param json Json to use to populate the xslt file.
     * @throws MissingJsonException If the json object is {@code null}.
     */
    public static void validateJson(JSONObject json) {
        if (json == null) {
            throw new MissingJsonException("Missing json object");
        }
    }

}
