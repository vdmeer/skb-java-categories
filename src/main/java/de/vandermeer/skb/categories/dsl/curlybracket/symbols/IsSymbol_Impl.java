package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.text.StrBuilder;
import org.stringtemplate.v4.ST;

import de.vandermeer.skb.base.Skb_ToStringStyle;
import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;
import de.vandermeer.skb.categories.kvt.ScopeKey;
import de.vandermeer.skb.categories.kvt.SwitchKey;

class IsSymbol_Impl implements IsSymbol {
	/** Symbol Identifier */
	protected IsID ident;

	/** Symbol Scoped Identifier */
	protected IsScopedID scopedIdent;

	/** Line in which the symbol is defined */
	protected int line;

	/** Column in which the symbol is defined */
	protected int column;

	/** Source (file name) where the symbol is defined */
	protected String sourceName;

	/** The Symbol's String Template */
	protected ST st;

	/** The Symbol's token type */
	protected IsTokentype type;

	/** The Symbol's call scope */
	protected Map<SwitchKey, Map<IsScopedID, List<ScopeKey>>> callScope;

	/**
	 * Returns a new symbol.
	 * @param ident symbol's identifier
	 * @param scopedIdent symbol's scoped identifier
	 * @param type symbols'token type
	 * @throws DslSymbolException if identifier, scopedIdent or type are null
	 */
	public IsSymbol_Impl(IsID ident, IsScopedID scopedIdent, IsTokentype type) {
		if(ident==null){
			throw new RuntimeErrorException(new Error("null ident object: ident==null"));
		}
		if(scopedIdent==null){
			throw new RuntimeErrorException(new Error("null scoped ident object: scopedIdent==null"));
		}
		if(type==null){
			throw new RuntimeErrorException(new Error("null special token type object: type==null"));
		}
		this.ident = ident;
		this.scopedIdent = scopedIdent;
		this.type = type;

		this.callScope = new LinkedHashMap<SwitchKey, Map<IsScopedID, List<ScopeKey>>>();
		this.callScope.put(SwitchKey.ENABLED, new LinkedHashMap<IsScopedID, List<ScopeKey>>());
		this.callScope.put(SwitchKey.DISABLE, new LinkedHashMap<IsScopedID, List<ScopeKey>>());

		this.line = -1;
		this.column = -1;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public int getLine() {
		return this.line;
	}

	@Override
	public String getSourceName() {
		return this.sourceName;
	}

	@Override
	public ST getST() {
		return this.st;
	}

	@Override
	public IsSymbol setColum(int c) {
		this.column = c;
		return this;
	}

	@Override
	public IsSymbol setLine(int l) {
		this.line = l;
		return this;
	}

	@Override
	public IsSymbol setPosition(TerminalNode node) {
		if(node!=null && node.getSymbol()!=null){
			return this.setPosition(node.getSymbol());
		}
		return this;
	}

	@Override
	public IsSymbol setPosition(Token tk) {
		if(tk!=null){
			this.column = tk.getCharPositionInLine();
			this.line = tk.getLine();
			this.sourceName = tk.getTokenSource().getSourceName();
		}
		return this;
	}

	@Override
	public IsSymbol setSourceName(String name) {
		this.sourceName = name;
		return this;
	}

	@Override
	public IsSymbol setST(ST st) {
		if(st!=null){
			this.st = st;
		}
		return this;
	}

	@Override
	public IsID getID() {
		return this.ident;
	}

	@Override
	public IsScopedID getScopedID() {
		return this.scopedIdent;
	}

	@Override
	public IsSymbol setCallScope(Map<SwitchKey, Map<IsScopedID, List<ScopeKey>>> map) {
		if(map!=null){
			this.callScope = map;
		}
		return this;
	}

	@Override
	public IsSymbol changeCallScope(Map<IsScopedID, List<ScopeKey>> map, SwitchKey state) {
		if(map!=null && state!=null){
			this.callScope.put(state, map);
		}
		return this;
	}

	@Override
	public IsSymbol changeCallScope(IsScopedID scIdent, ScopeKey callScope, SwitchKey state) {
		if(scIdent!=null && callScope!=null&&state!=null){
			if(this.callScope.get(state).get(scIdent)!=null){
				this.callScope.get(state).get(scIdent).add(callScope);
			}
			else{
				List<ScopeKey> list = new ArrayList<ScopeKey>();
				list.add(callScope);
				this.callScope.get(state).put(scIdent, list);
			}
		}
		return this;
	}

	@Override
	public IsTokentype getType() {
		return this.type;
	}

	@Override
	public String toString(){
		StrBuilder id = new StrBuilder(50);
		id.append(this.ident).append(" -> ").append(this.scopedIdent).append(" --> ").append(this.type);

		StrBuilder cls = new StrBuilder(50);
		cls.append(this.sourceName).append('(').append(this.line).append(':').append(this.column).append(')');

		ToStringBuilder ret = new ToStringBuilder(this, Skb_ToStringStyle.TS_STYLE)
		.append("id/sc/type    ", id)
		.append("name/c/l      ", cls)
		.append("------------------------------------")
		.append("st            ", this.st, false)
		.append("st            ", this.st)
		.append("------------------------------------")
		.append("callScope enabled     ", this.callScope.get(SwitchKey.ENABLED))
		.append("callScope disabled    ", this.callScope.get(SwitchKey.DISABLE))
		.append("------------------------------------")
		;

		return ret.toString();
	}

	@Override
	public Map<SwitchKey, Map<IsScopedID, List<ScopeKey>>> getCallScope() {
		return this.callScope;
	}


}
