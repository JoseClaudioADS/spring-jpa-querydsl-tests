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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "country", callSuper = false)
public class Address extends BusinessEntity {

	@NotNull
	@Enumerated(EnumType.STRING)
	private ECountry country;

	@NotBlank
	@Size(max = 10)
	@Column(length = 10)
	private String postalCode;

}
