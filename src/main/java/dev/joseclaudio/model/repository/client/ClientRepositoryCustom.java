package dev.joseclaudio.model.repository.client;

import java.util.List;

import dev.joseclaudio.model.entity.Client;

public interface ClientRepositoryCustom {

	List<Client> search(ClientSearch search);

}
