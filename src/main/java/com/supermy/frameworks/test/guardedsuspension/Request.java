package com.supermy.frameworks.test.guardedsuspension;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class Request {

    private String name;

    public RespData response;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized RespData getResponseData(){
        return response;
    }

    public synchronized void setRespData(RespData response){
        this.response = response;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
