package it.foptool.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.foptool.demo.utility.FileUtility;

/**
 * Utilty test class.
 * 
 * @author Simone Lungarella
 */
@SpringBootTest
@ActiveProfiles("test")
class UtilityTest {
    
    @Test
    void givenAListOfParamsThatContainsFilename_ShouldReturnTheFilename() {
        String FILENAME = "test.pdf";
        Map<String, String> map = new HashMap<>();
        map.put("filename", FILENAME);

        assertTrue(FileUtility.getFilename(map).contains(FILENAME));
    }

//     @Test
//     void test()  {
//         XsltParamsDTO params = new XsltParamsDTO();

//         // ValidationHelper.validateParams(null);
//     }
}
