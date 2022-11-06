package it.foptool.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import it.foptool.demo.dto.XsltParamsDTO;
import it.foptool.demo.utility.FileUtility;
import lombok.extern.slf4j.Slf4j;

/**
 * PDF Creator controller test class.
 * 
 * @author Simone Lungarella
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class XsltProcessorTest {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    int port;

    String getBaseUrl() {
        return "http://localhost:" + port + "/foptility";
    }

    @Test
    void givenInvalidParams_whenXsltToPdf_thenThrowMalformedParametersException() {
        XsltParamsDTO requestBody = null; // Testing null safety
        byte[] xsltBytes = FileUtility.getFileFromInternalResources("xslt" + File.separator + "test.xslt");
        assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            xsltToPdfFromMap(requestBody, xsltBytes);
        }, "Calling the endpoint with invalid xslt should throw a BadRequest Exception");
    }

    @Test
    void givenInvalidXslt_whenXsltToPdfFromMap_thenThrowMissingParametersException() {
        Map<String, String> map = new HashMap<>();
        map.put("author", "Simone Lungarella");

        XsltParamsDTO requestBody = new XsltParamsDTO();
        requestBody.setParams(map);

        byte[] xsltBytes = null; // Testing null safety
        assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            xsltToPdfFromMap(requestBody, xsltBytes);
        }, "Calling the endpoint with invalid parameters should throw a BadRequest Exception");
    }

    @Test
    void givenValidParams_whenXsltToPdf_thenShouldReturnCreated() {
        Map<String, String> map = new HashMap<>();
        map.put("author", "Simone Lungarella");

        XsltParamsDTO requestBody = new XsltParamsDTO();
        requestBody.setParams(map);

        byte[] xsltBytes = FileUtility.getFileFromInternalResources("xslt" + File.separator + "test.xslt");
        ResponseEntity<byte[]> response = xsltToPdfFromMap(requestBody, xsltBytes);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "If the operation succeded, the status code should be CREATED");
    }

    @Test
    void givenInvalidJson_whenXsltToPdf_thenThrowMalformedParametersException() {
        String json = null; // Testing null safety
        byte[] xsltBytes = FileUtility.getFileFromInternalResources("xslt" + File.separator + "test.xslt");
        assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            xsltToPdfFromJson(json, xsltBytes);
        }, "Calling the endpoint with invalid json should throw a BadRequest Exception");
    }

    @Test
    void givenInvalidXslt_whenXsltToPdfFromJson_thenThrowMissingParametersException() {
        String json = "{\"test\":\"test\"}";
        byte[] xsltBytes = null; // Testing null safety
        assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            xsltToPdfFromJson(json, xsltBytes);
        }, "Calling the endpoint with invalid xslt should throw a BadRequest Exception");
    }

    ResponseEntity<byte[]> xsltToPdfFromMap(XsltParamsDTO requestBody, byte[] xslt) {

        String url = getBaseUrl() + "/transform";

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ByteArrayResource fileAsResource = null;

        if (xslt != null) {
            fileAsResource = new ByteArrayResource(xslt) {
                @Override
                public String getFilename() {
                    return "xslt";
                }
            };
        } else {
            log.warn("The file is null, it will be passes as null to the endpoint");
        }

        map.add("xslt", fileAsResource);
        map.add("requestBody", requestBody);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(url, requestEntity, byte[].class);
    }

    ResponseEntity<byte[]> xsltToPdfFromJson(String json, byte[] xslt) {
        String url = getBaseUrl() + "/transform/json";

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ByteArrayResource fileAsResource = new ByteArrayResource(xslt) {
            @Override
            public String getFilename() {
                return "xslt";
            }
        };

        map.add("xslt", fileAsResource);
        map.add("json", json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(url, requestEntity, byte[].class);
    }

}
