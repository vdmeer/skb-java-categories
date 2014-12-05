package de.vandermeer.skb.categories.dsl.curlybracket;

import de.vandermeer.skb.categories.kvt.IsType;

public interface IsDerefType extends IsType<Object> {
	/**
	 * Returns the de-referenced type.
	 * @return dereferenced type
	 */
	String getDereferencedType();
}
