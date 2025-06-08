package com.nitya.spring_security.repository;

import com.nitya.spring_security.entities.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Optional;

@Repository
public class UserRepository {
    private static final String INSERT = "INSERT INTO \"user\" (name, email, password) VALUES(:name, :email, :password)";;
    private static final String FIND_BY_EMAIL = "SELECT * FROM \"user\" WHERE email = :email";

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void add(User user) {
        long affected = jdbcClient.sql(INSERT)
                .param("name", user.name())
                .param("email", user.email())
                .param("password", user.password())
                .update();

        Assert.isTrue(affected == 1, "Could not add user.");
    }

    public Optional<User> findByEmail(String email) {
        return jdbcClient.sql(FIND_BY_EMAIL)
                .param("email", email)
                .query(User.class)
                .optional();
    }
}
