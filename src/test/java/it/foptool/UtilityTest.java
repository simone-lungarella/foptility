package it.foptool;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.foptool.exception.MissingJsonException;
import it.foptool.helper.ValidationHelper;

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
    void jsonValidationTest() throws JSONException  {
        
        assertThrows(MissingJsonException.class, () -> {
            ValidationHelper.validateJson(null);
        }, "Valdation of null json should throw a MissingJsonException");
        
        JSONObject json = new JSONObject();
        json.put("param", "value");

        assertDoesNotThrow(() -> {
            ValidationHelper.validateJson(json);
        }, "Validation of json should not throw any exception");
    }

}
