/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;
import bugbusterlibrary.EntityManagerFactoryHandler;
import bugbusterlibrary.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Takudzwa Mzembegwa
 * @author Sango
 */
public class UserDao {
    
    /**
     * @return list of all Users
     */
    public List<User> findAll(){
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<User> user = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        return user;
    }

    /**
     * @param username the username of the user to be found  
     * @return User 
     */
    public User findByUsername(String username){
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        User user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getSingleResult();
        return user;
    }
    
    // funtion to delete a specific user. 
    public void deleteUser(Long userID) 
    {
       EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
       User user = em.find(User.class, userID); // search for the user by ID. 
        
       if (user != null) // see if they are in the database. 
       {
            em.getTransaction().begin();
            em.remove(user); // then remove them. 
            em.getTransaction().commit();
        }
    }

    // function to update a user's details. 
    public void updateUser(User myUser, List<Receipt> receiptList)
    {
        // set the new information. 
    	User user = myUser;
    	receipt.setReceiptList(receiptList);
        
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(user); // update it. 
        em.getTransaction().commit();
    }
    
}
