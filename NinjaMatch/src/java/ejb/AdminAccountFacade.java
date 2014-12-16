/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.AdminAccount;
import model.UserAccount;
import service.AdminLogger;

/**
 *
 * @author atan
 */
@Stateless
public class AdminAccountFacade extends AbstractFacade<AdminAccount> {
    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminAccountFacade() {
        super(AdminAccount.class);
    }
    
    public AdminAccount findByUsername(String username){
        try{
            String jpql = "SELECT c FROM AdminAccount c WHERE c.userName = :username";
            Query query = getEntityManager().createQuery(jpql, UserAccount.class);
            query.setParameter("username", username);
            return (AdminAccount)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    public AdminAccount findByUsernamePassword(String username, String password){
        try{
            String jpql = "SELECT c FROM AdminAccount c WHERE  c.userName = :username AND c.password = :password";
            Query query = getEntityManager().createQuery(jpql, UserAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return (AdminAccount)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
