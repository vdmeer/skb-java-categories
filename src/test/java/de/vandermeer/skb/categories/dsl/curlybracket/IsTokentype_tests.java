package de.vandermeer.skb.categories.dsl.curlybracket;

import javax.management.RuntimeErrorException;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IsTokentype_tests {

	@Rule public ExpectedException exception = ExpectedException.none();

	@Test public void testConstructorWExceptions(){
		exception.expect(RuntimeErrorException.class);
		IsTokentype.create(null, null, null);
	}

	@Test public void testTokentype(){
		this._tokenTypeTest(null, "string", "a String token");
		this._tokenTypeTest(null, "integer", "an integer token");
		this._tokenTypeTest(null, "boolean", "a boolean token");
		this._tokenTypeTest(null, "double", "a double token");

		for(TestTokens tk:TestTokens.values()){
			this._tokenTypeTest(tk, tk.type, tk.description);
		}
	}

	private void _tokenTypeTest(IsTokentype tk, Object type, String description){
		if(tk==null){
			tk = IsTokentype.create(type.toString(), type.toString(), description);
		}

		assertEquals(type.toString(), tk.getDereferencedType());
		assertEquals(type.toString(), tk.type());
		assertEquals(type.toString(), tk._value());
		assertEquals(description, tk.getDescription());
//		assertEquals(tk.getClass().getSimpleName()+"["+type+"]", tk.toString());
	}
}
