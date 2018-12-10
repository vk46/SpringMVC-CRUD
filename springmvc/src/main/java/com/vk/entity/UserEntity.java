/**
 * 
 */
package com.vk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.vk.enumerator.Salutations;
import com.vk.enumerator.Status;

/**
 * @author Vinay Kumar
 *
 */

@Entity
@Table(name="users_list")
public class UserEntity extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name="ul_salutation",nullable=false,columnDefinition="ENUM('Mr','Mrs','Miss','Ms') default 'Mr'")
	private Salutations salutation;
	
	@Column(name="ul_username",nullable=false, length=100)
	private String userName;
	
	@Column(name="ul_password",nullable=false, length=100)
	private String password;
	
	@Column(name="ul_firstname",nullable=false, length=100)
	private String firstName;
	
	@Column(name="ul_lastname",nullable=false, length=100)
	private String lastName;
	
	@Column(name="ul_email", nullable=false)
	private String email;
	
	@Column(name="ul_mobile",nullable=false)
	private String mobile;
	
	@Enumerated(EnumType.STRING)
	@Column(name="mu_status", nullable=false,columnDefinition="ENUM('Active','Inactive','Pending') default 'Active'")
	private Status status;


	/**
	 * @return the salutation
	 */
	public Salutations getSalutation() {
		return salutation;
	}


	/**
	 * @param salutation the salutation to set
	 */
	public void setSalutation(Salutations salutation) {
		this.salutation = salutation;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
