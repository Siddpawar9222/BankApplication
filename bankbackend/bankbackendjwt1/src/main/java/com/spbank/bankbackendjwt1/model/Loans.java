package com.spbank.bankbackendjwt1.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.* ;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="loans")
public class Loans {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	@Column(name = "loan_number")
	private int loanNumber;


	@Column(name="start_dt")
	private Date startDt;

	@Column(name = "loan_type")
	private String loanType;

	@Column(name = "total_loan")
	private int totalLoan;

	@Column(name = "amount_paid")
	private int amountPaid;

	@Column(name = "outstanding_amount")
	private int outstandingAmount;

	@Column(name = "create_dt")
	private String createDt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User user ;
	
}
