package com.example.demo.config;

import com.example.demo.models.Account;
import com.example.demo.models.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountConfig {
    public Account ToAccount(AccountResponse accountResponse)
    {
        Account account= new Account(
                accountResponse.getId(),
                accountResponse.getAccountHolderName(),
                accountResponse.getBalance()
        );
        return account;
    }
    public AccountResponse toAccountResponse(Account account)
    {
        AccountResponse accountResponse=new AccountResponse(
          account.getId(),
          account.getAccountHolderName(),
                account.getBalance()
        );
        return accountResponse;
    }
}
