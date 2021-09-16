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
    
    public Category findById(int Id){
        EntityManager em =  EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category category = em.createNamedQuery("Category.findByCategoryId", Category.class).setParameter("categoryId", Id).getSingleResult();
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
    
    // function to delete a category. 
    public void deleteCategory(Category categoryId) 
    {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category category = em.find(Category.class, categoryId); // search for the category by its ID. 
        
       if (category != null) // check if the category is in the database. 
       {
            em.getTransaction().begin();
            em.remove(category); // delete the category. 
            em.getTransaction().commit();
        }
    }

    // function to update a category. 
    public void updateCategory(Category category)
    {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(category); // call to update the category. 
        em.getTransaction().commit();
    }
}
