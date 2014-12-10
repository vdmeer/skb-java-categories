package de.vandermeer.skb.categories.options;

import de.vandermeer.skb.categories.kvt.IsType;

public enum OptionType implements IsType<String> {
	BOOLEAN,
	CHARACHTER,
	CHARACTER_ARRAY,
	STRING,
	DOUBLE,
	INTEGER
	;

	@Override
	public String _value() {
		return this.name();
	}
}
