package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.mastercard.crossborder.api.rest.vas.bav.api.response.Bic;
import lombok.ToString;

@ToString
public class Bank {
    private String name;
    private String branchName;

    private String country;
    private Bic bic;
    private BAVAddress address;

    public Bank() {
    }

    public Bank(String name, String branchName, String country , Bic bic, BAVAddress address) {
        this.name = name;
        this.branchName = branchName;
        this.country = country;
        this.bic = bic;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }


    public String getCountry(){ return country; }

    public void setCountry (String country){
        this.country = country;
    }

    public Bic getBic() {
        return bic;
    }

    public void setBic(Bic bic) {
        this.bic = bic;
    }

    public BAVAddress getAddress() {
        return address;
    }

    public void setAddress(BAVAddress address) {
        this.address = address;
    }
}
