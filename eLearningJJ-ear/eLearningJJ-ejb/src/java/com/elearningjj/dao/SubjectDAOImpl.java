/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.dao;

import com.elearningjj.entities.Subject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Subject's DAO implementation
 * @author Milan
 */
public class SubjectDAOImpl implements ISubjectDAO {

    /* Creating entity manager factory, manager & tx */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("eLearningJJ_PU");
    EntityManager manager    = emf.createEntityManager();
    EntityTransaction tx = manager.getTransaction();
    
    /* Subjects list */
    List<Subject> subjects = new ArrayList<Subject>();
    
    /**
     * Method that is being invoked by admin on it's upload page when the new 
     * category is need to be added to the list.
     * 
     * @param obj
     */
    @Override
    public void add(Subject obj) {
        try {
            tx.begin();
            
            manager.persist(obj);

            tx.commit();
            manager.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Oops, an error occured while trying to sent a message." + e.getMessage());
        }
    }

    /**
     * Fetching the list of all subjects
     * @return subjects
     */
    @Override
    public List<Subject> getAll() {
        subjects = manager.createNamedQuery("Subject.findAll", Subject.class).getResultList();
        return subjects;
    }
    
}
