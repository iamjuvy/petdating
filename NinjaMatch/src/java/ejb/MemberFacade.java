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
}
