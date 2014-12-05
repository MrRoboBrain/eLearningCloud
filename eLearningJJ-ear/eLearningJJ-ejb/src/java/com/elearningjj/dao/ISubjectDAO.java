/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.dao;

import com.elearningjj.entities.Subject;
import java.util.List;

/**
 * Subject's DAO interface class
 * @author Milan
 */
public interface ISubjectDAO {
    void add(Subject obj);
    List<Subject> getAll();
}
