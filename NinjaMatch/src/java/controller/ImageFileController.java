/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PhotoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.MemberAccount;
import model.Photo;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import util.DateUtil;
import util.ImageUtil;

/**
 *
 * @author FrancisAerol
 */
@ManagedBean(name = "imageBean")
@SessionScoped
public class ImageFileController implements Serializable {

    @EJB
    private PhotoFacade ejbPhotoFacade;
    @EJB
    private ImageUtil imageUtil;
    @EJB
    private DateUtil dateUtil;

    private StreamedContent streamedContent;
    private StreamedContent currentPicture;
    private UploadedFile file;

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
                bytes = imageUtil.convertToBytes(file.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(ImageFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            streamedContent = imageUtil.getStreamed(bytes);
            photo.setImage(bytes);
            MemberAccount member = (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            photo.setMember(member);
            photo.setUploadDate(dateUtil.getCurrentDate());
            ejbPhotoFacade.create(photo);
        }
    }

    public StreamedContent getStreamedContent() {
        streamedContent = imageUtil.getStreamed(bytes);
        return streamedContent;
    }

    public StreamedContent getCurrentPicture() {
        MemberAccount member = (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        Photo p = ejbPhotoFacade.getCurrentImage(member);
        if (p != null) {
            currentPicture = imageUtil.getStreamed(p.getImage());
        } else {
            currentPicture = null;
        }
        return currentPicture;
    }
}
