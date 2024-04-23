package com.mastercard.crossborder.api.rest.vas.bav.api.response;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.BankInfoRes.Address;

import java.util.List;

public class BankData {

    private List<Bic> bics;
    private String name;
    private String branchName;
    private Address address;
    private SanctionDetails sanctionDetails;
    private List<LocalDetails> localDetails;

    public BankData() {
    }



    public BankData(List<Bic> bics, String name, String branchName, Address address, SanctionDetails sanctionDetails, List<LocalDetails> localDetails) {
        this.bics = bics;
        this.name = name;
        this.branchName = branchName;
        this.address = address;
        this.sanctionDetails = sanctionDetails;
        this.localDetails = localDetails;
    }

    public List<Bic> getBics() {
        return bics;
    }

    public void setBics(List<Bic> bics) {
        this.bics = bics;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SanctionDetails getSanctionDetails() {
        return sanctionDetails;
    }

    public void setSanctionDetails(SanctionDetails sanctionDetails) {
        this.sanctionDetails = sanctionDetails;
    }

    public List<LocalDetails> getLocalDetails() {
        return localDetails;
    }

    public void setLocalDetails(List<LocalDetails> localDetails) {
        this.localDetails = localDetails;
    }
}
