package com.example.indexer.config;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {
    private int code;
    private String msg;

}
