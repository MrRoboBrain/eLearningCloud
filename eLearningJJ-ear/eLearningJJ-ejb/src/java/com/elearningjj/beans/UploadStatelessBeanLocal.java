/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import com.elearningjj.entities.Subject;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.Part;

/**
 * Local interface of backing upload bean
 * @author Milan
 */
@Local
public interface UploadStatelessBeanLocal {
    public String uploadFile(String folder, Part selectedUploadFile);
    public String getFilename(Part part);
    public List<Subject> getSubjectCodeList();
    
}
