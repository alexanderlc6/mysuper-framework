package com.supermy.frameworks.test.guardedsuspension;

/**
 * Created by AlexLc on 2020/3/27.
 */
public class RealData implements RespData {
    private String content;

    public RealData(String param) {
        this.content = param;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
