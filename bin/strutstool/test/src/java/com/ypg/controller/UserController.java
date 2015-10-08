package com.ypg.controller;

// generator:imports

import com.ypg.model.entity.User;
import com.ypg.model.search.UserSearchMap;
import com.ypg.model.service.UserService;
import com.ypg.model.service.UserServiceImpl;
import com.framework.util.pagination.manager.PagingLookupManagerException;
import com.framework.util.search.EntitySearchMap;
import com.framework.util.struts.StrutsController;
import com.framework.util.repository.RepositoryException;
import com.framework.util.search.SearchAware;
import com.framework.util.validator.ValidatorException;
import com.opensymphony.xwork2.ModelDriven;

public class UserController extends StrutsController 
        implements ModelDriven<User>, SearchAware {
    
    // generator:attributes

    private UserService userService;
    private User user = new User();
    private Integer userId;

    // Actions =================================================================
    // generator:actions
    
    public String index() {
        try {
            paginatedList = paginateListFactory.getPaginatedListFromRequest(request);
            paginatedList.setPageSize(Integer.parseInt(getText("table.pagesize")));

            if (isSearch()) {
                paginatedList = pagingManager.getSearchRecordsPage(searchParams, paginatedList);
            } else {
                paginatedList = pagingManager.getAllRecordsPage(paginatedList);
            }
        } catch (PagingLookupManagerException ex) {
            errorHandler(ex);
        }
        
        statusHandler();
        return SUCCESS;
    }

    public String edit() {
        try {
        	// generator:loaders

        	
            if (isSave()) {
                getUserService().save(user);
                return SUCCESS_SAVE;
            } else {
                user = getUserService().findById(userId);
                if (user == null) {
                    return NOT_FOUND;
                }
            }
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public String add() {
        try {
        	// generator:loaders

        	
            if (isSave()) {
                getUserService().save(user);
                return SUCCESS_SAVE;
            }
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public void delete() {
        try {
            getUserService().delete(userId);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
    }

    // Implemented interface methods ===========================================

    public User getModel() {
        return user;
    }

    public EntitySearchMap getEntitySearchMap() {
        return new UserSearchMap();
    }

    // Getters and Setters =====================================================
    // generator:accessors

    
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
