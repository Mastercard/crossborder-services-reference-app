package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "cancelpaymentrequest")
@XmlType(name = "CancelPayment")
@XmlRootElement(name = "cancelpaymentrequest")
public class CancelRemittance extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public CancelRemittance() {
    }

    public CancelRemittance(String stringBody) {
    }

}
