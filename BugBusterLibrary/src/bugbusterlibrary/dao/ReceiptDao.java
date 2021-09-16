/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;

import bugbusterlibrary.EntityManagerFactoryHandler;
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

    /**
     * @return list of all Receipts
     */
    public List<Receipt> findAll() {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<Receipt> receipt = em.createQuery("SELECT r FROM Receipt r", Receipt.class).getResultList();
        return receipt;
    }

    /**
     * @param user the user whose receipts are to be found
     * @return user's receipts
     */
    public List<Receipt> findUserReceipts(User user) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<Receipt> userRecepits = em
                .createQuery("SELECT r FROM Receipt r WHERE r.receiptId = :receiptId", Receipt.class)
                .setParameter("receiptId", user.getUserId()).getResultList();
        return userRecepits;
    }

    /**
     * Adds a receipt to the database
     */
    public void persist(Long receiptId, Date dateLoaned, Date dateReturned, float fineDue, Book bookId, User userId) {
        Receipt receipt = new Receipt(receiptId, dateLoaned, dateReturned, fineDue);
        receipt.setBookId(bookId);
        receipt.setUserId(userId);

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(receipt);
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Deletes a receipt from the database.
     * 
     * @param receiptID the id of the receipt to be deleted
     */
    public void deleteReceipt(Long receiptID) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Receipt receipt = em.find(Receipt.class, receiptID); // search the database for a receipt with a specific ID.

        if (receipt != null) // see if the receipt ID exists.
        {
            em.getTransaction().begin();
            em.remove(receipt); // delete the receipt.
            em.getTransaction().commit();
        }
    }

    /**
     * Updates a receipt.
     * 
     * @param myReceipt the Receipt object to be updated
     * @param bookID    the book to be added to the receipt
     * @param userID    the book to be added to the receipt
     */
    public void updateReceipt(Receipt myReceipt, Book bookID, User userID) {
        // set the new values of the receipt.
        Receipt receipt = myReceipt;
        receipt.setBookId(bookID);
        receipt.setUserId(userID);

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(receipt); // update the receipt.
        em.getTransaction().commit();
    }
}
