package com.ypg.model.service;

import com.ypg.model.repository.UserRepository;
import com.ypg.model.repository.UserRepositoryHibernate;
import com.ypg.model.entity.User;
import com.framework.util.repository.RepositoryException;
import com.framework.util.validator.Validator;
import com.framework.util.validator.ValidatorException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private Validator userValidator;

    public UserServiceImpl() {}

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Implemented interface methods ===========================================

    public void save(User user) throws RepositoryException, ValidatorException {
        getUserValidator().validate(user);
        getUserRepository().save(user);
    }

    public void delete(Integer id) throws RepositoryException {
        User user = getUserRepository().findById(id);
        getUserRepository().delete(user);
    }

    public List<User> findAll() throws RepositoryException {
        return getUserRepository().findAll();
    }

    public List<User> findAll(String orderBy, String orderType) throws RepositoryException {
        return getUserRepository().findAll(orderBy, orderType);
    }

    public User findById(Integer id) throws RepositoryException {
        return getUserRepository().findById(id);
    }
    
    public List<User> findAllByExample(User user) throws RepositoryException {
        return getUserRepository().findAllByExample(user);
    }

    public List<User> findAllWithPagination(int min, int max, String orderBy, String orderType) throws RepositoryException {
        return getUserRepository().findAllWithPagination(min, max, orderBy, orderType);
    }

    public Integer count() throws RepositoryException {
        return getUserRepository().count();
    }

    // Getters and Setters =====================================================

    public UserRepository getUserRepository() {
        if (userRepository == null) {
            setUserRepository(new UserRepositoryHibernate());
        }

        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Validator getUserValidator() {
        if (userValidator == null) {
            setUserValidator(new Validator<User>());
        }
        return userValidator;
    }

    public void setUserValidator(Validator userValidator) {
        this.userValidator = userValidator;
    }
}
