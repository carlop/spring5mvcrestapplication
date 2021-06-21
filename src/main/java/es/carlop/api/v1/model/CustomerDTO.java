package es.carlop.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerDTO {
    @Schema(description = "This is the first name.", required = true)
    private String firstName;
    @Schema(description = "This is the last name.", required = true)
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
