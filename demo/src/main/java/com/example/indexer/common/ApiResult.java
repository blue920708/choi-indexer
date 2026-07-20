package com.example.indexer.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult<T> {

    private int code;
    private String msg;
    private T result;

    public ApiResult(EnumMapperType en) {
        this.code = en.getCode();
        this.msg = en.getMsg();
    }

    public ApiResult(EnumMapperType en, T result) {
        this.code = en.getCode();
        this.msg = en.getMsg();
        this.result = result;
    }

    public ApiResult(T result) {
        this.code = ApiCode.SUCCESS.getCode();
        this.msg = ApiCode.SUCCESS.getMsg();
        this.result = result;
    }
}
