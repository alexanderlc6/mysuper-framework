package com.supermy.frameworks.test.guardedsuspension;

/**
 * Created by AlexLc on 2020/3/27.
 */
public class FutureData implements RespData {

    private Boolean isReady;

    private RealData realData;

    public void setRealData(RealData realData){
        this.realData = realData;
    }

    @Override
    public String getContent() {
        return null;
    }
}
