package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class Bic {
    String type;
    String value;

    public Bic(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Bic() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Bic{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
