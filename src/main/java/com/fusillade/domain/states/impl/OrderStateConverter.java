package com.fusillade.domain.states.impl;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fusillade.domain.states.State;

@Converter
public class OrderStateConverter implements AttributeConverter<State, String> {

	@Override
	public String convertToDatabaseColumn(State attribute) {
		return attribute.getClass().getSimpleName();
	}

	@Override
	public State convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "NewOrderState":
			return new NewOrderState();
		case "InProgressOrderState":
			return new InProgressOrderState();
		case "DoneOrderState":
			return new DoneOrderState();
		case "CancelledOrderState":
			return new CancelledOrderState();
		}
		return null;
	}

}
