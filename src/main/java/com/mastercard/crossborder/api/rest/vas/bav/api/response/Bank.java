package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class Bank {

    private Bic bic;
    private String name;
    private String branchName;
    private String branchCode;
    private BavAddress address;

    public Bank() {
    }

    public Bank(Bic bic, String name, String branchName, String branchCode, BavAddress address) {
        this.bic = bic;
        this.name = name;
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.address = address;
    }

    public Bic getBic() {
        return bic;
    }

    public void setBic(Bic bic) {
        this.bic = bic;
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

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public BavAddress getAddress() {
        return address;
    }

    public void setAddress(BavAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bic=" + bic +
                ", name='" + name + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", address=" + address +
                '}';
    }
}
