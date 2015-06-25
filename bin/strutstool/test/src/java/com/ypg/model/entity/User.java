package com.ypg.model.entity;

import com.framework.util.validator.constraints.NotNull;
import com.framework.util.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
// generator:imports

@Indexed
public class User implements Serializable {
    @DocumentId
    private int id;

    @Field(index=Index.TOKENIZED,store=Store.YES)
    private String name;

    @Field(index=Index.TOKENIZED,store=Store.YES)
    private String passw;

    @Field(index=Index.UN_TOKENIZED, store=Store.YES)
    @DateBridge(resolution=Resolution.SECOND)
    private Date registration;

    // generator:attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @XSSFilter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @XSSFilter
    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @NotNull
    @XSSFilter
    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    // generator:methods
}