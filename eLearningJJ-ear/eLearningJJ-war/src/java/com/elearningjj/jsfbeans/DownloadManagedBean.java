/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.jsfbeans;

import com.elearningjj.beans.DownloadStatelessBeanLocal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Milan
 */
@Named("downloadManagedBean")
@RequestScoped
public class DownloadManagedBean implements Serializable {
    
    /* download's backing bean ejb injection */
    @EJB
    private DownloadStatelessBeanLocal downloadStatelessBean;

    /* selected file's hash */
    private Map<String, Boolean> selectedFiles = new HashMap<String, Boolean>();
    
    /**
     * Creates a new instance of DownloadManagedBean
     */
    public DownloadManagedBean() {
    }
    
    /**
     * 
     * @param folder
     * @return 
     */
    public List<String> getFilesInFolder(String folder){
        return downloadStatelessBean.getFilesInFolder(folder);
    }
    
    /**
     * Downloading method.
     */
    public void downloadSelected() {
        downloadStatelessBean.downloadSelected(selectedFiles);
    }
    
    public void setSelectedFiles(Map<String, Boolean> toDelete) {
        this.selectedFiles = toDelete;
    }

    public Map<String, Boolean> getSelectedFiles() {
        return selectedFiles;
    }
}
