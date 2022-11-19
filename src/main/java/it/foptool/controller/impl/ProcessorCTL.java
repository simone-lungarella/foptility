package it.foptool.controller.impl;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.foptool.controller.IProcessorCTL;
import it.foptool.exception.PDFGenerationException;
import it.foptool.helper.FOPHelper;
import it.foptool.helper.ValidationHelper;
import it.foptool.utility.FileUtility;

/**
 * Processor controller implementation.
 * 
 * @author Simone Lungarella
 */
@RestController
public class ProcessorCTL implements IProcessorCTL {

    @Override
    public ResponseEntity<byte[]> generatePdfFromJson(String json, HttpServletRequest request) {
        
        JSONObject jsonObject = new JSONObject(json);
        ValidationHelper.validateJson(jsonObject);

        final String filename =  FileUtility.getFilenameFromJson(jsonObject);
        final byte[] file = FileUtility.getFileFromInternalResources(filename + ".xslt");
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + " - " + LocalDate.now() + ".pdf" );

        final byte[] pdfFile = generatePdf(json, file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_PDF)
                .headers(httpHeaders)
                .body(pdfFile);
    }

    private byte[] generatePdf(String json, byte[] file) {
        try {
            return FOPHelper.transformJson2PDF(json, file);
        } catch (Exception e) {
            throw new PDFGenerationException("Error during PDF generation", e);
        }
    }
}
