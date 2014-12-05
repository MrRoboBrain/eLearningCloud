/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.dao;

import com.elearningjj.entities.User;
import java.util.List;

/**
 * UserDAO's interface
 * @author Milan
 */
public interface IUserDAO {
    public User find(User obj);
    public void add(User obj);
}
