package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import java.util.Collection;

import de.vandermeer.skb.categories.CategoryDoes;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;

public interface DoesExtend extends CategoryDoes {
	/**
	 * Adds an extension.
	 * @param scopedIdent identifier of the extension
	 */
	void addExtension(IsScopedID scopedIdent);

	/**
	 * Returns all (registered) extensions.
	 * @return list of extensions
	 */
	Collection<IsScopedID> getExtensions();
}
