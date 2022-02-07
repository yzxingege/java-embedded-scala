package com.example.demo.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/1/8 19:35
 * @Description
 **/
@Table("user")
@Data
public class User {

    /**
     * 主键
     */
    @Id
    @Column("id")
    private Long id;

    /**
     * 名字
     */
    @Column("name")
    private String name;

    /**
     * 年龄
     */
    @Column("user_age")
    private int userAge;

    /**
     * 是否删除
     */
    @Column("is_del")
    private int isDel;

}
