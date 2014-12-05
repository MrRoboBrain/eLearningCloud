package com.elearningjj.jsfbeans;

import com.elearningjj.beans.UploadStatelessBeanLocal;
import com.elearningjj.entities.Subject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

//@ManagedBean(name="uploadManagedBean")
//@SessionScoped
@Named("uploadManagedBean")
@SessionScoped
public class UploadManagedBean implements Serializable {
    
    @EJB
    private UploadStatelessBeanLocal uploadStatelessBean;
    
    private Part fileToUpload;
    private String categoryToUpload;
    
    /**
     * Init constructor
     */
    public UploadManagedBean() {
    }
    
    /**
     * Setter for file
     * @param fileToUpload 
     */
    public void setFileToUpload(Part fileToUpload){
        this.fileToUpload = fileToUpload;
    }
    
    /**
     * Getter for file
     * @return fileToUpload
     */
    public Part getFileToUpload(){
        return fileToUpload;
    }
    
    /**
     * Setting category
     * @param categoryToUpload 
     */
    public void setCategoryToUpload(String categoryToUpload){
        this.categoryToUpload = categoryToUpload;
    }
    
    /**
     * Getting category
     * @return categoryToUpload
     */
    public String getCategoryToUpload(){
        return categoryToUpload;
    }
    
    /**
     * This method sent the current values of uploadBean's attributes to backging bean in 
     * order to do the upload magic.
     */
    public void uploadFile(){
        uploadStatelessBean.uploadFile(categoryToUpload, fileToUpload);
    }
    
    /**
     * This method converts the subject's list type to string in order to get
     * the proper output format in the select tags. 
     * 
     * @return subjects list
     */
    public List<String> getSubjectCodes(){
       List<String> subjectsToString = new ArrayList<String>();
       for (Subject sub : uploadStatelessBean.getSubjectCodeList()) {
            subjectsToString.add(sub.getSubjectName());
       }
       return subjectsToString;
    }
    
}
