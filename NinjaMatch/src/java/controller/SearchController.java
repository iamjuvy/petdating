package controller;

import ejb.MemberFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.MemberAccount;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import util.GoogleGeocode;
import util.ImageUtil;
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
    private ImageUtil imageUtil;

    @EJB
    private GoogleGeocode geocoder;

    private MapModel simpleModel;
    private Marker marker;
    private MemberAccount searchMember;
    private MemberAccount member;
    private StreamedContent searchMemberImg;
    private String coords;

    private int zoom;

    @PostConstruct
    void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        member = (MemberAccount) ec.getSessionMap().get("user");
        zoom = 4;
        coords = "37.6,-95.665";

    }

    public void searchNearMe() {
        simpleModel = new DefaultMapModel();
        List<MemberAccount> res = ejbMemberFacade.getStateMembers(member);
        coords = member.getAddress().getGeoCode();

        for (MemberAccount mem : res) {
            Location loc;
            LatLng latlng;

            String detail = mem.getFirstName() + " " + mem.getLastname() + ":" + mem.getAddress().getStreet() + mem.getAddress().getCity();
            if (member.getId().equals(mem.getId())) {
                loc = geocoder.geocodeParser(mem.getAddress().getGeoCode());
                latlng = new LatLng(loc.getDLat(), loc.getDLng());
                simpleModel.addOverlay(new Marker(latlng, detail, mem, "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"));
            } else {
                loc = geocoder.geocodeParser(mem.getAddress().getGeoCode());
                latlng = new LatLng(loc.getDLat(), loc.getDLng());
                simpleModel.addOverlay(new Marker(latlng, detail, mem, "http://maps.google.com/mapfiles/ms/icons/purple-dot.png"));
            }
        }
        zoom = 8;
    }

    public StreamedContent getSearchMemberImg() {
        byte[] bytes = searchMember.getPhotos().get(0).getImage();
        searchMemberImg = imageUtil.getStreamed(bytes);
        return searchMemberImg;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        searchMember = (MemberAccount) marker.getData();
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

    public Marker getMarker() {
        return marker;
    }

    public MemberAccount getSearchMember() {
        return searchMember;
    }

    public void setSearchMember(MemberAccount searchMember) {
        this.searchMember = searchMember;
    }

}
