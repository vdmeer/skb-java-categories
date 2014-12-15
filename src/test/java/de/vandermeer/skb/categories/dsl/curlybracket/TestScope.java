package de.vandermeer.skb.categories.dsl.curlybracket;

public interface TestScope {

	static IsTokentype tt = IsTokentype.create("string", "string", "a string");

	static IsTokentype ttString = IsTokentype.create("string", "string", "a string");
	static IsTokentype ttInteger = IsTokentype.create("integer", "integer", "an integer");
	static IsTokentype ttDouble = IsTokentype.create("double", "double", "a double");
	static IsTokentype ttBoolean = IsTokentype.create("boolean", "boolean", "a boolean");

	static IsScopedID_Mutable testScope(){
		IsScopedID_Mutable ret = new IsScopedID_Mutable_Impl();

		ret.push("test",	 TestScope.ttString);
		ret.push(1, 		TestScope.ttInteger);
		ret.push(3.1415,	TestScope.ttDouble);
		ret.push(true, 		TestScope.ttBoolean);

		return ret;
	}
}
