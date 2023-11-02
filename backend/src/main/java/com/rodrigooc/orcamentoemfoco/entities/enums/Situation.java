package com.rodrigooc.orcamentoemfoco.entities.enums;

public enum Situation {
	OVERDUE(1), PAID(2), COMPLETED(3);

	private int code;

	private Situation(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Situation valueOf(int code) {
		for (Situation value : Situation.values()) {
			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Situation code");
	}
}
