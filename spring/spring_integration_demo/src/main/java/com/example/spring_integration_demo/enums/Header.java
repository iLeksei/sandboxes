package com.example.spring_integration_demo.enums;

import lombok.Data;

public enum Header {
    REGION_MESSAGE_HEADER("REGION");

    private String value;

    Header(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
