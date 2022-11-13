package it.foptool.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.foptool.dto.XsltParamsDTO;

/**
 * Processor controller.
 * 
 * @author Simone Lungarella
 */
@CrossOrigin(origins = "*")
@RequestMapping(path = "/foptility")
@Tag(name = "PDF Processor", description = "PDF transformer and merger")
public interface IProcessorCTL {

        @PostMapping(value = "/transform", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        @Operation(summary = "Transform a xslt into a PDF", description = "Execute the transformation of a xslt file into a PDF file")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)) })
        ResponseEntity<byte[]> generatePdf(@RequestBody(required = false) XsltParamsDTO requestBody, @RequestPart("file") MultipartFile file, HttpServletRequest request);

        @PostMapping(value = "/transform/json", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        @Operation(summary = "Transform a xslt into a PDF using params from Json object", description = "Execute the transformation of a xslt file into a PDF file")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)) })
        ResponseEntity<byte[]> generatePdfFromJson(@RequestPart(name = "json", required = true) String json, @RequestPart(value = "file", required = true) MultipartFile file, HttpServletRequest request);

        @PostMapping(value = "/transform/json/generic-table", consumes = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Transform a xslt into a PDF using params from Json object", description = "Execute the transformation of a xslt file into a PDF file")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)) })
        ResponseEntity<byte[]> generatePdf(@RequestBody(required = true) String json, HttpServletRequest request);
}
