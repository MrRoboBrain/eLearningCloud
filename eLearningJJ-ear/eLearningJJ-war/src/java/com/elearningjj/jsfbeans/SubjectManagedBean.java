/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.jsfbeans;

import com.elearningjj.beans.SubjectStatelessBeanLocal;
import com.elearningjj.entities.Subject;
import com.elearningjj.entities.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("subjectManagedBean")
@RequestScoped
public class SubjectManagedBean implements Serializable{
    
    /* subject attributes */
    private String subjectCode;
    private String subjectName;
    
    /* jsf managed bean injection */
    @Inject
    UserManagedBean userManagedBean;
    
    /* subject's backing bean ejb injection */
    @EJB
    private SubjectStatelessBeanLocal subjectStatelessBean;
    
    
    /**
     * Creates a new instance of SubjectManagedBean
     */
    public SubjectManagedBean() {
    }

    /**
     * Getter for subject code
     * @return subjectCode
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * Setter for subject code
     * @param subjectCode 
     */
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    /**
     * Getter for subject name
     * @return subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Seter for subject name
     * @param subjectName 
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    /**
     * Creates a new subject/category
     */
    public void addSubject(){
        /* Setting up a new object to be sent to the backing bean */
        Subject obj = new Subject();
        obj.setUserId((User)UserManagedBean.getInstance());
        obj.setSubjectCode(subjectCode);
        obj.setSubjectName(subjectName);
        
        subjectStatelessBean.addSubject(obj);
    }
    
}
