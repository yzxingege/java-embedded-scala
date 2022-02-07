package com.example.demo.repository;
import com.example.demo.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * 仓库(repository) 类似于原先的 dao 的角色，主要提供各种底层数据访问功能
 * @author hanxiang
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("SELECT * FROM user LIMIT :limit OFFSET :offset")
    Flux<User> findbyPage(int limit, int offset);

    @Query("SELECT u.* FROM `user` u, `order` o where o.uid = u.id and is_del = :isDel")
    Flux<User> complexQuery(int isDel);

}
