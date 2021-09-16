/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;
import bugbusterlibrary.EntityManagerFactoryHandler;
import bugbusterlibrary.entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Takudzwa Mzembegwa
 * @author Sango
 */
public class CategoryDao {

    /**
      * @return list of all Categories
      */
    public List<Category> findAll(){Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em =  EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<Category> category = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        return category;
    } 
    
    /**
     * Add a category in the category list
     * @param category the category to be persisted 
     */
    public void persist(Category category) {
        EntityManager em =  EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }  
    
    public void delete(Category categoryId) 
   {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category category = em.find(Category.class, categoryId);
        
       if (category != null) {
            em.getTransaction().begin();
            em.remove(category);
            em.getTransaction().commit();
        }
    }

    public void update(Category category)
    {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }
}
