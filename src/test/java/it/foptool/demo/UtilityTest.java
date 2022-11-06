package it.foptool.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import it.foptool.demo.dto.XsltParamsDTO;
import it.foptool.demo.helper.ValidationHelper;
import it.foptool.demo.utility.FileUtility;

/**
 * Utilty test class.
 * 
 * @author Simone Lungarella
 */
@SpringBootTest
@ActiveProfiles("test")
class UtilityTest {
    
    final static String FILENAME = "test_file.pdf";

    @Test
    void givenAListOfParamsThatContainsFilename_ShouldReturnTheFilename() {
        Map<String, String> map = new HashMap<>();
        map.put("filename", FILENAME);

        assertTrue(FileUtility.getFilename(map).contains(FILENAME), "If the map contains a filename, it should be returned");
        
        map = new HashMap<>();
        assertFalse(FileUtility.getFilename(map).contains(FILENAME), "If the map does not contains the filename, a default name should be returned");
    }

    @Test
    void paramValidationTest()  {
        // TODO: Removed commented code when the exceptions are implemented

        // assertThrows(MissingParametersException.class, () -> {
        //     ValidationHelper.validateParams(null);
        // }, "Valdation of null params should throw a MissingParametersException");
        
        XsltParamsDTO requestDto = new XsltParamsDTO();
        
        // assertThrows(MalformedParametersException.class, () -> {
        //     ValidationHelper.validateParams(requestDto);
        // }, "Validation of empty params should throw a MalformedParametersException");
        
        Map<String, String> params = new HashMap<>();
        params.put("param", "value");
        requestDto.setParams(params);

        assertDoesNotThrow(() -> {
            ValidationHelper.validateParams(requestDto);
        }, "Validation of params should not throw any exception");
    }

    @Test
    void jsonValidationTest() throws JSONException  {
        // TODO: Removed commented code when the exception is implemented

        // assertThrows(MissingJsonException.class, () -> {
        //     ValidationHelper.validateJson(null);
        // }, "Valdation of null json should throw a MissingJsonException");
        
        JSONObject json = new JSONObject();
        json.put("param", "value");

        assertDoesNotThrow(() -> {
            ValidationHelper.validateJson(json);
        }, "Validation of json should not throw any exception");
    }

    @Test
    void fileValidationTest() {
        // TODO: Removed commented code when the exception is implemented

        // assertThrows(MissingTemplateException.class, () -> {
        //     ValidationHelper.validateTemplate(null);
        // }, "Valdation of null template should throw a MissingTemplateException");

        // assertThrows(MissingTemplateException.class, () -> {
        //     ValidationHelper.validateTemplate(new MockMultipartFile("test", new byte[0]));
        // }, "Valdation of empty template should throw a MissingTemplateException");

        assertDoesNotThrow(() -> {
            ValidationHelper.validateTemplate(new MockMultipartFile("test", "test".getBytes()));
        }, "Validation of template should not throw any exception");

    }
}
