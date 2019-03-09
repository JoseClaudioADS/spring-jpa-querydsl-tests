package dev.joseclaudio.model.repository.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import dev.joseclaudio.model.entity.Client;

public interface ClientRepository
		extends JpaRepository<Client, Long>, QuerydslPredicateExecutor<Client>, ClientRepositoryCustom {

	// *** Plus

	// using projection ClientName
	List<ClientName> findNameByEmail(String email);

	// projections, only name field
	interface ClientName {
		String getName();
	}

}
