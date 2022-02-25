package com.example.demo.server;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pbBean.TestProto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @Author hanxiang
 * @email gordon.han@adtiming.com
 * @Date 2022/2/24 15:34
 * @Description
 **/
@Component
public class CacheBuilder extends PbBuiler{

    private final UserRepository userRepository;
    public CacheBuilder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger LOG = LogManager.getLogger();

//    @Scheduled(fixedDelay = 60000)
    private void buildCache() {
        long start = System.currentTimeMillis();
        LOG.debug("build cache start");
        buildTestData();
        LOG.debug("build cache finished, cost:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 构建测试数据
     */
    public void buildTestData() {
        Flux<User> users = userRepository.findAll();
        Mono<Long> count = users.count();
        ArrayList<User> list = new ArrayList<>();
        users.toStream().forEach(list::add);
        build("gx_country", new File("/Users/hanxiang/Desktop"), out -> {
            list.forEach(m->{
            pbBean.TestProto.TestData.Builder user = pbBean.TestProto.TestData.newBuilder();
                user.setId( m.getId().intValue())
                    .setName(m.getName())
                    .setUserAge(m.getUserAge())
                    .setIsDel(m.getIsDel());
            System.err.println(JSON.toJSONString(user.build()));
            });
        });
    }
}
