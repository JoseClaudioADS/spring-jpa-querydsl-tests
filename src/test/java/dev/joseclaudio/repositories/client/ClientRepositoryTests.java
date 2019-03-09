package dev.joseclaudio.repositories.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.joseclaudio.model.entity.Address;
import dev.joseclaudio.model.entity.Client;
import dev.joseclaudio.model.entity.enums.ECountry;
import dev.joseclaudio.model.repository.client.ClientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTests {

	private @Autowired ClientRepository clientRepository;

	@Before
	public void before() {
		Client johnDoe = Client.builder().name("John Doe").email("john@email.com").build();
		johnDoe.addAddress(Address.builder().country(ECountry.UNITED_STATES).postalCode("1235436789").build());
		clientRepository.save(johnDoe);
	}

	@Test
	public void testSpringProjectionQuery() {
		List<ClientRepository.ClientName> clientsNames = clientRepository.findNameByEmail("john@email.com");
		assertThat(clientsNames).isNotEmpty();
		assertThat(clientsNames).hasSize(1);
		ClientRepository.ClientName clientName = clientsNames.get(0);
		assertThat(clientName.getName()).isEqualTo("John Doe");
	}
}
