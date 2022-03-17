package no.oving5.kalkulator5backend.repository;

import no.oving5.kalkulator5backend.model.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcExpressionRepository implements ExpressionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Expression expression){
        return jdbcTemplate.update("INSERT INTO expressions (mathExpression, answer, userId) VALUES(?,?,?)",
                new Object[] { expression.getMathExpression(), expression.getAnswer(), expression.getUserId() });
    }

    @Override
    public Expression findById(Long id) {
        try {
            Expression expression = jdbcTemplate.queryForObject("SELECT * FROM expressions WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Expression.class), id);
            return expression;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM expressions WHERE id=?", id);
    }

    @Override
    public List<Expression> findAll() {
        return jdbcTemplate.query("SELECT * from expressions", BeanPropertyRowMapper.newInstance(Expression.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from expressions");
    }
}
