package com.spbank.bankbackendjwt1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Accounts {

//	@Column(name = "customer_id")
//	private Long customerId;

	@Id
	@Column(name="account_number")
	private Long accountNumber;

	@Column(name="account_type")
	private String accountType;

	@Column(name = "branch_address")
	private String branchAddress;

	@Column(name = "create_dt")
	private String createDt;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "customer_id")
	private User user ;


}
