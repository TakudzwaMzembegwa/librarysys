package com.librarysys.backend.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**OOP representation of the User Entity managed by Hibernate
 * 
 * @author BugBusters
  */
@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
	@Column(name = "username", nullable = false, unique = true, length = 30)
	private String username;

    @Column(name = "password", updatable = true, nullable = false, length = 70)
	private String password;

	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_joined", updatable = false, nullable = false)
	private Date dateJoined;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", nullable = false)
	private Date lastUpdate;
    
}
