/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.AdminAccountFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class AdminController {
    @Inject 
    private AdminAccountFacade adminFacade;
    private AdminAccount admin;
    private PieChartModel chartAgesModel;
    private PieChartModel chartGendersModel;
    
    @PostConstruct
    public void init(){
        admin = new AdminAccount();
        createAnimatedModels();
    }
    
    public void save(){
        
    }

    /**
     * @return the admin
     */
    public AdminAccount getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
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
