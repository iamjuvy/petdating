/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.AdminAccountFacade;
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

/**
 *
 * @author yyun
 */
@ManagedBean
@SessionScoped
public class AdminController {
    @Inject 
    private AdminAccountFacade adminFacade;
    private AdminAccount admin;
    private PieChartModel chartAgesModel;
    private PieChartModel chartGendersModel;
    
    @Inject
    private AccountManager accountManager;
    
    @PostConstruct
    public void init(){
        admin = new AdminAccount();
        createAnimatedModels();
    }
    
    public String prepareEdit(AdminAccount u){
        admin = u;
        return "edit_admin";
    }
    public String remove(AdminAccount u){
        adminFacade.remove(u);
        return "manage_admin";
    }
    public String edit(){
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
                adminFacade.edit(admin);
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
    
    public List<AdminAccount> getAllAdmins(){
        return adminFacade.findAll();
    }
}
