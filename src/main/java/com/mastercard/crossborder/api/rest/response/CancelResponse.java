package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName("cancelpayment")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@XmlType(name = "CancelResponse")
@XmlRootElement(name = "cancelpayment")
public class CancelResponse  extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;


}
