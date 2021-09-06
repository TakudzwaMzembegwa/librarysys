/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;
import bugbusterlibrary.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Takudzwa Mzembegwa
 */
public class UserDao {
    
    //Return list of all Users
    public List<User> findAllUsers(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<User> user = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        return user;
    }
    
}
