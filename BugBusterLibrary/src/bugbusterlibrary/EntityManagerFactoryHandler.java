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

    private EntityManagerFactoryHandler() {
    }

    /**
     * A method to fetch an already created EntityManagerFactory. Done to reduce
     * stress on resources that can be done by frequently creating
     * EntityManagerFactory.
     * 
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }
}
