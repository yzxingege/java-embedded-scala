package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/2/10 17:28
 * @Description 提供给scala的假数据
 **/
public class MockDataInScala {

    /**
     * Scala调用Java的假数据Map
     * @return
     */
    public Map<String, Object> mockDataMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("潘金莲", 23);
        map.put("貂蝉", 26);
        return map;
    }

}
