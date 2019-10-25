package com.mastercard.crossborder.api.rest.response;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "CancelResponse")
@XmlRootElement(name = "cancelpayment")
public class CancelResponse extends BaseResponse  implements Serializable {

    private static final long serialVersionUID = 1L;

    public CancelResponse() {
        //Default constructor
    }
}
