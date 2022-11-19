package it.foptool;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import it.foptool.controller.impl.ProcessorCTL;

/**
 * PDF Creator controller test class.
 * 
 * @author Simone Lungarella
 */
@Disabled
@ComponentScan
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(ProcessorCTL.class)
class XsltProcessorTest {

    @Autowired
    MockMvc mvc;

    @Test
    void givenInvalidJson_whenXsltToPdf_thenThrowBadRequestException() throws Exception {
        String json = ""; // Testing null safety
        mvc.perform(post("/foptility/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

}
