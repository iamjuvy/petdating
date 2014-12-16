/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.AdminAccount;
import model.MemberAccount;
import model.UserAccount;

/**
 *
 * @author atan
 */
@Stateless
public class MemberFacade extends AbstractFacade<MemberAccount> {

    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public MemberFacade() {
        super(MemberAccount.class);
    }

    public List<MemberAccount> getStateMembers(MemberAccount m) {
        String jpql = "SELECT m FROM MemberAccount m WHERE m.address.state = :state";
        Query query = em.createQuery(jpql);
        query.setParameter("state", m.getAddress().getState());
        List<MemberAccount> res = query.getResultList();
        return res;
    }
    public boolean isUserNameExist(String username) {
        String jpql = "SELECT m FROM MemberAccount m WHERE m.userName = :username";
        Query query = em.createQuery(jpql);
        query.setParameter("username",username);
        List<MemberAccount> res = query.getResultList();
        if(res.isEmpty())
            return false;
        else
            return true;
    }
    public MemberAccount findByUsername(String username){
        try{
            String jpql = "SELECT c FROM MemberAccount c WHERE c.userName = :username";
            Query query = getEntityManager().createQuery(jpql, UserAccount.class);
            query.setParameter("username", username);
            return (MemberAccount)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    public MemberAccount findByUsernamePassword(String username, String password){
        try{
            String jpql = "SELECT c FROM MemberAccount c WHERE  c.userName = :username AND c.password = :password";
            Query query = getEntityManager().createQuery(jpql, UserAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return (MemberAccount)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
