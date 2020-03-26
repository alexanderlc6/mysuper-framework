package com.supermy.frameworks.test.masterworker;

import java.util.Map;
import java.util.Set;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class CalculateWorker extends Worker {
    @Override
    public Object handle(Object input) {
        Integer num = (Integer)input;
        return num * num * num;
    }

    public static void main(String[] args) {
        Master calcMaster = new Master(new CalculateWorker(), 5);

        for (int i = 0; i < 100; i++) {
            calcMaster.submit(i);
        }

        calcMaster.execute();

        int total = 0;
        Map<String, Object> resultMap = calcMaster.getResultMap();

        while (resultMap.size() > 0 || !calcMaster.isComplete()){
            Set<String> keySet = resultMap.keySet();
            String key = null;

            for (String k : keySet){
                key = k;
                break;
            }

            Integer num = 0;
            if(key != null){
                num = (Integer)resultMap.get(key);
            }

            if(num != null){
                total += num;
            }

            //移除已经被计算过的项
            if(key != null){
                resultMap.remove(key);
            }
        }

        System.out.println("The calculate result is:" + total);
    }
}
