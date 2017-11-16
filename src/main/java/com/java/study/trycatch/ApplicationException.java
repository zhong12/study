package com.java.study.trycatch;

import java.util.List;

/**
 * Created by zhongjing on 2016/1/28 0028.
 */
public class ApplicationException extends RuntimeException {
    private String statusCode;
    private String message;
    private String placeHolderMessage;
    private List<String> placeHolderList;
    private static final long serialVersionUID = 1L;

    public ApplicationException(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApplicationException(String statusCode, String message, Throwable throwable) {
        super(message, throwable);
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApplicationException(String statusCode, String message, String placeHolderMessage, List<String> placeHolderList) {
        this.statusCode = statusCode;
        this.message = message;
        this.placeHolderMessage = placeHolderMessage;
        this.placeHolderList = placeHolderList;
    }

    public ApplicationException(String statusCode, String message, String placeHolderMessage, List<String> placeHolderList, Throwable throwable) {
        super(message, throwable);
        this.statusCode = statusCode;
        this.message = message;
        this.placeHolderMessage = placeHolderMessage;
        this.placeHolderList = placeHolderList;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResultCode() {
        return this.statusCode;
    }

    public String getPlaceHolderMessage() {
        return this.placeHolderMessage;
    }

    public List<String> getPlaceHolderList() {
        return this.placeHolderList;
    }
}
