package dev.joseclaudio.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
@Getter
@Setter
public class Client extends BusinessEntity {

	@NotBlank
	@Size(min = 6, max = 200)
	@Column(length = 200)
	private String name;

	@Email
	@NotBlank
	@Size(max = 250)
	@Column(unique = true, length = 250)
	private String email;

	@OneToMany
	@JoinColumn(name = "client_id")
	private Set<Address> addresses = new HashSet<>();

	public void addAddress(Address address) {
		addresses.add(address);
	}

}
