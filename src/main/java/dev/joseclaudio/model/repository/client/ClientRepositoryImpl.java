package dev.joseclaudio.model.repository.client;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;

import dev.joseclaudio.model.entity.Client;
import dev.joseclaudio.model.entity.QAddress;
import dev.joseclaudio.model.entity.QClient;
import dev.joseclaudio.model.repository.BusinessOrder;

@Repository
public class ClientRepositoryImpl implements ClientRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Client> search(ClientSearch filters) {
		JPAQuery<Client> query = configureQuery(filters);
		if (filters.getOrder() != null) {
			query = configureOrder(query, filters);
		}
		return query.fetch();
	}

	@Override
	public Long count(ClientSearch search) {
		return configureQuery(search).fetchCount();
	}

	private JPAQuery<Client> configureQuery(ClientSearch filters) {
		JPAQuery<Client> jpaQuery = new JPAQuery<>(em);
		JPAQuery<Client> query = jpaQuery.from(QClient.client).distinct();
		query = predicates(query, filters);
		return query;
	}

	private JPAQuery<Client> predicates(JPAQuery<Client> query, ClientSearch filters) {
		if (!isEmpty(filters.getEmail())) {
			query = query.where(QClient.client.email.eq(filters.getEmail()));
		}

		if (!isEmpty(filters.getNameStarts())) {
			query = query.where(QClient.client.name.startsWithIgnoreCase(filters.getNameStarts()));
		}

		QAddress address = new QAddress("addresses");
		if (!CollectionUtils.isEmpty(filters.getCountrys()) || !isEmpty(filters.getPostalCode())) {
			query.leftJoin(QClient.client.addresses, address).fetchJoin();
		}
		if (!CollectionUtils.isEmpty(filters.getCountrys())) {
			query = query.where(address.country.in(filters.getCountrys()));
		}

		if (!isEmpty(filters.getPostalCode())) {
			query = query.where(address.postalCode.eq(filters.getPostalCode()));
		}

		return query;
	}

	@SuppressWarnings("unchecked")
	private JPAQuery<Client> configureOrder(JPAQuery<Client> query, ClientSearch filters) {
		BusinessOrder order = filters.getOrder();
		return query
				.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, order.getPropertyPath()));
	}

}
