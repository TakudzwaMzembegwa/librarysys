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
import javax.persistence.Persistence;

/**
 *
 * @author Takudzwa Mzembegwa
 * @author Sango
 */
public class CategoryDao {

    /**
     * Finds all the Categories in the database
     *
     * @return list of all Categories
     */
    public List<Category> findAll() {
        Persistence.createEntityManagerFactory("BugBusterLibraryPU");
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<Category> category = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        return category;
    }

    /**
     * Finds {@code Category} by {@code Category id}
     *
     * @param id the {@code id} of the {@code Category} to be found
     * @return book if any was found else {@code null}
     */
    public Category findById(int id) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category category = em.createNamedQuery("Category.findByCategoryId", Category.class).setParameter("categoryId", id).getSingleResult();
        return category;
    }

    /**
     * Add a category in the category list
     *
     * @param category the category to be persisted
     */
    public void persist(Category category) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    /**
     * function to delete a category.
     *
     * @param category the category to be deleted.
     */
    public void deleteCategory(Category category) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category delCategory = em.find(Category.class, category); // search for the category by its ID. 

        if (delCategory != null) // check if the category is in the database. 
        {
            em.getTransaction().begin();
            em.remove(delCategory); // delete the category. 
            em.getTransaction().commit();
        }
    }

    /**
     * Finds {@code Category} by {@code Category id}.
     *
     * @param faculty the {@code faculty} the {@code Category} to be found
     * belongs to.
     * @param department the {@code department} under {@code faculty} the
     * {@code Category} to be found belongs to.
     * @return book if any was found else {@code null}.
     */
    public Category findCategory(String faculty, String department) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category category = em.createQuery("SELECT c FROM Category c WHERE c.faculty = :faculty AND c.department = :department", Category.class)
                .setParameter("faculty", faculty)
                .setParameter("department", department)
                .getSingleResult();
        return category;
    }

    /**
     * function to update a category.
     *
     * @param category the category to be updated.
     */
    public void updateCategory(Category category) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(category); // call to update the category. 
        em.getTransaction().commit();
    }
}
