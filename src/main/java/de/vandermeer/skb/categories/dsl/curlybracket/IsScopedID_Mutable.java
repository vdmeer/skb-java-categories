package de.vandermeer.skb.categories.dsl.curlybracket;


public interface IsScopedID_Mutable extends IsScopedID {

	/**
	 * Returns a static, immutable, scope
	 * @return immutable scope
	 */
	IsScopedID getStaticScope();

	/**
	 * Changes the symbol type of a scope element.
	 * @param element element to change the type for
	 * @param symbolType new type of the element
	 */
	void changeSymbolType(Object element, IsTokentype symbolType);

	/**
	 * Clears the scope, for instance removes all elements and their properties.
	 */
	void clear();

	/**
	 * Pushes a new element at the front of the scope
	 * @param element new element
	 * @param symbolType type of the new element
	 */
	void push(Object element, IsTokentype symbolType);


	/**
	 * Removes the first element of the scope
	 * @return the element poped or null if not possible
	 */
	Object pop();

	static IsScopedID_Mutable createMutable(){
		return new IsScopedID_Mutable_Impl();
	}
}
