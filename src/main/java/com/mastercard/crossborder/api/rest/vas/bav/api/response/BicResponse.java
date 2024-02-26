package com.mastercard.crossborder.api.rest.vas.bav.api.response;

import java.util.List;

public class BicResponse {
    private List<Bic> bic;

    public BicResponse() {
    }

    public BicResponse(List<Bic> bic) {
        this.bic = bic;
    }

    public List<Bic> getBic() {
        return bic;
    }

    public void setBic(List<Bic> bic) {
        this.bic = bic;
    }
}
