/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;
import bugbusterlibrary.EntityManagerFactoryHandler;
import bugbusterlibrary.entity.Book;
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
public class BookDao {
    
    /** 
     *  @return list of all books
    */
    public List<Book> findAll(){
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        return books;
    } 
    
    /** 
     * Adds a book in the database
     * @param book the book to be persisted 
    */
    public void persist(Long bookId, String title, String author, String isbn, String description, String edition, String image, String availability, Category categoryId){
        Book book = new Book(bookId, title, author, isbn, availability);
        book.setDescription(description);
        book.setEdition(edition);
        book.setImage(image);
        book.setCategoryId(categoryId);
        
        EntityManager em =  EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.clear();   
    } 
    
}
