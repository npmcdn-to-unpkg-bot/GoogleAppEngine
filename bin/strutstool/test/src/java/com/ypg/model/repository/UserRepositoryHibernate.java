package com.ypg.model.repository;

import com.ypg.model.entity.User;
import com.framework.util.hibernate.GenericRepositoryHibernate;

public class UserRepositoryHibernate extends GenericRepositoryHibernate<User, Integer> implements UserRepository {
    public UserRepositoryHibernate() {
        super(User.class);
    }
}
