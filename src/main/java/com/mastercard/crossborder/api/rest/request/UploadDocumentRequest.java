package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "uploadDocumentRequest")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonPropertyOrder(value = {"fileName", "file" })
public class UploadDocumentRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String file;

    @JsonProperty(value = "fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JsonProperty(value = "file")
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
