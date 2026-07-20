package com.example.indexer.common;

public enum ApiCode implements EnumMapperType{

    SUCCESS(0000, "성공"),
    NODATA(0001, "데이터 없음"),
    FAIL(9999, "실패")
    ;

    private int code;
    private String msg;

    ApiCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public int getCode() {
        return this.code;
    }
    
    @Override
    public String getMsg() {
        return this.msg;
    }


    
}
