/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PhotoFacade;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Member;
import model.Photo;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FrancisAerol
 */
@ManagedBean(name = "imageBean")
@SessionScoped
public class ImageFileController implements Serializable {

    @EJB
    private PhotoFacade ejbPhotoFacade;

    private StreamedContent streamedContent;
    private UploadedFile file;
    private Member member;
    private boolean bool = false;
    private byte[] bytes;

    @PostConstruct
    void init() {
        streamedContent = null;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        bool = true;
        this.file = file;

    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void upload() {
        if (file != null) {
            TempStorage t = TempStorage.getInstance();
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Photo photo = new Photo();
            try {
                bytes = IOUtils.toByteArray(file.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(ImageFileController.class.getName()).log(Level.SEVERE, null, ex);
            }

            photo.setImage(bytes);
            photo.setMember(t.getMember());

            try {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(bytes));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ejbPhotoFacade.create(photo);
            file = null;
            bool = false;
        }
    }

    public StreamedContent getStreamedContent() {
        try {
            streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return streamedContent;
    }

}
