/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.dao;

import com.elearningjj.entities.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * DAO implementation of IFilesDAO interface
 * @author Milan
 */
public class FilesDAOImpl implements IFilesDAO{

    /* Creating entity manager factory, manager & tx */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("eLearningJJ_PU");
    EntityManager manager    = emf.createEntityManager();
    EntityTransaction tx = manager.getTransaction();
    
    /* List of all subjects */
    List<Subject> subjects = new ArrayList<Subject>();
    
    /**
     * Method that is being invoked by upload page. This method returns the list
     * of available subjects (categories) for admin to upload files to. The result
     * is listed in the select html list
     * 
     * @return subjects
     */
    @Override
    public List<Subject> getAll() {
        subjects = manager.createNamedQuery("Subject.findAll", Subject.class).getResultList();
        return subjects;
    }

}
