package de.vandermeer.skb.categories.dsl.curlybracket;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.base.Skb_Renderable;
import de.vandermeer.skb.base.Skb_Transformer;
import de.vandermeer.skb.base.utils.Skb_Antlr4Utils;
import de.vandermeer.skb.base.utils.Skb_TextUtils;
import de.vandermeer.skb.categories.CategoryWithValue;

public class IsScopedID_Impl implements IsScopedID {

	/** The actual scope */
	Deque<Object> scope;

	/** Table with properties for scope elements */
	Map<Object, Map<PropAttributes, Object>> properties;

	/**
	 * Returns a new scoped name for given scope and properties
	 * @param scope scope to use for the scoped name
	 * @param properties all properties of scoped name elements
	 */
	public IsScopedID_Impl(Deque<Object> scope, Map<Object, Map<PropAttributes, Object>> properties){
		if(scope==null){
			throw new RuntimeErrorException(new Error("null scope: scope==null"));
		}
		if(properties==null){
			throw new RuntimeErrorException(new Error("null properties: properties==null"));
		}

		this.scope = new ArrayDeque<Object>(scope);
		this.properties = properties;
	}

	@Override
	public String toString(){
		return IsScopedID.class.getSimpleName() +  "[" + this.render() + "]";
	}

	@Override
	public boolean contains(Object element){
		return this.scope.contains(element);
	}

	@Override
	public Map<PropAttributes, Object> getProperties(Object element){
		Map<PropAttributes, Object> ret = new HashMap<PropAttributes, Object>();
		if(this.scope.contains(element)){
			if(this.properties.containsKey(element)){
				ret.putAll(this.properties.get(element));
			}
		}
		return ret;
	}

	@Override
	public IsTokentype getSymbolType(Object element){
		if(this.scope.contains(element)){
			return (IsTokentype)this.properties.get(element).get(PropAttributes.SYMBOL_TYPE);
		}
		return null;
	}

	@Override
	public boolean isEmpty(){
		if(this.scope==null || this.scope.size()==0){
			return true;
		}
		return false;
	}

	@Override
	public Object peekFirst(){
		return this.scope.peekFirst();
	}

	@Override
	public Map<PropAttributes, Object> peekFirstProperties(){
		return this.getProperties(this.scope.peekFirst());
	}

	@Override
	public Object peekLast(){
		return this.scope.peekLast();
	}

	@Override
	public Map<PropAttributes, Object> peekLastProperties() {
		return this.getProperties(this.scope.peekLast());
	}

	@Override
	public int size(){
		return this.scope.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String render() {
		String separator = IsScopedID.DEFAULT_SEPARATOR;
//		if(options!=null){
//			separator = options.getOptValue("SCOPE_SEPARATOR", IsScopedIdent.DEFAULT_SEPARATOR);
//		}
		Skb_Transformer<Object, String> toText = Skb_Transformer.CHAIN(Skb_Antlr4Utils.ANTLR_TO_TEXT(), CategoryWithValue.CAT_TO_VALUESTRING(), Skb_Renderable.OBJECT_TO_RENDERABLE_VALUE(), Skb_TextUtils.TO_STRING());
		StrBuilder sb = Skb_TextUtils.MANYOBJECTS_TO_STRBUILDER(separator, toText).transform(this.scope.descendingIterator());
		return sb.toString();
	}

	@Override
	public Map<Object, Map<PropAttributes, Object>> getProperties() {
		Map<Object, Map<PropAttributes, Object>> ret = new HashMap<Object, Map<PropAttributes, Object>>();
		ret.putAll(this.properties);
		return ret;
	}

	@Override
	public Deque<Object> _value(){
		return this.scope;
	}

}


///**
// * Renders the scoped name with options.
// * @param options options for rendering
// * @return rendered scoped name
// */
//public String render(Options options){
//	return this.getValue(options).toString();
//}
//
///**
// * An option for rendering setting the separator to use.
// * @param value the scope separator
// * @return an option with the scope separator
// */
//public IsStringOption OPTION_SEPARATOR(String value){
//	return IsStringOption.create("SCOPE_SEPARATOR", value, "The padding character used for rendering");
//}