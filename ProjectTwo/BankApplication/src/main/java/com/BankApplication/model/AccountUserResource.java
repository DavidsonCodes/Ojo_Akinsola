package com.BankApplication.model;

import org.springframework.hateoas.RepresentationModel;

public class AccountUserResource extends RepresentationModel<AccountUserResource> {
    private AccountUser user;

    public AccountUserResource(AccountUser user) {
        this.user = user;
    }

    public AccountUser getUser() {
        return user;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }
}

