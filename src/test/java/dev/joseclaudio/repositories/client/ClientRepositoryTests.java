package dev.joseclaudio.repositories.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
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
import dev.joseclaudio.model.repository.client.ClientSearch;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTests {

	private @Autowired ClientRepository clientRepository;

	private ClientSearch.ClientSearchBuilder clientSearchBuilder;

	@Before
	public void before() {
		clientSearchBuilder = ClientSearch.builder();

		Client johnDoe = Client.builder().name("John Doe").email("john@email.com").build();
		johnDoe.addAddress(Address.builder().country(ECountry.UNITED_STATES).postalCode("1235436789").build());
		clientRepository.save(johnDoe);

		Client janeDoe = Client.builder().name("Jane Doe").email("jane@email.com").build();
		janeDoe.addAddress(Address.builder().country(ECountry.ENGLAND).postalCode("3211233211").build());
		janeDoe.addAddress(Address.builder().country(ECountry.MEXICO).postalCode("3456789654").build());
		clientRepository.save(janeDoe);

		Client luizGoncalves = Client.builder().name("Luiz Gonçalves").email("luiz.g@email.com").build();
		luizGoncalves.addAddress(Address.builder().country(ECountry.BRAZIL).postalCode("11223344").build());
		clientRepository.save(luizGoncalves);
	}

	@Test
	public void testFilterNameStarts() {
		clientSearchBuilder.nameStarts("Ja");
		List<Client> clients = clientRepository.search(clientSearchBuilder.build());
		assertThat(clients).isNotEmpty();
		assertThat(clients).hasSize(1);
		Client client = clients.get(0);
		assertThat(client.getName()).isEqualTo("Jane Doe");
	}

	@Test
	public void testFilterEmail() {
		clientSearchBuilder.email("john@email.com");
		List<Client> clients = clientRepository.search(clientSearchBuilder.build());
		assertThat(clients).isNotEmpty();
		assertThat(clients).hasSize(1);
		Client client = clients.get(0);
		assertThat(client.getName()).isEqualTo("John Doe");
	}

	@Test
	public void testFilterCountrys() {

		List<ECountry> countrys = new ArrayList<>();
		countrys.add(ECountry.BRAZIL);
		countrys.add(ECountry.UNITED_STATES);

		clientSearchBuilder.countrys(countrys);
		List<Client> clients = clientRepository.search(clientSearchBuilder.build());
		assertThat(clients).isNotEmpty();
		assertThat(clients).hasSize(2);

		Client johnDoe = clientRepository.findByEmail("john@email.com");
		Client luizGoncalves = clientRepository.findByEmail("luiz.g@email.com");
		assertThat(clients.containsAll(Arrays.asList(johnDoe, luizGoncalves))).isTrue();
	}

	@Test
	public void testFilterPostalCode() {
		clientSearchBuilder.postalCode("3211233211");
		List<Client> clients = clientRepository.search(clientSearchBuilder.build());
		assertThat(clients).isNotEmpty();
		assertThat(clients).hasSize(1);
		Client client = clients.get(0);
		assertThat(client.getName()).isEqualTo("Jane Doe");
		assertThat(client.getAddresses()).hasSize(2);
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
