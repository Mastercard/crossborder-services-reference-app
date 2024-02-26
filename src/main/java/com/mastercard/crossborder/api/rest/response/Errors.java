package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.ToString;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonTypeName("Errors")
@XmlRootElement(
        name = "Errors"
)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@ToString
public class Errors implements Serializable {


    @XmlElement(name = "Error",required = true)
    @JsonProperty(value = "Error")
    private List<Error> Errors = new ArrayList<>();

    public void addError(Error error) {
        this.getErrorList().add(error);
    }


    @XmlElement(
            name = "Error",
            required = true
    )
    @JsonProperty(value = "Error")
    public List<Error> getErrorList() {
        if (this.Errors == null) {
            this.Errors = new ArrayList<>();
        }
        return this.Errors;
    }

    public void setErrorList(List<Error> Errors) {
        this.Errors = Errors;
    }

  /*  public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }*/
}
