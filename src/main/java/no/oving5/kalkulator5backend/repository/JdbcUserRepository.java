package no.oving5.kalkulator5backend.repository;

import no.oving5.kalkulator5backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user){
        return jdbcTemplate.update("INSERT INTO users (firstName, lastName, username, password) VALUES(?,?,?,?)",
                new Object[] { user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword() });
    }

    @Override
    public User findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
