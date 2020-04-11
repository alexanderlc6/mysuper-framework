package com.supermy.frameworks.test.produceconsume;

/**
 * Created by AlexLc on 2020/4/11.
 */
public class ConsumeData {
    private final int data;

    public ConsumeData(int data) {
        this.data = data;
    }

    public ConsumeData(String data) {
        this.data = Integer.valueOf(data);
    }

    public int getData(){
        return data;
    }

    @Override
    public String toString() {
        return "ConsumeData{" +
                "data=" + data +
                '}';
    }
}
