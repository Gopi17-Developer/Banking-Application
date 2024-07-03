package com.example.demo.service;

import com.example.demo.dto.CreateAccountRequest;
import com.example.demo.models.AccountResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
	
        public ResponseEntity<AccountResponse> create(CreateAccountRequest createAccountRequest);
        public ResponseEntity<AccountResponse> get(Long id);
        public  ResponseEntity<AccountResponse> deposit(long id, double depositBalance);
        public ResponseEntity<AccountResponse> withdraw(long id,double withdrawAmount);
        public ResponseEntity<List<AccountResponse>> getAll();
        public void delete(long id);

}
