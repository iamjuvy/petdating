/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PhotoFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import model.MemberAccount;
import model.Photo;
import org.primefaces.event.CaptureEvent;
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
    private String filename;

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
        System.out.println("=====" + member.getFirstName());
        Photo p = ejbPhotoFacade.getCurrentImage(member);
        if (p != null) {
            currentPicture = imageUtil.getStreamed(p.getImage());
        } else {
            currentPicture = null;
        }
        return currentPicture;
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
                + File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".png";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }
}
