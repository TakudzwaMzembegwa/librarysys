/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;
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
    
     //Return list of Category
    public List<Category> findAllCategories(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<Category> category = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        return category;
    } 
    
    //Add a category in the category list
    public void PushInCategory(Integer categoryId, String faculty, String department, String module) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setFaculty(faculty);
        category.setDepartment(department);
        category.setModule(module);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.clear();
    }  
}
