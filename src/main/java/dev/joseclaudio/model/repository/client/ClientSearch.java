package dev.joseclaudio.model.repository.client;

import java.util.List;

import dev.joseclaudio.model.entity.enums.ECountry;
import dev.joseclaudio.model.repository.BusinessOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientSearch {

	private String nameStarts;
	private String email;
	private List<ECountry> countrys;
	private String postalCode;

	private BusinessOrder order;

}
