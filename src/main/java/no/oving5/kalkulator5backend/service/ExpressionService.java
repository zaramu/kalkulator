package no.oving5.kalkulator5backend.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/expressions")
public class ExpressionService {

    public double calculate(String expression){
        Expression newExpression = new ExpressionBuilder(expression).build();
        return newExpression.evaluate();
    }

}
