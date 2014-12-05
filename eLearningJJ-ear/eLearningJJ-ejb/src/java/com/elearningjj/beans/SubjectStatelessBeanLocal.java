/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import com.elearningjj.entities.Subject;
import javax.ejb.Local;

/**
 * Local interface of subject backing bean 
 * @author Milan
 */
@Local
public interface SubjectStatelessBeanLocal {
    void addSubject(Subject obj);
    public void createFolder(String folder);
}
