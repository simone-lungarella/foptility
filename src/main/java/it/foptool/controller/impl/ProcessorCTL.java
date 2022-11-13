package it.foptool.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.foptool.controller.IProcessorCTL;
import it.foptool.dto.XsltParamsDTO;
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
    public ResponseEntity<byte[]> generatePdfFromJson(String json, MultipartFile file, HttpServletRequest request) {
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=seedTracking.pdf" );

        byte[] pdfFile = null;
        try {
            pdfFile = generatePdf(json, file.getBytes());
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .headers(httpHeaders)
                .body(pdfFile);
    }

    @Override
    public ResponseEntity<byte[]> generatePdf(String json, HttpServletRequest request) {
        
        final JSONObject jsonObject = new JSONObject(json);
        final JSONObject root = jsonObject.getJSONObject("parameters");
        String filename = root.getString("filename");

        if (!StringUtils.isEmpty(filename)) {
            // Removing extension
            filename = FilenameUtils.getBaseName(filename);
        } else {
            // TODO: Handle exception
        }

        final byte[] file = FileUtility.getFileFromInternalResources(filename + ".xslt");
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + ".pdf" );

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
            throw new RuntimeException("Error during PDF generation");
        }
    }
}
