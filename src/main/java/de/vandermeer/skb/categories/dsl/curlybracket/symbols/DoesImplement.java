package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import java.util.Collection;

import de.vandermeer.skb.base.categories.CategoryDoes;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;

public interface DoesImplement extends CategoryDoes {
	/**
	 * Adds a class/interface/object that is implemented.
	 * @param scopedIdent identifier of the implemented class/interface/object
	 */
	void addImplementation(IsScopedID scopedIdent);

	/**
	 * Returns all (registered) implementations.
	 * @return list of implementations
	 */
	Collection<IsScopedID> getImplementations();
}
