package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/2/10 17:45
 * @Description Java调用Scala
 **/
@SpringBootTest
public class JavaTransferScala {

    /**
     * Java调用Scala得到Map集合
     * 注意： Scala返回的Map不能直接使用，需要在scala那边先转成Java的Map
     */
    @Test
    public void JavaTranserScalaTest(){
        MockDataInJava mockDataInJava = new MockDataInJava();
        Map map = JSON.parseObject(JSON.toJSONString(mockDataInJava.mockDataMap()));
        System.err.println(JSON.toJSONString(map));
    }

}
