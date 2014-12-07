/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.ImageFileController;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author FrancisAerol
 */
@Singleton
public class ImageUtil {

    private ImageUtil imageUtil;

    @PostConstruct
    void init() {
        imageUtil = new ImageUtil();
    }

    protected ImageUtil getImageUtil() {
        return imageUtil;
    }

    public StreamedContent getStreamed(byte[] bytes) {
        StreamedContent foto = null;
        try {
            foto = new DefaultStreamedContent(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foto;
    }

    public byte[] convertToBytes(InputStream is) {
        byte[] conBytes = null;
        try {
            conBytes = IOUtils.toByteArray(is);
        } catch (IOException ex) {
            Logger.getLogger(ImageFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conBytes;
    }
}
