/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Takudzwa Mzembegwa
 */
public class EntityManagerFactoryHandler {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("BugBusterLibraryPU");

    public EntityManagerFactoryHandler() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }
}
