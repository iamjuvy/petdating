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
import model.MemberAccount;
import model.UserAccount;

/**
 *
 * @author atan
 */
@Stateless
public class UserFacade extends AbstractFacade<UserAccount> {

    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(UserAccount.class);
    }

    public List<MemberAccount> validateUser(String username, String password) {
        String jpql = "SELECT c FROM UserAccount c WHERE c.userName = :custName AND c.password = :pass";
        Query query = em.createQuery(jpql, UserAccount.class);
        query.setParameter("custName", username);
        query.setParameter("pass", password);
        List<MemberAccount> queryList = query.getResultList();
        return queryList;
    }

}
