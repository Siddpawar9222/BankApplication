package com.spbank.bankbackendjwt1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer")
public class User {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "customer_id")
	  private Long id;

	  @NotBlank
	  @Size(max = 20)
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;

	  @NotBlank
	  @Size(max = 120)
	  private String password;

	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "customer_roles",
	        joinColumns = @JoinColumn(name = "customer_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles = new HashSet<>();
	  
	  public User(String username, String email, String password) {
		    this.username = username;
		    this.email = email;
		    this.password = password;
		  }
    
}