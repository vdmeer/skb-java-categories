package de.vandermeer.skb.categories.dsl.curlybracket.symbols;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.management.RuntimeErrorException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.vandermeer.skb.base.categories.IsID;
import de.vandermeer.skb.base.categories.kvt.ScopeKey;
import de.vandermeer.skb.base.categories.kvt.SwitchKey;
import de.vandermeer.skb.categories.dsl.curlybracket.CB_Factory;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID_Mutable;
import de.vandermeer.skb.categories.dsl.curlybracket.Test_IsTokentype;
import de.vandermeer.skb.categories.dsl.curlybracket.Test_IsTokentype.TestTokens;

public class Test_IsSymbol {

	@Rule public ExpectedException exception = ExpectedException.none();

	@Test public void testConstructorWExceptions(){
		exception.expect(RuntimeErrorException.class);

		IsScopedID_Mutable scope = CB_Factory.createScopedID_Mutable();
		scope.push("one", Test_IsTokentype.TestTokens.STRING);
		IsScopedID isi=scope.getStaticScope();

		new IsSymbol_Impl(null, null, null);
		new IsSymbol_Impl(null, null, Test_IsTokentype.TestTokens.STRING);
		new IsSymbol_Impl(IsID.create("one"), null, TestTokens.STRING);
		new IsSymbol_Impl(null, isi, Test_IsTokentype.TestTokens.STRING);
	}

	@Test public void testTestMe(){
		IsScopedID_Mutable scope = CB_Factory.createScopedID_Mutable();
		scope.push("mod1", TestTokens.STRING);
		scope.push("one", TestTokens.STRING);

		IsSymbol sym = new IsSymbol_Impl(IsID.create("one"), scope.getStaticScope(), TestTokens.STRING);

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
