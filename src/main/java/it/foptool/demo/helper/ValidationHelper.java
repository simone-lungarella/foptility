package it.foptool.demo.helper;

import java.lang.reflect.MalformedParametersException;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import it.foptool.demo.dto.XsltParamsDTO;

/**
 * Helper class to validate the parameters and the template.
 * 
 * @author Simone Lungarella
 */
public class ValidationHelper {
    
    /**
     * Validates the parameters necessary to perform the PDF generation.
     * 
     * @param params Dto containing Map of parameters to use to populate the xslt file.
     * @throws MalformedParametersException If any of given parameters is {@code null}.
     * @throws MissingParametersException If the DTO or the map is empty or {@code null}.
     */
    public static void validateParams(XsltParamsDTO params) {
        // TODO: implement this method
    }

    /**
     * Validates the json necessary to perform the PDF generation.
     * 
     * @param json Json to use to populate the xslt file.
     * @throws MissingJsonException If the json object is {@code null}.
     */
    public static void validateJson(JSONObject json) {
        // TODO: implement this method
    }

    /**
     * Validates the template necessary to perform the PDF generation.
     * 
     * @param xslt Template fop to generate the pdf from.
     * @throws MissingTemplateException If the template is {@code null} or the bytes length is 0.
     */
    public static void validateTemplate(MultipartFile xslt) {
        // TODO: implement this method
    }
}
