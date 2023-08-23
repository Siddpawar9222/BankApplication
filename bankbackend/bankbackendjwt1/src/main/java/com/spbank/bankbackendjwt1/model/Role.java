package com.spbank.bankbackendjwt1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "roles")
public class Role {
	
  @Id
//  @Column(name = "role_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.ORDINAL)
  //@Enumerated(EnumType.STRING)
  @Column(name = "name")
  private ERole name;

  public Role(ERole name) {
    this.name = name;
  }
}