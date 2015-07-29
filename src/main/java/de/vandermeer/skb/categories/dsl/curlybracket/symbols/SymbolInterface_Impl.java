package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.vandermeer.skb.base.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;

class SymbolInterface_Impl extends IsSymbol_Impl implements IsSymbolInterface {
	/** All extensions of the interface */
	List<IsScopedID> extensions;

	SymbolInterface_Impl(IsID ident, IsScopedID scopedIdent, IsTokentype type) { // throws DslSymbolException {
		super(ident, scopedIdent, type);

		this.extensions = new ArrayList<>();
		//HasValueCollection.getValueObject(ListStrategy.ARRAY_LIST, IsScopedID.class);
	}

	@Override
	public void addExtension(IsScopedID scopedIdent) {
		this.extensions.add(scopedIdent);
	}

	@Override
	public Collection<IsScopedID> getExtensions() {
		return this.extensions;
	}
}

///** All extensions of the interface */
//HasValueCollection<IsScopedIdent> extensions;
//
//public SymbolInterfaceImpl(IsIdent ident, IsScopedIdent scopedIdent, IsTokentype type) throws DslSymbolException {
//	super(ident, scopedIdent, type);
//
//	this.extensions = HasValueCollection.getValueObject(ListStrategy.ARRAY_LIST, IsScopedIdent.class);
//}
//
//@Override
//public void addExtension(IsScopedIdent scopedIdent) {
//	this.extensions.getValueCollection().getValueCollection().add(scopedIdent);
//}
//
//@Override
//public Collection<IsScopedIdent> getExtensions() {
//	return this.extensions.getValueCollection().getValueCollection();
//}