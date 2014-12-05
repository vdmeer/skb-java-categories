package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.ST;

import de.vandermeer.skb.categories.CategoryIs;
import de.vandermeer.skb.categories.HasID;
import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.HasScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.HasTokentype;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;
import de.vandermeer.skb.categories.kvt.ScopeKey;
import de.vandermeer.skb.categories.kvt.SwitchKey;

public interface IsSymbol extends CategoryIs, HasID, HasScopedID, HasTokentype {

	/**
	 * Returns the column where the symbol is defined.
	 * @return column
	 */
	int getColumn();

	/**
	 * Returns the line where the symbol is defined.
	 * @return line
	 */
	int getLine();

	/**
	 * Returns the source name, that is the source file, where the symbol is defined.
	 * @return source name, null if not set
	 */
	String getSourceName();

	/**
	 * Returns the String Template for the symbol.
	 * @return string template
	 */
	ST getST();

	/**
	 * Sets the column in which the symbol is defined.
	 * @param c column
	 * @return returns the symbol itself to chain method calls
	 */
	IsSymbol setColum(int c);

	/**
	 * Sets the line in which the symbol is defined.
	 * @param l line
	 * @return returns the symbol itself to chain method calls
	 */
	IsSymbol setLine(int l);

	/**
	 * Sets column, line and source for the symbol.
	 * @param node object with symbol information
	 * @return returns the symbol itself to chain method calls
	 */
	IsSymbol setPosition(TerminalNode node);

	/**
	 * Sets column, line and source for the symbol.
	 * @param tk object with symbol information
	 * @return returns the symbol itself to chain method calls
	 */
	IsSymbol setPosition(Token tk);

	/**
	 * Sets the source name of the symbol, that is the file in which the symbol is defined.
	 * @param name source
	 * @return returns the symbol itself to chain method calls
	 */
	IsSymbol setSourceName(String name);

	/**
	 * Sets the String Template for the symbol
	 * @param st new string template
	 * @return returns the symbol itself to chain method calls
	 */
	IsSymbol setST(ST st);

	//TODO
	Map<SwitchKey, Map<IsScopedID, List<ScopeKey>>> getCallScope();

	//TODO
	IsSymbol setCallScope(Map<SwitchKey, Map<IsScopedID, List<ScopeKey>>> map);

	//TODO
	IsSymbol changeCallScope(Map<IsScopedID, List<ScopeKey>> map, SwitchKey state);

	//TODO
	IsSymbol changeCallScope(IsScopedID scIdent, ScopeKey callScope, SwitchKey state);

	static IsSymbol create(IsID ident, IsScopedID scopedIdent, IsTokentype type){
		return new IsSymbol_Impl(ident, scopedIdent, type);
	}
}
