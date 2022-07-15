package com.tuniu.dao.impl;

import com.tuniu.Pojo.User;
import com.tuniu.dao.userDao;
import com.tuniu.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class userDaoImpl implements userDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User checkusername(String username) {
        try {
            String sql="select * from tab_user where username=?";
            User user= template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void registeruser(User user) {
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email) VALUES(?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                            user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail());

    }
}
