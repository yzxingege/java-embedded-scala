package com.example.demo.api;
import com.alibaba.fastjson.JSON;
import com.example.TestJava;
import com.example.demo.common.AjaxResult;
import com.example.demo.util.JavaConvertScala;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Map;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/2/10 18:39
 * @Description Java调用Scala测试单例的问题
 **/
@RestController()
public class JavaTransferScalaApi {

    @RequestMapping(value = "/javaToScala",method = RequestMethod.POST)
    public Mono<AjaxResult> testJavaToScala(@RequestBody Map<String, Object> request){
        TestJava testJava = new TestJava();
        //传入之前，先转成scala的map
        scala.collection.immutable.Map<String, Object> req = JavaConvertScala.toScalaImmutableMap(request);
        //调用scala处理业务逻辑
        Map map = JSON.parseObject(JSON.toJSONString(testJava.returnMap(req)));
        AjaxResult<Object> ajaxResult = new AjaxResult(map);
        return Mono.just(ajaxResult);
    }

}
