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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Milan
 */
@Stateless
public class DownloadStatelessBean implements DownloadStatelessBeanLocal {

    @Override
    public List<String> getFilesInFolder(String folder) {
        List<String> filePaths = new ArrayList<String>();
        File targetFolder = new File("C:/uploads/" + folder + "/");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(targetFolder.listFiles()));
        for(int i=0; i<files.size(); i++){
            filePaths.add(files.get(i).getPath());
        }
        return filePaths;
    }

    @Override
    public void downloadSelected(Map<String, Boolean> selectedFiles) {
        ISubjectDAO subjectDao = new SubjectDAOImpl();

        List<Subject> subjects = subjectDao.getAll();
        
        for (Subject sub : subjects) {
            System.out.println("~ test: " + sub.getSubjectCode());
        }
        
        List<String> allSubjectNames = new ArrayList<String>();
        for(int i=0; i<subjects.size(); i++){
            allSubjectNames.add(subjects.get(i).getSubjectName());
        }
        
        List<String> allFiles = new ArrayList<String>();
        for (int i = 0; i < allSubjectNames.size(); i++) {
            List<String> filesInFolder = getUploadedByFolder(allSubjectNames.get(i));
            for (int z = 0; z < filesInFolder.size(); z++) {
                allFiles.add(filesInFolder.get(z) + "");
            }
        }
        
        for (int i = 0; i < allFiles.size(); i++) {
            if (selectedFiles.get(allFiles.get(i))) {
                downloadFile(allFiles.get(i));
                break;
            }
        }
    }
    
    public List<String> getUploadedByFolder(String folder) {
        List<String> filePaths = new ArrayList<String>();
        
        File f = new File("C:/uploads/" + folder + "/");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        for(int i=0; i<files.size(); i++){
            filePaths.add(files.get(i).getPath());
        }
        return filePaths;
    }
    
    private void downloadFile(String fileAdress) {
        File file = new File(fileAdress);
        String[] fileName = fileAdress.split("\\\\");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName[3]);
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}
