package com.example.demo.service.impl;

import com.example.demo.config.AccountConfig;
import com.example.demo.dto.CreateAccountRequest;
import com.example.demo.models.Account;
import com.example.demo.models.AccountResponse;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;

import jakarta.persistence.criteria.From;

import org.hibernate.annotations.DialectOverride.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EqlParser.Select_clauseContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountConfig accountConfig;
    @Override
    public ResponseEntity<AccountResponse> create(CreateAccountRequest createAccountRequest) {
	
        //convert request to account
        Account account=createAccountRequest.ToAccount();
        //save account in db
        accountRepository.save(account);
        
        //return the created account as account response
        AccountResponse accountResponse= accountConfig.toAccountResponse(account);
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
    }

    @Override
    public ResponseEntity<AccountResponse> get(Long id) {
        //find the account by given id
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account is not found with"+String.valueOf(id)));
        
        AccountResponse accountResponse=accountConfig.toAccountResponse(account);
        return new ResponseEntity<>(accountResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountResponse> deposit(long id, double depositBalance) {
        //find account
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account is not found with"+String.valueOf(id)));
        account.setBalance(account.getBalance()+depositBalance);
        Account modifiesAccount=accountRepository.save(account);
        AccountResponse accountResponse=accountConfig.toAccountResponse(modifiesAccount);
        return new ResponseEntity<>(accountResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountResponse> withdraw(long id, double withdrawAmount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account is not found with"+String.valueOf(id)));
        if(withdrawAmount>account.getBalance()) throw  new RuntimeException("Insufficient balance");
        else{
            double modifiedBalance=account.getBalance()-withdrawAmount;
            account.setBalance(modifiedBalance);
            Account account1=accountRepository.save(account);
            AccountResponse accountResponse=accountConfig.toAccountResponse(account);
            return new ResponseEntity<>(accountResponse,HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<List<AccountResponse>> getAll()
    {
        List<Account> accountList=accountRepository.findAll();
        List<AccountResponse> accountResponseList=new ArrayList<>();
        for(Account account:accountList)
        {
            accountResponseList.add(accountConfig.toAccountResponse(account));
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountResponseList);
    }

    @Override
    public void delete(long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account is not found with"+String.valueOf(id)));
        if(account!=null)
        {
            accountRepository.deleteById(id);
        }
    }
}
