package com.fusillade.domain.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fusillade.domain.entity.states.NewOrderState;

@Converter
public class OrderStateConverter implements AttributeConverter<State, String>{

	@Override
	public String convertToDatabaseColumn(State attribute) {
		return attribute.getClass().getName();
	}

	@Override
	public State convertToEntityAttribute(String dbData) {
		return new NewOrderState();
	}

}
