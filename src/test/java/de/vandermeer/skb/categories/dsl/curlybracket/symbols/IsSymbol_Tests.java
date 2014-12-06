package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.management.RuntimeErrorException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID_Mutable;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype_tests;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype_tests.TestTokens;
import de.vandermeer.skb.categories.kvt.ScopeKey;
import de.vandermeer.skb.categories.kvt.SwitchKey;

public class IsSymbol_Tests {

	@Rule public ExpectedException exception = ExpectedException.none();

	@Test public void testConstructorWExceptions(){
		exception.expect(RuntimeErrorException.class);

		IsScopedID_Mutable scope = IsScopedID_Mutable.createMutable();
		scope.push("one", IsTokentype_tests.TestTokens.STRING);
		IsScopedID isi=scope.getStaticScope();

		IsSymbol.create(null, null, null);
		IsSymbol.create(null, null, IsTokentype_tests.TestTokens.STRING);
		IsSymbol.create(IsID.create("one"), null, TestTokens.STRING);
		IsSymbol.create(null, isi, IsTokentype_tests.TestTokens.STRING);
	}

	@Test public void testTestMe(){
		IsScopedID_Mutable scope = IsScopedID_Mutable.createMutable();
		scope.push("mod1", TestTokens.STRING);
		scope.push("one", TestTokens.STRING);

		IsSymbol sym = IsSymbol.create(IsID.create("one"), scope.getStaticScope(), TestTokens.STRING);

		assertEquals(-1, sym.getLine());
		assertEquals(-1, sym.getColumn());
		assertNull(sym.getSourceName());
		assertNull(sym.getST());

		assertNotNull(sym.getCallScope());
		assertNotNull(sym.getCallScope().get(SwitchKey.ENABLED));
		assertEquals(0, sym.getCallScope().get(SwitchKey.ENABLED).size());
		assertNotNull(sym.getCallScope().get(SwitchKey.DISABLE));
		assertEquals(0, sym.getCallScope().get(SwitchKey.DISABLE).size());

		sym.setLine(10);
		sym.setColum(99);
		sym.setSourceName("me/source.ext");
		assertEquals(10, sym.getLine());
		assertEquals(99, sym.getColumn());
		assertEquals("me/source.ext", sym.getSourceName());

		scope.clear();
		scope.push("me", TestTokens.STRING);
		scope.push("you", TestTokens.STRING);

		sym.changeCallScope(scope.getStaticScope(), ScopeKey.ABOVE, SwitchKey.ENABLED);
		sym.changeCallScope(scope.getStaticScope(), ScopeKey.SELF, SwitchKey.ENABLED);
System.err.println(sym);
	}
}
