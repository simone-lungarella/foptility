package it.foptool.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XsltParamsDTO {

    @Schema(description = "XSLT parameters", example = "{ \"param1\": \"value1\", \"param2\": \"value2\" }")
    private Map<String, String> params;
}
