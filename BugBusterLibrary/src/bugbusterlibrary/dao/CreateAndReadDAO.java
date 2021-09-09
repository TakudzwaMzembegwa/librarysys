/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;

import bugbusterlibrary.entity.Book;
import bugbusterlibrary.entity.Category;
import bugbusterlibrary.entity.Receipt;
import bugbusterlibrary.entity.User;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sango
 */
public class CreateAndReadDAO {
    
    //Return list of all books
    public List<Book> findAllBooks(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        return books;
    } 
    
    //Return list of all Receipts
    public List<Receipt> findAllReceipts(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<Receipt> receipt = em.createQuery("SELECT r FROM Receipt r", Receipt.class).getResultList();
        return receipt;
    }  
    
    //Return a user's receipt
    public Receipt UserReceipt(User user){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        Receipt userRecepit = em.createQuery("SELECT r FROM Receipt r WHERE r.receiptId = :receiptId", Receipt.class).setParameter("receiptId", user.getUserId()).getSingleResult();
        return userRecepit;
    }
    
    //Return list of Category
    public List<Category> findAllCategories(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<Category> category = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        return category;
    } 
    
    //Add a book in the book list
    public void PushInBook(Long bookId, String title, String author, String isbn, String description, String edition, String image, String availability, Category categoryId){
        Book book = new Book(bookId, title, author, isbn, availability);
        book.setDescription(description);
        book.setEdition(edition);
        book.setImage(image);
        book.setCategoryId(categoryId);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.clear();   
    }
    
    //Add a receipt in the receipt list
    public void PushInReceipt(Long receiptId, Date dateLoaned, Date dateReturned, float fineDue, Book bookId, User userId){
        Receipt receipt = new Receipt(receiptId, dateLoaned, dateReturned, fineDue);
        receipt.setBookId(bookId);
        receipt.setUserId(userId);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(receipt);
        em.getTransaction().commit();
        em.clear();
    }
    
    //Add a category in the category list
    public void PushInCategory(Integer categoryId, String faculty, String department) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setFaculty(faculty);
        category.setDepartment(department);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.clear();
    }
}
