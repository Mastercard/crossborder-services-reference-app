package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "downloadDocumentResponse")
@JsonPropertyOrder(value = {"file","fileName","referenceId"})
public class DownloadDocumentResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String referenceId;
    private String fileName;
    private String file;

    @JsonProperty(value = "referenceId")
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

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
