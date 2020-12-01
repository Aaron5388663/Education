package com.atlp.commonutils;

public enum ResultCodeEnum {

    SUCCESS(20000, "成功"),
    ERROR(200001, "失败");

    private Integer key;
    private String value;

    private ResultCodeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public ResultCodeEnum getById(Integer id) {
        for(ResultCodeEnum codeEnum : values()) {
            if(codeEnum.getKey() == id) {
                return codeEnum;
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
