package bugbusterlibrary.dao;

import bugbusterlibrary.entity.Book;
import bugbusterlibrary.entity.Category;
import bugbusterlibrary.entity.Receipt;
import bugbusterlibrary.entity.User;
import bugbusterlibrary.EntityManagerFactoryHandler;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeleteUpdateDOA{
    
    public void deleteBook(Long bookId) 
    {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Book book = em.find(Book.class, bookId);
        
       if (book != null) {
            em.getTransaction().begin();
            em.remove(book);
            em.getTransaction().commit();
        }
    }

    public void updateBook(Book myBook, String description, String edition, String image, Category categoryId)
    {
        Book book = myBook;
        book.setDescription(description);
        book.setEdition(edition);
        book.setImage(image);
        book.setCategoryId(categoryId);

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    public void deleteCategory(Category categoryId) 
    {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Category category = em.find(Category.class, categoryId);
        
       if (category != null) {
            em.getTransaction().begin();
            em.remove(category);
            em.getTransaction().commit();
        }
    }

    public void updateCategory(Category category)
    {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }
    
    public void deleteReceipt(Long receiptID) 
   {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Receipt receipt = em.find(Receipt.class, receiptId);
        
       if (receipt != null) {
            em.getTransaction().begin();
            em.remove(receipt);
            em.getTransaction().commit();
        }
    }

    public void updateReceipt(Receipt myReceipt, Book bookID, User userID)
    {
    	Receipt receipt = myReceipt;
    	receipt.setBookId(bookID);
    	receipt.setUserId(userID); 
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(receipt);
        em.getTransaction().commit();
    }
    
   public void deleteUser(Long userID) 
   {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        User user = em.find(User.class, userID);
        
       if (user != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }

    public void updateUser(User myUser, List<Receipt> receiptList)
    {
    	User user = myUser;
    	receipt.setReceiptList(receiptList);
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
}
