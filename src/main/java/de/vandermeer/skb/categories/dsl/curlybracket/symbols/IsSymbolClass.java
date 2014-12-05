package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;

public interface IsSymbolClass extends IsSymbol, DoesImplement, DoesExtend {

	static IsSymbolClass create(IsID ident, IsScopedID scopedIdent, IsTokentype type){
		return new IsSymbolClass_Impl(ident, scopedIdent, type);
	}
}

