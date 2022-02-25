package com.example.demo.api.proto;

import com.alibaba.fastjson.JSON;
import com.example.TestJava;
import com.example.demo.common.AjaxResult;
import com.example.demo.entity.User;
import com.example.demo.server.CacheBuilder;
import com.example.demo.util.JavaConvertScala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Author hanxiang
 * @email gordon.han@adtiming.com
 * @Date 2022/2/24 16:56
 * @Description proto数据生成
 **/
@RestController()
public class ProtoDataGenerateApi {

    @Autowired
    private final CacheBuilder cacheBuilder;

    public ProtoDataGenerateApi(CacheBuilder cacheBuilder){
        this.cacheBuilder = cacheBuilder;
    }

    @RequestMapping(value = "/api/proto/run",method = RequestMethod.GET)
    public void generateProtoData(){
        cacheBuilder.buildTestData();
    }

}
