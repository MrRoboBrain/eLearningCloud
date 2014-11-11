package com.elearningjj.jsfbeans;

import com.elearningjj.beans.UserStatelessBeanLocal;
import com.elearningjj.entities.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * User managed bean
 * @author Milan
 */
@ManagedBean (name="userManagedBean")
@SessionScoped
public class UserManagedBean {

    /* Instance of User's business logic */
    @EJB
    UserStatelessBeanLocal userStateless;
    
    /* User model attr */
    private String username;
    private String password;
    private boolean isActiveSession;
    
    /* Singleton pattern, holding data for model User */
    private static User userModel = new User();
    
    /**
     * Constructor
     */
    public UserManagedBean() {

    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Getter for active session
     * @return true/false
     */
    public boolean getIsActiveSession() {
        return isActiveSession;
    }

    /**
     * Setter for username
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Setter for active session
     * @param active 
     */
    public void setActiveSession(boolean active) {
        this.isActiveSession = active;
    }
    
    /* Ovaj metod ce se koristiti kasnije */
    public static FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }
    
    /* Ovaj metod ce se koristiti kasnije */
    protected static void setCurrentInstance(FacesContext context) {
    }
    
    /**
     * This method returns the current active instance of User and it is used 
     * to pass the data model to backing bean. If user instance is not yet 
     * created it creates a new one.
     * 
     * @return userModel
     */
    public static User getInstance() {
        if (userModel == null)
            userModel = new User();
        return userModel;
    }
    
    /**
     * This method is invoked by the user on login.xhtml page. When user submit
     * the form, the server side is checking for the data than sending it to 
     * backing bean in order to check whether user exist in the database.
     * 
     * @return response string
     */
    public String getLoginResponse() throws NullPointerException {
        
        String response = "";
        
        /* check whether the data is set on the server side */
        if (username != null && password != null) {
            
            /* Setting and prepairing data to being sent to backing bean */
            userModel.setUserName(username);
            userModel.setUserPassword(password);
                        
            /* Check for user obj existance in the db and login or fail */
            if(userStateless.login(userModel) != null) {
                
                response = "You have been logged in sucessfully.";
                userModel = userStateless.login(userModel);
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
                
                try {
                    isActiveSession = true;
                    context.redirect("dashboard.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { 
                
                response = "Incorrect username or password.";
            }
        } 
        
        return response;
    }

    /**
     * This method is called on each of the restricted pages in order to check
     * whether the session is active or not. This will result either continuing 
     * or redirecting user to login page.
     */
    public void permission() {
        if(isActiveSession == false) {
            System.out.println("*** The user has no permission to visit this page. *** ");
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("*** The session is still active. User is logged in. *** ");
        }
    }
    
    /**
     * Destroys the current user's session
     */
    public void sessionDestroy() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        isActiveSession = false;
        try {
            context.redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
