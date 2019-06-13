package com.yauhenl.cloudapp.account.service;

import com.yauhenl.cloudapp.account.client.AuthServiceClient;
import com.yauhenl.cloudapp.account.domain.Account;
import com.yauhenl.cloudapp.account.domain.User;
import com.yauhenl.cloudapp.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;

public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AuthServiceClient authClient;

    @Autowired
    private AccountRepository repository;

    public Account findByName(String accountName) {
        Assert.hasLength(accountName);
        return repository.findByName(accountName);
    }

    public Account create(User user) {
        Account existing = repository.findByName(user.getUsername());
        Assert.isNull(existing, "account already exists: " + user.getUsername());

        authClient.createUser(user);

        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new Date());

        repository.save(account);

        log.info("new account has been created: " + account.getName());

        return account;
    }

    public void saveChanges(String name, Account update) {
        Account account = repository.findByName(name);
        Assert.notNull(account, "can't find account with name " + name);

        account.setNote(update.getNote());
        account.setLastSeen(new Date());
        repository.save(account);

        log.debug("account {} changes has been saved", name);
    }
}
