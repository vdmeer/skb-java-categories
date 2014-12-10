package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;

class IsSymbolClass_Impl extends IsSymbol_Impl implements IsSymbolClass {
	/** All implementations of the class symbol */
	List<IsScopedID> implementations;

	/** All extensions of the class symbol */
	List<IsScopedID> extensions;

	IsSymbolClass_Impl(IsID ident, IsScopedID scopedIdent, IsTokentype type) {
		super(ident, scopedIdent, type);
		this.implementations = new ArrayList<>();
		this.extensions = new ArrayList<>();
	}

	@Override
	public void addImplementation(IsScopedID scopedIdent) {
		this.implementations.add(scopedIdent);
	}

	@Override
	public Collection<IsScopedID> getImplementations() {
		return this.implementations;
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

///** All implementations of the class symbol */
//HasValueCollection<IsScopedIdent> implementations;
//
///** All extensions of the class symbol */
//HasValueCollection<IsScopedIdent> extensions;
//
///**
//* Returns a new class symbol.
//* @param ident symbol's identifier
//* @param scopedIdent symbol's scoped identifier
//* @param type symbol's token type
//* @throws DslSymbolException if ident, scopedIdent or type are null
//*/
//public SymbolClassImpl(IsIdent ident, IsScopedIdent scopedIdent, IsTokentype type) throws DslSymbolException {
//	super(ident, scopedIdent, type);
//	this.implementations = HasValueCollection.getValueObject(ListStrategy.ARRAY_LIST, IsScopedIdent.class);
//	this.extensions = HasValueCollection.getValueObject(ListStrategy.ARRAY_LIST, IsScopedIdent.class);
//}
//
//@Override
//public void addImplementation(IsScopedIdent scopedIdent) {
//	this.implementations.getValueCollection().getValueCollection().add(scopedIdent);
//}
//
//@Override
//public Collection<IsScopedIdent> getImplementations() {
//	return this.implementations.getValueCollection().getValueCollection();
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
