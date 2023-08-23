package com.spbank.bankbackendjwt1.repository;

import java.util.List;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spbank.bankbackendjwt1.model.Cards;






@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {
	
	List<Cards> findByUserId(Long customerId);

}
