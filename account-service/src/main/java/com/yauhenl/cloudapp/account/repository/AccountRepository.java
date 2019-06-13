package com.yauhenl.cloudapp.account.repository;

import com.yauhenl.cloudapp.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Account findByName(String name);
}
