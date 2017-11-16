package com.java.study.trycatch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by zhongjing on 2016/1/28 0028.
 */
public class ApiException extends Exception {
    private static final long serialVersionUID = -238091758285157331L;
    private String errCode;
    private String errMsg;

    public ApiException() {
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }


    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("10.32.41.45", 8080);
            Socket socket = connect(address,1000);
            try {
                //判断是否是连接状态
                socket.setSendBufferSize(0xff);
            }catch (IOException e){
                throw new ApplicationException("-10001", "Socket关闭了");
            }
        } catch (ApiException|IOException e) {
            throw new ApplicationException("", e.getMessage());
        }
    }


    public static Socket connect(SocketAddress address, Integer timeOut) throws ApiException,IOException {
        Socket socket = new Socket();
        socket.connect(address, timeOut);
        return socket;
    }
}
