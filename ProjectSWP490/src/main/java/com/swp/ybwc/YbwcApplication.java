package com.swp.ybwc;

import com.swp.ybwc.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class YbwcApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(YbwcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "Select * from CATEGORY";
        List<Category> Cate = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Category.class));
        Cate.forEach(System.out::println);
    }

}
