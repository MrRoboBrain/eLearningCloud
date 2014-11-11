/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import com.elearningjj.dao.IUserDAO;
import com.elearningjj.dao.UserDAOImpl;
import com.elearningjj.entities.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 * User business logic
 * @author Milan
 */
@Stateless
public class UserStatelessBean implements UserStatelessBeanLocal {
    
    /** 
     * Instantiating DAO interface and returning it method's value (User obj)
     * This method is called and used in WAR container
     * 
     * @param obj
     * @return User
     */
    @Override
    public User login(User obj) {
        IUserDAO userDao = new UserDAOImpl();
        return userDao.find(obj);
    }
}
