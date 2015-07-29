package de.vandermeer.skb.categories.dsl.curlybracket;

import de.vandermeer.skb.base.categories.CategoryHas;


public interface HasScopedID extends CategoryHas {
	/**
	 * Returns a scoped ident
	 * @return scoped ident
	 */
	IsScopedID getScopedID();
}
