package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Technical {
    private String specialNotes;
    private String lastUpdatedTime;
    private List<Field> fields;
}
