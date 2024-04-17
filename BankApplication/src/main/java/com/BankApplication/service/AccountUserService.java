package com.BankApplication.service;

import com.BankApplication.model.AccountUser;
import com.BankApplication.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountUserService {

    @Autowired
    private final AccountUserRepository accountUserRepository;

    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    public ResponseEntity<List<AccountUser>> getAllUsers() {
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> getUserById(int id) {
        Optional<AccountUser> user = accountUserRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public AccountUser getUserByUsername(String username) {
        return accountUserRepository.findByUserName(username);
    }

    public ResponseEntity<AccountUser> createAccount(AccountUser user) {
        AccountUser savedAccount = accountUserRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    public Optional<AccountUser> updateUser(int id, AccountUser user) {
        return accountUserRepository.findById(id);
    }

    public ResponseEntity<Void> deleteUser(int id) {
        Optional<AccountUser> accountOptional = accountUserRepository.findById(id);
        if (accountOptional.isPresent()) {
            accountUserRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

