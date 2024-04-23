package com.mastercard.crossborder.api.rest.vas.bav.api.request.ValidateReq;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.Bic;
import lombok.ToString;

@ToString
public class Bank {

    private Bic bic;
    private String name;
    private String branchName;

    private String branchCode;
    private BAVAddress address;


    public Bank() {
    }

    public Bank(String name, String branchName, String branchCode , String country , Bic bic, BAVAddress address) {
        this.name = name;
        this.branchName = branchName;
        this.branchCode = branchCode;
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

    public void setBranchName(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchCode() {
        return branchName;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
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
