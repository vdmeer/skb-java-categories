package de.vandermeer.skb.categories.dsl.curlybracket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID.PropAttributes;

public class IsScopedID_Tests {

	@Test public void testConstructorRaw(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertNotNull(sn.getScope());
		assertNotNull(sn._value());

		assertTrue(sn.getScope().isEmpty());
		assertEquals(0, sn.size());

		assertNull(sn.peekFirst());
		assertNotNull(sn.peekFirstProperties());
		assertEquals(0, sn.peekFirstProperties().size());

		assertNull(sn.peekLast());
		assertNotNull(sn.peekLastProperties());
		assertEquals(0, sn.peekLastProperties().size());
	}


	@Test public void testConstructorWScope(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertNotNull(sn.getScope());
		assertTrue(sn.getScope().isEmpty());

		assertEquals(0, sn.size());

		assertNull(sn.peekFirst());
		assertNotNull(sn.peekFirstProperties());
		assertEquals(0, sn.peekFirstProperties().size());

		assertNull(sn.peekLast());
		assertNotNull(sn.peekLastProperties());
		assertEquals(0, sn.peekLastProperties().size());
	}

	@Test public void testPeekRaw(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();
		IsTokentype tt = IsTokentype.create("string", "string", "a string");

		mscope.push("p0", tt);
		mscope.push("p1", tt);
		mscope.push("p2", tt);
		mscope.push("p3", tt);

		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertFalse(sn.isEmpty());
		assertEquals(4, sn.size());
		assertEquals("p0", sn.peekLast());
		assertTrue(sn.contains("p0"));
		assertTrue(sn.contains("p1"));
		assertTrue(sn.contains("p2"));
		assertTrue(sn.contains("p3"));
		assertEquals("p3", sn.peekFirst());
	}

	@Test public void testPeekWScope(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();
		IsTokentype tt = IsTokentype.create("string", "string", "a string");

		mscope.push("p0", tt);
		mscope.push("p1", tt);
		mscope.push("p2", tt);
		mscope.push("p3", tt);

		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertFalse(sn.isEmpty());
		assertEquals(4, sn.size());
		assertEquals("p0", sn.peekLast());
		assertTrue(sn.contains("p0"));
		assertTrue(sn.contains("p1"));
		assertTrue(sn.contains("p2"));
		assertTrue(sn.contains("p3"));
		assertEquals("p3", sn.peekFirst());
	}

	@Test public void testGettestGetSymbolTypeRaw(){
		IsScopedID_Mutable mscope=TestScope.testScope();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertEquals(TestScope.ttString, sn.getSymbolType("test"));
		assertEquals(TestScope.ttInteger, sn.getSymbolType(1));
		assertEquals(TestScope.ttDouble, sn.getSymbolType(3.1415));
		assertEquals(TestScope.ttBoolean, mscope.getSymbolType(true));

		assertNull(sn.getSymbolType("1"));
		assertNull(sn.getSymbolType("3.1415"));
		assertNull(sn.getSymbolType("true"));
	}

	@Test public void testGettestGetSymbolTypeWScope(){
		IsScopedID_Mutable mscope=TestScope.testScope();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertEquals(TestScope.ttString, sn.getSymbolType("test"));
		assertEquals(TestScope.ttInteger, sn.getSymbolType(1));
		assertEquals(TestScope.ttDouble, sn.getSymbolType(3.1415));
		assertEquals(TestScope.ttBoolean, mscope.getSymbolType(true));

		assertNull(sn.getSymbolType("1"));
		assertNull(sn.getSymbolType("3.1415"));
		assertNull(sn.getSymbolType("true"));
	}

	@Test public void testGetValueRaw(){
		IsScopedID_Mutable mscope=TestScope.testScope();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertEquals("test:1:3.1415:true", sn.render());
	}

	@Test public void testGetValueWScope(){
		IsScopedID_Mutable mscope=TestScope.testScope();

		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());
		assertEquals("test:1:3.1415:true", sn.render());
	}

	@Test public void testGetPropertiesRaw(){
		IsScopedID_Mutable mscope=TestScope.testScope();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertEquals(TestScope.ttString, sn.getProperties("test").get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttInteger, sn.getProperties(1).get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttDouble, sn.getProperties(3.1415).get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttBoolean, sn.getProperties(true).get(PropAttributes.SYMBOL_TYPE));

		assertEquals(TestScope.ttString, sn.peekLastProperties().get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttBoolean, sn.peekFirstProperties().get(PropAttributes.SYMBOL_TYPE));
	}

	@Test public void testGetPropertiesWScope(){
		IsScopedID_Mutable mscope=TestScope.testScope();
		IsScopedID sn = CB_Factory.createScopedID(mscope.getScope(), mscope.getProperties());

		assertEquals(TestScope.ttString, sn.getProperties("test").get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttInteger, sn.getProperties(1).get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttDouble, sn.getProperties(3.1415).get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttBoolean, sn.getProperties(true).get(PropAttributes.SYMBOL_TYPE));

		assertEquals(TestScope.ttString, sn.peekLastProperties().get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttBoolean, sn.peekFirstProperties().get(PropAttributes.SYMBOL_TYPE));
	}

}
