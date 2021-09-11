/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Takudzwa Mzembegwa
 */
@Entity
@Table(name = "Category", catalog = "sql10433996", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
    , @NamedQuery(name = "Category.findByCategoryId", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId")
    , @NamedQuery(name = "Category.findByFaculty", query = "SELECT c FROM Category c WHERE c.faculty = :faculty")
    , @NamedQuery(name = "Category.findByDepartment", query = "SELECT c FROM Category c WHERE c.department = :department")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "department")
    private String department;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private List<Book> bookList;

    public Category() {
    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @XmlTransient
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.categoryId);
        hash = 79 * hash + Objects.hashCode(this.faculty);
        hash = 79 * hash + Objects.hashCode(this.department);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.faculty, other.faculty)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.categoryId, other.categoryId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bugbusterlibrary.entity.Category[ categoryId=" + categoryId + " ]";
    }
    
}
