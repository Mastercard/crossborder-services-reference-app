package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "PrincipalAmount")
@XmlType(name = "principal_amount", propOrder = {"prinAmt", "prinCurr"})
@XmlRootElement(name = "principal_amount")
public class PrincipalAmount implements Serializable {

        private String prinAmt;
        private String prinCurr;

        @JsonProperty(value = "amount")
        @XmlElement(name = "amount", nillable = false)

        public String getPrinAmt() {
            return prinAmt;
        }

        public void setPrinAmt(String prinAmt) {
            this.prinAmt = prinAmt;
        }

        @JsonProperty(value = "currency")
        @XmlElement(name = "currency", nillable = false)
        public String getPrinCurr() {
            return prinCurr;
        }

        public void setPrinCurr(String prinCurr) {
            this.prinCurr = prinCurr;
        }

        @Override
        public String toString() {
            return "PrincipalAmount{" +
                    "currency='" + prinCurr + '\'' +
                    ",amount='" + prinAmt + '\'' +
                    '}';
        }

        public String toString(String fieldName) {
            return ","+ fieldName +
                    "_currency='" + prinCurr + "'," +
                    fieldName + "_amount='" + prinAmt + '\'' ;
        }

}
