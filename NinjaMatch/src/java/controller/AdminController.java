/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.AdminAccountFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.AdminAccount;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author yyun
 */
@ManagedBean
@SessionScoped
public class AdminController {
    private AdminAccount admin;
    private PieChartModel chartAgesModel;
    private PieChartModel chartGendersModel;
    
    private WebTarget rest_web_target;
    private Client rest_client;
    private static final String BASE_URI = "http://localhost:8080/NinjaMatch/restful";
    
    @Inject
    private AccountManager accountManager;
    
    @PostConstruct
    public void init(){
        admin = new AdminAccount();
        rest_client = javax.ws.rs.client.ClientBuilder.newClient();
        rest_web_target = rest_client.target(BASE_URI).path("admin");
        createAnimatedModels();
    }
    
    public String prepareEdit(AdminAccount u){
        admin = u;
        return "edit_admin";
    }
    public String remove(AdminAccount u)  throws ClientErrorException{
        rest_web_target.path(java.text.MessageFormat.format("{0}", new Object[]{u.getId().toString()})).request().delete();
        return "manage_admin";
    }
    public String edit() throws ClientErrorException{
        FacesContext ctx = FacesContext.getCurrentInstance();
        try{
            boolean found_error = false;
            if (admin.getFirstName()==null || "".equals(admin.getFirstName())){
                ctx.addMessage("mainForm:bookForm:first_name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid first name", "First name is required"));
                found_error = true;
            }
            if (admin.getLastname()==null || "".equals(admin.getLastname())){
                ctx.addMessage("mainForm:bookForm:last_name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid last name", "Last name is required"));
                found_error = true;
            }
            if (admin.getPassword()==null || "".equals(admin.getPassword())){
                ctx.addMessage("mainForm:bookForm:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid password", "Password is required"));
                found_error = true;
            }
            if (found_error == false){
                rest_web_target.path(java.text.MessageFormat.format("{0}", new Object[]{admin.getId()})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(admin, javax.ws.rs.core.MediaType.APPLICATION_XML));
                return "manage_admin";
            }
            return null;
        }catch(Exception ex){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
            return null;
        }
    }

    /**
     * @return the admin
     */
    public AdminAccount getAdmin() {
        return admin;
    }
    public void setAdmin(AdminAccount admin) {
        this.admin = admin;
    }
    public PieChartModel getChartAgesModel() {
        return chartAgesModel;
    }
 
    public PieChartModel getChartGendersModel() {
        return chartGendersModel;
    }
 
    private void createAnimatedModels() {
        chartAgesModel = new PieChartModel();
         
        chartAgesModel.set("Teenagers", 540);
        chartAgesModel.set("Adults", 325);
        chartAgesModel.set("Olds", 702);
        chartAgesModel.setTitle("Members by Ages");
        chartAgesModel.setLegendPosition("w");
        chartAgesModel.setShowDataLabels(true);
        chartAgesModel.setDiameter(150);
        
        chartGendersModel = new PieChartModel();
        chartGendersModel.set("Boys", 540);
        chartGendersModel.set("Girls", 325);
        chartGendersModel.setTitle("Members By Gender");
        chartGendersModel.setLegendPosition("e");
        chartGendersModel.setShowDataLabels(true);
        chartGendersModel.setDiameter(150);
    }
    
    public List<AdminAccount> getAllAdmins()  throws ClientErrorException{
        GenericType<List<AdminAccount>> gType = new GenericType<List<AdminAccount>>(){}; 
        WebTarget resource = rest_web_target;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(gType);
    }
}
