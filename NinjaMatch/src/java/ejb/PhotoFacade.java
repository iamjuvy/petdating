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
import model.Photo;

/**
 *
 * @author atan
 */
@Stateless
public class PhotoFacade extends AbstractFacade<Photo> {

    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoFacade() {
        super(Photo.class);
    }

    public Photo getCurrentImage(MemberAccount member) {
        String sql = "SELECT p FROM Photo p WHERE p.member = :mem";
        Query query = em.createQuery(sql);
        query.setParameter("mem", member);
        List<Photo> queryList = query.getResultList();

        if (!queryList.isEmpty()) {
            return queryList.get(queryList.size() - 1);
        }
        return null;
    }

}
