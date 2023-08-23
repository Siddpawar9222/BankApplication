package com.spbank.bankbackendjwt1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spbank.bankbackendjwt1.model.AccountTransactions;
import com.spbank.bankbackendjwt1.model.Accounts;
import com.spbank.bankbackendjwt1.model.Cards;
import com.spbank.bankbackendjwt1.model.Loans;
import com.spbank.bankbackendjwt1.repository.AccountTransactionsRepository;
import com.spbank.bankbackendjwt1.repository.AccountsRepository;
import com.spbank.bankbackendjwt1.repository.CardsRepository;
import com.spbank.bankbackendjwt1.repository.LoanRepository;
import com.spbank.bankbackendjwt1.security.services.UserDetailsImpl;


//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private")
public class PrivateController {

	
	@Autowired
	private AccountsRepository accountsRepository ;
    

	 @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId(); 

//        Accounts accounts = accountsRepository.findByUserId(userId.intValue());
        Accounts accounts = accountsRepository.findByUserId(userId);

        if (accounts != null) {
            return ResponseEntity.ok(accounts); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found"); 
        }
    }


    
    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByUserIdOrderByTransactionDtDesc(userId);
        
        return accountTransactions;
    }

    
    @Autowired
    private CardsRepository cardsRepository;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/myCards")
    public List<Cards> getCardDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId(); 

        List<Cards> cards = cardsRepository.findByUserId(userId);
        
        return cards; 
    }

    @Autowired
    private LoanRepository loanRepository;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId(); 

        List<Loans> loans = loanRepository.findByUserIdOrderByStartDtDesc(userId);
        
        return loans;
    }

    
    
}
