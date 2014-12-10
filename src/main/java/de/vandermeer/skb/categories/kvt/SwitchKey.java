package de.vandermeer.skb.categories.kvt;


public enum SwitchKey  implements IsKey_String {
	/** Enable a call switch */
	ENABLED		("enabled", "enable a scope or a set of scopes"),
	/** Disable a call switch */
	DISABLE		("disable", "disable a scoep or a set of scopes"),
	;

	/** Key */
	String key;

	/** Key description */
	String description;

	SwitchKey(String key, String description){
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

	//TODO
	@Override
	public String toString(){
		return SwitchKey.class.getSimpleName() + "(" + this.getClass().getSimpleName() + "):" + this.key();
	}
}
