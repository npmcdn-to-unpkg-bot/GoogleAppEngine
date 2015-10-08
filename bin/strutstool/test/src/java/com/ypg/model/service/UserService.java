package com.ypg.model.service;

import com.ypg.model.entity.User;
import com.framework.util.repository.RepositoryException;
import com.framework.util.validator.ValidatorException;
import java.util.List;

public interface UserService {
    public void save(User user) throws RepositoryException, ValidatorException;
    public void delete(Integer id) throws RepositoryException;
    public List<User> findAll() throws RepositoryException;
    public List<User> findAll(String orderBy, String orderType) throws RepositoryException;
    public User findById(Integer id) throws RepositoryException;

    public List<User> findAllByExample(User user) throws RepositoryException;
    public List<User> findAllWithPagination(int min, int max, String orderBy, String orderType) throws RepositoryException;

    public Integer count() throws RepositoryException;
}
