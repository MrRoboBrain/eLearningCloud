/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import com.elearningjj.dao.FilesDAOImpl;
import com.elearningjj.dao.IFilesDAO;
import com.elearningjj.entities.Subject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

/**
 * Upload Business Logic
 * @author Milan
 */
@Stateless
public class UploadStatelessBean implements UploadStatelessBeanLocal {

    /**
     * This method is responsible for uploading files into specific directory 
     * on the disc. 
     * 
     * @param folder
     * @param selectedUploadFile
     * @return 
     */
    @Override
    public String uploadFile(String folder, Part selectedUploadFile) {
        try{
            InputStream inputStream = selectedUploadFile.getInputStream();          
            FileOutputStream outputStream = new FileOutputStream("C:/uploads/" + folder + "/" + getFilename(selectedUploadFile));  

            byte[] buffer = new byte[4096];          
            int bytesRead = 0;  
            while(true) {                          
                bytesRead = inputStream.read(buffer);  
                if(bytesRead > 0) {  
                    outputStream.write(buffer, 0, bytesRead);  
                }else {  
                    break;  
                }                         
            }  
            outputStream.close();  
            inputStream.close();
            
            return "File: " + getFilename(selectedUploadFile) + " uploaded.";
        }catch(Exception ex){
            return "Error: Nije odabran file.";
        }
    }

    /**
     * This method returns the actual file name in string that is used for upload
     * 
     * @param part
     * @return 
     */
    @Override
    public String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {  
            if (cd.trim().startsWith("filename")) {  
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }  
        }  
        return "Error: Problem pri upload-u.";  
    }

    /** 
     * Instantiating DAO interface and returning it method's value (Subject obj)
     * This method is called and used in WAR container.
     * 
     * @return subjects list
     */
    @Override
    public List<Subject> getSubjectCodeList() {
       IFilesDAO filesDao = new FilesDAOImpl();
       return filesDao.getAll();
    }

    

}
