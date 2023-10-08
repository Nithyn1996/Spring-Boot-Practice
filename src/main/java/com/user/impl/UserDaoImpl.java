package com.user.impl;

import com.user.User;
import com.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.mapper.UserRowMapper;
import java.util.List;

@Repository
public class UserDaoImpl extends UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        String getQuery = "SELECT * FROM " + TABLE_NAME;
        return namedParameterJdbcTemplate.query(getQuery, new UserRowMapper());
    }

    @Override
    public User addUser(User user) {
        String addQuery = "INSERT INTO " + TABLE_NAME + "(role_id, user_name, email_address, phone_number, salary, skills) " +
                "VALUES (:roleId, :userName, :emailAddress, :phoneNumber, :salary, :skills)";

        MapSqlParameterSource mapper = new MapSqlParameterSource();
        mapper.addValue("roleId", user.getRoleId());
        mapper.addValue("userName", user.getUserName());
        mapper.addValue("emailAddress", user.getEmailAddress());
        mapper.addValue("phoneNumber", user.getPhoneNumber());
        mapper.addValue("salary", user.getSalary());
        ObjectMapper objectMapper = new ObjectMapper();
        String skillsJson;
        try {
            skillsJson = objectMapper.writeValueAsString(user.getSkills());
        } catch (Exception e) {
            throw new RuntimeException(JSON_ERROR, e);
        }
        mapper.addValue("skills", skillsJson);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = namedParameterJdbcTemplate.update(addQuery, mapper, keyHolder);
        Number generatedKey = keyHolder.getKey();
        if (rowsAffected > 0) {
            if (generatedKey != null) {
                user.setId(generatedKey.intValue());
            }
            return user;
        } else {
            return new User();
        }
    }

    @Override
    public int updateUser(User user) {
        String updateQuery = "UPDATE " +TABLE_NAME +
                " SET role_id = :roleId, " +
                "user_name = :userName, " +
                "email_address = :emailAddress, " +
                "phone_number = :phoneNumber," +
                "salary = :salary," +
                "skills = :skills " +
                "WHERE id = :id";

        MapSqlParameterSource mapper = new MapSqlParameterSource();
        mapper.addValue("id", user.getId());
        mapper.addValue("roleId", user.getRoleId());
        mapper.addValue("userName", user.getUserName());
        mapper.addValue("emailAddress", user.getEmailAddress());
        mapper.addValue("phoneNumber", user.getPhoneNumber());
        mapper.addValue("salary", user.getSalary());
        ObjectMapper objectMapper = new ObjectMapper();
        String skillsJson;
        try {
            skillsJson = objectMapper.writeValueAsString(user.getSkills());
        } catch (Exception e) {
            throw new RuntimeException(JSON_ERROR, e);
        }
        mapper.addValue("skills", skillsJson);

        return namedParameterJdbcTemplate.update(updateQuery, mapper);
    }

    @Override
    public int deleteUser(int id) {
        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = :id";
        MapSqlParameterSource mapper = new MapSqlParameterSource();
        mapper.addValue("id", id);
        return namedParameterJdbcTemplate.update(deleteQuery, mapper);
    }
}
