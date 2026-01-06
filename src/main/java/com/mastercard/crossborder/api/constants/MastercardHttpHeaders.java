package com.mastercard.crossborder.api.constants;

public enum MastercardHttpHeaders  {
    ENCRYPTED_HEADER("x-encrypted"),
    PARTNER_REF_ID("Partner-Ref-Id"),
    SPECIFICATION_TYPE("Specification-Type");

    private final String text;

    MastercardHttpHeaders(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
