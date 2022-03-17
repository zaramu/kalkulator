package no.oving5.kalkulator5backend.controller;

import no.oving5.kalkulator5backend.model.Expression;
import no.oving5.kalkulator5backend.repository.ExpressionRepository;
import no.oving5.kalkulator5backend.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ExpressionController {

    @Autowired
    ExpressionRepository expressionRepository;

    @Autowired
    private ExpressionService service;


    @GetMapping("/expressions")
    public ResponseEntity<List<Expression>> getAllExpressions() {
        try {
            List<Expression> expressions = new ArrayList<Expression>();


            //Gjort litt endringer her, ikke helt likt som eksemplet!!


            expressionRepository.findAll().forEach(expressions::add);

            if (expressions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(expressions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**@GetMapping("/expressions")
    public List<Expression> getAllExpressions() {
        return expressionRepository.findAll();
    }*/

    @GetMapping("/expressions/{id}")
    public ResponseEntity<Expression> getExpressionById(@PathVariable("id") long id) {
        Expression expression = expressionRepository.findById(id);
        if (expression != null) {
            return new ResponseEntity<>(expression, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/expressions")
    public ResponseEntity<String> createExpression(@RequestBody Expression expression) {
        try {

            /**net.objecthunter.exp4j.Expression newExpression = new ExpressionBuilder(expression.getMathExpression()).build();
            expression.setAnswer(newExpression.evaluate());*/

            expression.setAnswer(service.calculate(expression.getMathExpression()));

            expressionRepository.save(new Expression(expression.getMathExpression(), expression.getAnswer(), expression.getUserId()));
            return new ResponseEntity<>("Expression was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/expressions/{id}")
    public ResponseEntity<String> deleteExpression(@PathVariable("id") long id) {
        try {
            int result = expressionRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find expression with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Expression was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete expression.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/expressions")
    public ResponseEntity<String> deleteAllExpressions() {
        try {
            int numRows = expressionRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " expression(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete expressions.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
