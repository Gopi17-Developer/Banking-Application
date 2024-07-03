package com.example.demo.controller;

import com.example.demo.dto.CreateAccountRequest;
import com.example.demo.models.AccountResponse;
import com.example.demo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> create(@RequestBody @Valid CreateAccountRequest createAccountRequest)
    {
        return accountService.create(createAccountRequest);
    }
    @GetMapping("/get")
    public ResponseEntity<AccountResponse> get(@RequestParam("id") long id)
    {
        return accountService.get(id);
    }
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountResponse> deposit(@PathVariable long id, @RequestBody Map<String,Double> request)
    {
        Double depositBalance=request.get("amount");
        return accountService.deposit(id,depositBalance);
    }
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountResponse> withdraw(@PathVariable long id,@RequestBody Map<String,Double> request)
    {
        Double withDrawAmount=request.get("amount");
        return accountService.withdraw(id,withDrawAmount);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AccountResponse>> getAll()
    {
        return accountService.getAll();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id)
    {
        accountService.delete(id);
        return ResponseEntity.ok("Success");
    }
}
