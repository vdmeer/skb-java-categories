package de.vandermeer.skb.categories.kvt;


public enum ScopeKey implements IsKey_String {
	ABOVE		("above", "can be called from anything above a given scope"),
	SELF		("self",  "can be called from anything in the current scope"),
	BELOW		("below", "can be called from anything below the current scope"),
	;

	String key;
	String description;

	ScopeKey(String key, String description){
		this.key = key;
		this.description = description;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override
	public String _value() {
		return this.key;
	}

	@Override
	public String toString(){
		return ScopeKey.class.getSimpleName() + "(" + this.getClass().getSimpleName() + "):" + this.key();
	}
}
