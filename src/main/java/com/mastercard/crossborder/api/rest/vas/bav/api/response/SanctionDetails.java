package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class SanctionDetails {
    private Boolean eu;
    private Boolean hmt;
    private Boolean ofac;
    private Boolean un;

    public SanctionDetails() {
    }

    public SanctionDetails(Boolean eu, Boolean hmt, Boolean ofac, Boolean un) {
        this.eu = eu;
        this.hmt = hmt;
        this.ofac = ofac;
        this.un = un;
    }

    public Boolean getEu() {
        return eu;
    }

    public void setEu(Boolean eu) {
        this.eu = eu;
    }

    public Boolean getHmt() {
        return hmt;
    }

    public void setHmt(Boolean hmt) {
        this.hmt = hmt;
    }

    public Boolean getOfac() {
        return ofac;
    }

    public void setOfac(Boolean ofac) {
        this.ofac = ofac;
    }

    public Boolean getUn() {
        return un;
    }

    public void setUn(Boolean un) {
        this.un = un;
    }
}
