package it.foptool.demo.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.foptool.demo.controller.IProcessorCTL;
import it.foptool.demo.dto.XsltParamsDTO;
import it.foptool.demo.helper.FOPHelper;
import it.foptool.demo.helper.ValidationHelper;
import it.foptool.demo.utility.FileUtility;

/**
 * Processor controller implementation.
 * 
 * @author Simone Lungarella
 */
@RestController
public class ProcessorCTL implements IProcessorCTL {

    @Override
    public ResponseEntity<byte[]> generatePdf(XsltParamsDTO requestBody, MultipartFile file, HttpServletRequest request) {
        
        ValidationHelper.validateParams(requestBody);
        ValidationHelper.validateTemplate(file);

        byte[] pdfByte = null;
        try {
            pdfByte = FOPHelper.transformMAP2PDF(requestBody.getParams(), file.getBytes());
        } catch (Exception e) {
            // TODO: Define and throw a runtimeException BusinessException
        }
        
        final String filename = FileUtility.getFilename(requestBody.getParams());

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/pdf"));
        httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .headers(httpHeaders)
                .body(pdfByte);
    }

    @Override
    public ResponseEntity<byte[]> generatePdfFromJson(JSONObject json, MultipartFile file, HttpServletRequest request) {
        
        ValidationHelper.validateJson(json);
        ValidationHelper.validateTemplate(file);

        byte[] pdfByte = null;
        try {
            pdfByte = FOPHelper.transformJson2PDF(json.toString(), file.getBytes());
        } catch (Exception e) {
            // TODO: Define and throw a BusinessException
        }
        
        final String filename = FileUtility.getFilenameFromJson(json);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/pdf"));
        httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .headers(httpHeaders)
                .body(pdfByte);
    }
    
}
