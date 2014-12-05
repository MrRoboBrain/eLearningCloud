/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import com.elearningjj.dao.ISubjectDAO;
import com.elearningjj.dao.SubjectDAOImpl;
import com.elearningjj.entities.Subject;
import java.io.File;
import javax.ejb.Stateless;

/**
 *
 * @author Milan
 */
@Stateless
public class SubjectStatelessBean implements SubjectStatelessBeanLocal {

    /**
     * Subject's DAO instantiation and creating a new folder (if it does not exist)
     * @param obj 
     */
    @Override
    public void addSubject(Subject obj) {
        ISubjectDAO subjectDao = new SubjectDAOImpl();
        createFolder(obj.getSubjectName());
        subjectDao.add(obj);
    }
    
    /**
     * This method is being invoked by upload method. It's used to create the 
     * directory (category) that will holds all the relevant data for it.
     * 
     * @param folder 
     */
    @Override
    public void createFolder(String folder) {
        File file = new File("C:/uploads/" + folder);
	if (!file.exists()) {
		if (file.mkdir()) {
			System.out.println("Directory is created!");
		} else {
			System.out.println("Failed to create directory!");
		}
	} else {
            System.out.println("Directory exist!");
        }
    }
    
}
