package dev.joseclaudio.model.repository;

import com.querydsl.core.types.Path;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BusinessOrder {

	@SuppressWarnings("rawtypes")
	private Path propertyPath;
	private boolean ascending;

}
