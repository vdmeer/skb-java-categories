package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import de.vandermeer.skb.base.categories.IsFactory;
import de.vandermeer.skb.base.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;

public class CBSym_Factory implements IsFactory {

	public static IsSymbol createSymbol(IsID ident, IsScopedID scopedIdent, IsTokentype type){
		return new IsSymbol_Impl(ident, scopedIdent, type);
	}

	public static IsSymbolClass createSymbolClass(IsID ident, IsScopedID scopedIdent, IsTokentype type){
		return new IsSymbolClass_Impl(ident, scopedIdent, type);
	}

	public static IsSymbolInterface createSymbolInterface(IsID ident, IsScopedID scopedIdent, IsTokentype type){
		return new SymbolInterface_Impl(ident, scopedIdent, type);
	}
}
