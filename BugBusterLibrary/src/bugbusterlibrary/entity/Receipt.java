/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takudzwa Mzembegwa
 */
@Entity
@Table(name = "Receipt", catalog = "sql10433996", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r"),
        @NamedQuery(name = "Receipt.findByReceiptId", query = "SELECT r FROM Receipt r WHERE r.receiptId = :receiptId"),
        @NamedQuery(name = "Receipt.findByDateLoaned", query = "SELECT r FROM Receipt r WHERE r.dateLoaned = :dateLoaned"),
        @NamedQuery(name = "Receipt.findByDateReturned", query = "SELECT r FROM Receipt r WHERE r.dateReturned = :dateReturned") })
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "receipt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;
    @Basic(optional = false)
    @Column(name = "date_loaned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLoaned;
    @Basic(optional = false)
    @Column(name = "date_returned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReturned;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private Book bookId;

    public Receipt() {
    }

    public Receipt(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Receipt(Long receiptId, Date dateLoaned, Date dateReturned, float fineDue) {
        this.receiptId = receiptId;
        this.dateLoaned = dateLoaned;
        this.dateReturned = dateReturned;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Date getDateLoaned() {
        return dateLoaned;
    }

    public void setDateLoaned(Date dateLoaned) {
        this.dateLoaned = dateLoaned;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    /***
     * Calculates the fine a user gets for keeping a book past due the date. A user
     * can keep a book for no more than 7 days. After that, the user will get fined
     * 8zar per day for every extra day they keep the book.
     * 
     * @return the fine charged.
     */
    public double getFine() {
        double fine = (((this.getDateReturned().getTime() - this.getDateLoaned().getTime()) - (7 * 8.64e+7)) / 8.64e+7)
                * 8;
        return fine > 0 ? fine : 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.receiptId);
        hash = 83 * hash + Objects.hashCode(this.dateLoaned);
        hash = 83 * hash + Objects.hashCode(this.dateReturned);
        hash = 83 * hash + Objects.hashCode(this.userId);
        hash = 83 * hash + Objects.hashCode(this.bookId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Receipt other = (Receipt) obj;
        if (!Objects.equals(this.receiptId, other.receiptId)) {
            return false;
        }
        if (!Objects.equals(this.dateLoaned, other.dateLoaned)) {
            return false;
        }
        if (!Objects.equals(this.dateReturned, other.dateReturned)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.bookId, other.bookId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bugbusterlibrary.entity.Receipt[ receiptId=" + receiptId + " ]";
    }

}
