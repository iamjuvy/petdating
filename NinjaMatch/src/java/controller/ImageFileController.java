/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import util.TempStorage;
import ejb.PhotoFacade;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.MemberAccount;
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

    private final TempStorage t = TempStorage.getInstance();
    private StreamedContent streamedContent;
    private StreamedContent currentPicture;
    private UploadedFile file;
    private MemberAccount member;
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
        this.file = file;
        bool = true;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void upload() {
        if (file != null) {

            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Photo photo = new Photo();
            try {
                bytes = convertToBytes(file.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(ImageFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            streamedContent = getStreamed(bytes);
            photo.setImage(bytes);
            photo.setMember(t.getMember());

            ejbPhotoFacade.create(photo);

        }
    }

    public StreamedContent getStreamedContent() {
        streamedContent = getStreamed(bytes);
        return streamedContent;
    }

    public StreamedContent getCurrentPicture() {
        Photo p = ejbPhotoFacade.getCurrentImage(t.getMember());
        if (p != null) {
            currentPicture = getStreamed(p.getImage());
        } else {
            currentPicture = null;
        }

        return currentPicture;
    }

    private StreamedContent getStreamed(byte[] bytes) {
        StreamedContent foto = null;
        try {
            foto = new DefaultStreamedContent(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foto;
    }

    private byte[] convertToBytes(InputStream is) {
        byte[] conBytes = null;
        try {
            conBytes = IOUtils.toByteArray(is);
        } catch (IOException ex) {
            Logger.getLogger(ImageFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conBytes;
    }

}
