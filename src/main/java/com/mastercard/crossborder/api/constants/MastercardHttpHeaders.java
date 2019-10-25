package com.mastercard.crossborder.api.constants;

public enum MastercardHttpHeaders  {
    ENCRYPTED_HEADER("x-encrypted");
    private final String text;

    MastercardHttpHeaders(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
