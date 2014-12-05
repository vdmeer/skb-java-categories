package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;


public interface IsSymbolInterface extends IsSymbol, DoesExtend {

	static IsSymbolInterface create(IsID ident, IsScopedID scopedIdent, IsTokentype type){
		return new SymbolInterface_Impl(ident, scopedIdent, type);
	}
}
