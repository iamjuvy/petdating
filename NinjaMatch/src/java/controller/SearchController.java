/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.MemberFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.MemberAccount;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import util.DateUtil;
import util.GoogleGeocode;
import util.Location;

/**
 *
 * @author FrancisAerol
 */
@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchController implements Serializable {

    @EJB
    private MemberFacade ejbMemberFacade;

    @EJB
    private TempCache t;
    private MapModel simpleModel;

    private int zoom;
    private String coords;

    private final DateUtil dateUtil = DateUtil.getInstance();
    private final GoogleGeocode geoUtil = GoogleGeocode.getInstance();

    private MemberAccount member;

    @PostConstruct
    void init() {
        member = t.getMember();
        zoom = 4;
        coords = "37.6,-95.665";

    }

    public void searchNearMe() {
        simpleModel = new DefaultMapModel();
        List<MemberAccount> res = ejbMemberFacade.getStateMembers(member);
        coords = member.getAddress().getGeoCode();
        //add otherstuff here
        zoom = 13;

    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

}
