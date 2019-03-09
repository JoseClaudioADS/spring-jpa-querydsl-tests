package dev.joseclaudio.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.joseclaudio.model.entity.enums.ECountry;
import lombok.Getter;
import lombok.Setter;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
@Getter
@Setter
public class Address extends BusinessEntity {

	@NotNull
	@Enumerated(EnumType.STRING)
	private ECountry country;

	@NotBlank
	@Size(max = 10)
	@Column(length = 10)
	private String postalCode;

}
