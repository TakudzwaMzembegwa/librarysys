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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
        return em.createQuery("SELECT r FROM Receipt r", Receipt.class).getResultList();
    }

    /**
     * @param user the user whose receipts are to be found
     * @return user's receipts
     */
    public List<Receipt> findUserReceipts(User user) {
        List<Receipt> receipts = findAll();
        ArrayList<Receipt> arrReceipt = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if ((long) receipt.getUser().getUser() == user.getUser()) {
                arrReceipt.add(receipt);
            }
        }
        return arrReceipt;
    }

    /**
     * Adds a receipt to the database.
     * 
     * @param receipt the new receipt to be persisted
     */
    public void persist(Receipt receipt) {
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
     */
    public void update(Receipt myReceipt) {

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(myReceipt); // update the receipt.
        em.getTransaction().commit();
    }

    /**
     * Checks if a user already loaned a book or not.
     * 
     * @param book the book to filter receipts by
     * @param user the user to filter receipts by
     * @return true if user already loaned the book
     */

    public boolean checkIfReceiptExist(Book book, User user) {
        try {
            EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
            em.createQuery("SELECT r FROM Receipt r WHERE r.book = :book AND r.user = :user", Receipt.class)
                    .setParameter("book", book).setParameter("user", user).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
}
