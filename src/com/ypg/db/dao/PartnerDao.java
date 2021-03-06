/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package com.ypg.db.dao;

import java.sql.Date;
import java.sql.Timestamp;

import com.spoledge.audao.db.dao.AbstractDao;
import com.spoledge.audao.db.dao.DaoException;

import com.ypg.db.dto.Partner;


/**
 * This is the DAO.
 *
 * @author generated
 */
public interface PartnerDao extends AbstractDao {

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Partner findByPrimaryKey( long id );

    /**
     * Finds a record.
     */
    public Partner findById( long id );

    /**
     * Finds records ordered by id.
     */
    public Partner[] findAll( );

    /**
     * Deletes a record identified by its primary key.
     * @return true iff the record was really deleted (existed)
     */
    public boolean deleteByPrimaryKey( long id ) throws DaoException;

    /**
     * Inserts a new record.
     * @return the generated primary key - id
     */
    public long insert( Partner dto ) throws DaoException;

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( long id, Partner dto ) throws DaoException;

}
