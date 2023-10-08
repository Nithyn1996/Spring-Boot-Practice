package com.user.mapper;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        User user = new User();
        user.setId(resultSetToInt(resultSet, "id"));
        user.setRoleId(resultSetToInt(resultSet, "role_id"));
        user.setUserName(resultSetToString(resultSet, "user_name"));
        user.setEmailAddress(resultSetToString(resultSet, "email_address"));
        user.setPhoneNumber(resultSetToLong(resultSet, "phone_number"));
        user.setSalary(resultSetToFloat(resultSet, "salary"));
        user.setSkills(resultSetToList(resultSet, "skills"));
        return user;
    }

    public String resultSetToString(ResultSet resultSet, String key) {
        try {
            String result = resultSet.getString(key);
            return (result != null) ? result : "";
        } catch (SQLException e) {
            return "";
        }
    }

    public int resultSetToInt(ResultSet resultSet, String key) {
        try {
            int result = resultSet.getInt(key);
            return (result > 0) ? result : 0;
        } catch (SQLException e) {
            return 0;
        }
    }


    public long resultSetToLong(ResultSet resultSet, String key) {
        try {
        	long result = resultSet.getLong(key);
            return (result > 0) ? result : 0;
        } catch (SQLException e) {
            return 0;
        }
    }

   public float resultSetToFloat(ResultSet resultSet, String key) {
        try {
        	float result = resultSet.getFloat(key);
            return (result > 0) ? result : 0.0f;
        } catch (SQLException e) {
            return 0.0f;
        }
    }

    public List<String> resultSetToList(ResultSet resultSet, String key) {
        try {
            String stringList = resultSet.getString(key);
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> skillList = objectMapper.readValue(stringList, List.class);
            return skillList;
        } catch (SQLException | IOException e) {
            return Collections.emptyList();
        }
    }
}