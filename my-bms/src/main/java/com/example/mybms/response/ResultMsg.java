package com.example.mybms.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 返回的消息格式
 */
public class ResultMsg<T> {
    private int code;
    private String msg;
    protected T data;
    public void success(T data){
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public void fail(){
        this.code = ResultCode.FAIL.getCode();
        this.msg = ResultCode.FAIL.getMsg();
    }
}
