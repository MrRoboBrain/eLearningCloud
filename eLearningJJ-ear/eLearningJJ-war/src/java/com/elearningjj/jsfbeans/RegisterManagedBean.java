/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.jsfbeans;


import com.elearningjj.beans.UserStatelessBeanLocal;
import com.elearningjj.entities.User;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Milan
 */
@Named("registrationManagedBean")
@RequestScoped
public class RegisterManagedBean {
    
    /* user's backing bean ejb injection */
    @EJB
    private UserStatelessBeanLocal userStatelessBean;

    /* User model attr */
    private String username;
    private String password;
    private String roles;
    
    /**
     * Creates a new instance of RegisterManagedBean
     */
    public RegisterManagedBean() {
    }

    /**
     * Getter for username
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for roles
     * @return roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Setter for roles
     * @param roles 
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    /**
     * This method is being invoked by administrator on the admin page. When the
     * admin submit the form, the data is being checked and sent to the backing 
     * bean where all the magic happends.
     * 
     * @return response
     */
    public String getRegisterResponse() {
        
        String response = "";
        
        if(username != null && password != null && roles != null) {
            
            /* Prepairing the object to be sent */
            User user = new User();
            user.setUserName(username);
            user.setUserPassword(password);
            user.setUserRoles(roles);
            
            /* Adding new user */
            userStatelessBean.registerUser(user);
            
            response = "One new user has been added sucessfully.";
        } else {
            response = "Please, enter the valid data into fields!";
        }
        
        return response;
    }    
}
