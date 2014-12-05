/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.ProfileView;

/**
 *
 * @author atan
 */
@Stateless
public class ProfileViewFacade extends AbstractFacade<ProfileView> {
    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfileViewFacade() {
        super(ProfileView.class);
    }
    
}
