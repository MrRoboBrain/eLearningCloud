/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearningjj.beans;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Milan
 */
@Local
public interface DownloadStatelessBeanLocal {
    public List<String> getFilesInFolder(String folder);
    public void downloadSelected(Map<String, Boolean> selectedFiles);
}
