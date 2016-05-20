package com.softserveinc.edu.boardgames.persistence.enumeration;

public enum TimeEnum {

	MILISECONDS(1000),
	SECONDS(60),
	MINUTES(60),
	HOURS(24);
	
	private final int value;

    TimeEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
	
}
