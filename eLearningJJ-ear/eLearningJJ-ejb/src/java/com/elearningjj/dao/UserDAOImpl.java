/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.dao;

import com.elearningjj.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * DAO implementation of IUserDAO interface
 * @author Milan
 */
public class UserDAOImpl implements IUserDAO {

    /* Creating entity manager factory, manager & tx */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("eLearningJJ_PU");
    EntityManager manager    = emf.createEntityManager();
    EntityTransaction tx = manager.getTransaction();

    /**
     * Method that is being invoked by login process. This method is trying 
     * to find the valid User object from DB. If not it returns NULL
     * 
     * @param obj
     * @return User/null
     */
    @Override
    public User find(User obj) {
        User user = null;
        
        try {
            user = manager.createNamedQuery("User.findUser", User.class).
                    setParameter("userName", obj.getUserName()).
                    setParameter("userPassword", obj.getUserPassword()).
                    getSingleResult();
            
        } catch (Exception e ){ 
            System.out.println("> Exception happend in find(User obj) method, UserDAOImpl class! " + e.toString());
        }

        return user;
    }

    /**
     * This method is invoked by administrator when new user is need to be added.
     * 
     * @param obj 
     */
    @Override
    public void add(User obj) {
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
    
}
