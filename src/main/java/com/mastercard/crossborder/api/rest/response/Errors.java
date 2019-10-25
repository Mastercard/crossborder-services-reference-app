package com.mastercard.crossborder.api.rest.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlType(
        name = "Errors",
        propOrder = {"errors"}
)
@XmlRootElement(
        name = "Errors"
)
public class Errors implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Error> errors = new ArrayList();

    public Errors() {
        //Default constructor
    }

    @XmlElement(
            name = "Error",
            required = true,
            nillable = false
    )
    public List<Error> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList();
        }

        return this.errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void addError(Error error) {
        this.getErrors().add(error);
    }
}

