package de.vandermeer.skb.categories.dsl.curlybracket;

import java.util.Deque;
import java.util.Map;

import de.vandermeer.skb.base.Skb_Renderable;
import de.vandermeer.skb.base.categories.CategoryIs;
import de.vandermeer.skb.base.categories.CategoryWithValue;
import de.vandermeer.skb.base.categories.kvt.IsAttributeKey;

public interface IsScopedID extends CategoryIs, CategoryWithValue, Skb_Renderable {

	/** Default scope separator */
	public static String DEFAULT_SEPARATOR=":";

	/**
	 * Returns true if the scope contains the given element
	 * @param element part of the scoped name to test
	 * @return true if the element is part of the scoped name, false otherwise
	 */
	boolean contains(Object element);

	/**
	 * Returns the properties of a specific element of the scoped name
	 * @param element element to look for
	 * @return properties, empty if element not part of scoped name
	 */
	Map<PropAttributes, Object> getProperties(Object element);

	/**
	 * Returns all properties of all elements of the scoped ID.
	 * @return elements with properties
	 */
	Map<Object, Map<PropAttributes, Object>> getProperties();

	/**
	 * Returns the symbol type of a scoped name element.
	 * @param element element to look for
	 * @return symbol type of the element, null if element not in scoped name or no type set
	 */
	IsTokentype getSymbolType(Object element);

	/**
	 * Tests if the scoped name is empty
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Peek the first element of the scoped name.
	 * @return first element of the scoped name
	 */
	Object peekFirst();

	/**
	 * Returns the properties of the first element of the scoped name
	 * @return properties
	 */
	Map<PropAttributes, Object> peekFirstProperties();

	/**
	 * Peeks the last element of the scoped name
	 * @return last element of the scoped name
	 */
	Object peekLast();

	/**
	 * Returns the properties of the last element of the scoped name
	 * @return properties
	 */
	Map<PropAttributes, Object> peekLastProperties();

	/**
	 * Returns the size of the scoped name, for instance the number of its elements
	 * @return size
	 */
	int size();

	/**
	 * Returns a copy of the scoped name elements
	 * @return copy of scoped name elements
	 */
	default Deque<Object> getScope(){;
		return this._value();
	}

	@Override
	Deque<Object> _value();

	/**
	 * Keys for values associated with parts of a scoped name.
	 *
	 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
	 * @version    v0.0.4 build 150701 (01-Jul-15) for Java 1.8
	 */
	public enum PropAttributes implements IsAttributeKey {
		/** Type of the symbol represented by an element of the scoped name */
		SYMBOL_TYPE	("type", "type of a scope entry"),
		;
		String key; String description;
		PropAttributes(String key, String description){this.key = key;this.description = description;}
		@Override public String getDescription() {return this.description;}
		@Override public String _value() {return this.key;}
	}

//	static IsScopedID create(Deque<Object> scope, Map<Object, Map<PropAttributes, Object>> properties){
//		return new IsScopedID_Impl(scope, properties);
//	}

//TODOs
//	/**
//	 * Returns the value of the scoped name, that is all elements concatenated.
//	 * @param options options, for instance the scope separator to use
//	 * @return value as concatenation of all elements
//	 */
//	Object getValue(Options options);
//
//	/**
//	 * An option for rendering setting the separator to use.
//	 * @param separator the scope separator
//	 * @return an option with the scope separator
//	 */
//	IsOption OPTION_SEPARATOR(String separator);
}
