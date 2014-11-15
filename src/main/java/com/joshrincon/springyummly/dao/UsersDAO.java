package com.joshrincon.springyummly.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Component("usersDAO")
public class UsersDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    // spring can autowire without a setter
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setDataSource(DataSource jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }
    @Transactional
    public boolean create(User user) {
//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", passwordEncoder.encode(user.getPassword()));
        params.addValue("email", user.getEmail());
        params.addValue("enabled", user.isEnabled());
        params.addValue("authority", user.getAuthority());
        jdbcTemplate.update("insert into users (username, password, email, enabled) values (:username, :password, :email, :enabled)", params);
        return jdbcTemplate.update("insert into authorities (username, authority) values (:username, :authority)", params) == 1;
    }
    public boolean exists(String username) {
        return jdbcTemplate.queryForObject("select count(*) from users where username=:username",
                new MapSqlParameterSource("username", username), Integer.class) > 0;
    }
    public List<User> getAllUsers() {
// map each these rows to a user bean
        return jdbcTemplate.query("select * from users, authorities where users.username=authorities.username", BeanPropertyRowMapper.newInstance(User.class));
    }
}
