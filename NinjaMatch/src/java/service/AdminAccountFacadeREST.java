/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.AdminAccount;
import model.UserAccount;

/**
 *
 * @author YunYusin
 */
@Stateless
@Path("admin")
public class AdminAccountFacadeREST extends AbstractFacade<AdminAccount> {
    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    public AdminAccountFacadeREST() {
        super(AdminAccount.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(AdminAccount entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, AdminAccount entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public AdminAccount find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @GET
    @Path("username/{userName}")
    @Produces({"application/xml", "application/json"})
    public AdminAccount findByUsername(@PathParam("userName") String userName) {
        String jpql = "SELECT c FROM UserAccount c WHERE c.userName LIKE :username";
        Query query = getEntityManager().createQuery(jpql, UserAccount.class);
        query.setParameter("username", userName);
        return (AdminAccount)query.getSingleResult();
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<AdminAccount> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<AdminAccount> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
