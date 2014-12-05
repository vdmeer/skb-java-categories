package de.vandermeer.skb.categories.dsl.curlybracket;

public enum TestTokens implements IsTokentype {
	STRING		("string", "a String token"),
	INTEGER		("integer", "an integer token"),
	BOOLEAN		("boolean", "a boolean token"),
	DOUBLE		("double", "a double token"),
	REFERENCE	("reference", "a reference"),
	;

	public String type;

	public String description;

	TestTokens(String type, String description){
		this.type=type;
		this.description=description;
	}

	@Override
	public String getDereferencedType() {
		return this.type;
	}

	@Override
	public String _value() {
		return this.type;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override public String toString(){
		return TestTokens.class.getSimpleName()+"["+this.type+"]";
	}

}
