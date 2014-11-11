/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import com.elearningjj.entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface of backing user bean
 * @author Milan
 */
@Local
public interface UserStatelessBeanLocal {
    public User login(User obj);
}
