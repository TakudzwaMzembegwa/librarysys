/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;
import bugbusterlibrary.entity.Book;
import bugbusterlibrary.entity.Receipt;
import bugbusterlibrary.entity.User;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Takudzwa Mzembegwa
 * @author Sango
 */
public class ReceiptDao {
    
    //Return list of all Receipts
    public List<Receipt> findAllReceipts(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<Receipt> receipt = em.createQuery("SELECT r FROM Receipt r", Receipt.class).getResultList();
        return receipt;
    }  
    
    //Return a user's receipts
    public List<Receipt> UserReceipts(User user){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("BugBusterLibraryPU");  
        EntityManager em=emf.createEntityManager();
        List<Receipt> userRecepits = em.createQuery("SELECT r FROM Receipt r WHERE r.receiptId = :receiptId", Receipt.class).setParameter("receiptId", user.getUserId()).getResultList();
        return userRecepits;
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
}
