package com.example.demo.dto;

import com.example.demo.models.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateAccountRequest {
    @NotBlank
    private String accountHolderName;

    private Double balance;

    public Account ToAccount()
    {
        return Account.builder()
                .accountHolderName(this.accountHolderName)
                .balance(this.balance)
                .build();
    }
}
