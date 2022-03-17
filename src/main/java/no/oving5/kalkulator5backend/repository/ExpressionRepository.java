package no.oving5.kalkulator5backend.repository;

import no.oving5.kalkulator5backend.model.Expression;

import java.util.List;

public interface ExpressionRepository {
    int save(Expression expression);
    //int update(Expression expression);
    Expression findById(Long id);
    int deleteById(Long id);
    List<Expression> findAll();
    int deleteAll();

}
