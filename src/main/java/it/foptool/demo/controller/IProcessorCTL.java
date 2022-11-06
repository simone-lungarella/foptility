package it.foptool.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.foptool.demo.dto.XsltParamsDTO;

/**
 * Processor controller.
 * 
 * @author Simone Lungarella
 */
@RequestMapping(path = "/foptility")
@Tag(name = "PDF Processor", description = "PDF transformer and merger")
public interface IProcessorCTL {

    @PostMapping(value = "/transform", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Transform a xslt into a PDF", description = "Execute the transformation of a xslt file into a PDF file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)) })
    ResponseEntity<byte[]> generatePdf(@RequestBody(required = true) @Parameter XsltParamsDTO requestBody, @RequestPart(value = "xslt", required = true) @Parameter MultipartFile xslt, HttpServletRequest request);

    @PostMapping(value = "/transform/json", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Transform a xslt into a PDF using params from Json object", description = "Execute the transformation of a xslt file into a PDF file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)) })
    ResponseEntity<byte[]> generatePdfFromJson(@RequestPart(required = true) JSONObject json, @RequestPart(value = "xslt", required = true) MultipartFile file, HttpServletRequest request);

}
