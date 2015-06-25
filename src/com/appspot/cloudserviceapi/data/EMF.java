package com.appspot.cloudserviceapi.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
    
    public static <T> T save(T entity) {
        get().createEntityManager().merge(entity);
        return entity;
    }
}