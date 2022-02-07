package com.example.demo.api;

import com.example.demo.common.AjaxResult;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/1/8 19:40
 * @Description webflux+r2dbc控制器
 **/
@RestController
@RequestMapping(value = "/api/webflux")
public class WebfluxAndR2dbcApi {

    private final UserRepository userRepository;

    public WebfluxAndR2dbcApi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 查询单条
     * @return
     */
    @GetMapping("/select/one")
    public Mono<AjaxResult> queryOne(){
        Mono<User> oneData = userRepository.findById(1L);
        AjaxResult<Mono<User>> userAjaxResult = new AjaxResult<>(oneData);
        return Mono.just(userAjaxResult);
    }

    /**
     * 查询多条
     * @return
     */
    @GetMapping("/select/multiple")
    public Flux<User> queryMultiple() {
        Flux<User> flux = userRepository.findAll();
        return flux;
    }

    /**
     * 查询多条，带分页
     */
    @GetMapping("/select/page")
    public Flux<User> queryMultipleByPage(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Flux<User> flux = userRepository.findbyPage(pageSize, pageNum);
        return flux;
    }

    /**
     * 复杂查询
     */
    @GetMapping("/select/complex")
    public Flux<User> ComplexQuery(@RequestParam("isDel") int isDel) {
        Flux<User> flux = userRepository.complexQuery(isDel);
        return flux;
    }

}
