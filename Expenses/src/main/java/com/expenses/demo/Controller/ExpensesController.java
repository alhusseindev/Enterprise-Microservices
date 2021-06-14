package com.expenses.demo.Controller;


import com.expenses.demo.Entity.Expenses;
import com.expenses.demo.Exceptions.ExpensesException;
import com.expenses.demo.Repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/expenses/expense")
@RestController
public class ExpensesController{
    @Autowired
    private ExpensesRepository myExpenseRepository;


    @GetMapping("/list")
    public List<Expenses> listExpenses(){
        return myExpenseRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Expenses findExpenseById(@PathVariable Long id) throws ExpensesException {
        return myExpenseRepository.findById(id).orElseThrow(() -> new ExpensesException("Expense Not Found!"));
    }

    @PostMapping("/new")
    public Expenses createExpense(@RequestBody Expenses myExpense){
        return myExpenseRepository.save(myExpense);
    }

    @PutMapping("/update/{id}")
    public Expenses updateExpenseById(@RequestBody Expenses myExpense, @PathVariable Long id){
        return myExpenseRepository.findById(id).map((expense) ->{
            expense.setPaymentDate(myExpense.getPaymentDate());
            expense.setPaymentMethod(myExpense.getPaymentMethod());
            expense.setExpenseValue(myExpense.getExpenseValue());
            expense.setBankAccount(myExpense.getBankAccount());
            expense.setExpenseDescription(myExpense.getExpenseDescription());
            expense.setOutstandingBalance(myExpense.getOutstandingBalance());
            expense.setPayee(myExpense.getPayee());
            expense.setMoneyInAccount(myExpense.getMoneyInAccount());
            expense.setTypeOfExpense(myExpense.getTypeOfExpense());
            expense.setCreatedAt(myExpense.getCreatedAt());
            return myExpenseRepository.save(expense);
        }).orElseGet(() ->{
            myExpense.setId(id);
            return myExpenseRepository.save(myExpense);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        myExpenseRepository.deleteById(id);
    }


    @GetMapping("/expense/total/count")
    public Long getExpenseCount(){
        return myExpenseRepository.count();
    }

}