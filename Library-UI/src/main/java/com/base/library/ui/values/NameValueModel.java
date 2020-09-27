package com.base.library.ui.values;

/**
 * @author reber
 */
public class NameValueModel {

    private String name;
    private String value;

    public NameValueModel(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
