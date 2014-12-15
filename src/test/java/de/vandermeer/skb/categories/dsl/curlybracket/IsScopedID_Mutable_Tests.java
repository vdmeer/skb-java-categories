package de.vandermeer.skb.categories.dsl.curlybracket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID.PropAttributes;

public class IsScopedID_Mutable_Tests {

	@Test public void testScopeConstructor(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();

		assertNotNull(mscope.getScope());
		assertTrue(mscope.getScope().isEmpty());

		assertEquals(0, mscope.size());

		assertNull(mscope.peekFirst());
		assertNotNull(mscope.peekFirstProperties());
		assertEquals(0, mscope.peekFirstProperties().size());

		assertNull(mscope.peekLast());
		assertNotNull(mscope.peekLastProperties());
		assertEquals(0, mscope.peekLastProperties().size());

		assertNull(mscope.pop());
	}

	@Test public void testPush(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();

		mscope.push("test", TestScope.ttString);
		mscope.push(1, 		TestScope.ttInteger);
		mscope.push(3.1415, TestScope.ttDouble);

		assertEquals("test", mscope.getScope().getLast());
		assertTrue(mscope.getScope().contains(1));
		assertEquals(3.1415, mscope.getScope().getFirst());
	}

	@Test public void testPop(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();

		mscope.push("p0", TestScope.tt);
		mscope.push("p1", TestScope.tt);
		mscope.push("p2", TestScope.tt);
		mscope.push("p3", TestScope.tt);

		assertFalse(mscope.isEmpty());
		assertEquals(4, mscope.size());
		assertEquals("p0", mscope.peekLast());
		assertTrue(mscope.contains("p0"));
		assertTrue(mscope.contains("p1"));
		assertTrue(mscope.contains("p2"));
		assertTrue(mscope.contains("p3"));
		assertEquals("p3", mscope.peekFirst());

		mscope.pop();
		assertFalse(mscope.isEmpty());
		assertEquals(3, mscope.size());
		assertEquals("p0", mscope.peekLast());
		assertTrue(mscope.contains("p0"));
		assertTrue(mscope.contains("p1"));
		assertTrue(mscope.contains("p2"));
		assertEquals("p2", mscope.peekFirst());

		mscope.pop();
		assertFalse(mscope.isEmpty());
		assertEquals(2, mscope.size());
		assertEquals("p0", mscope.peekLast());
		assertTrue(mscope.contains("p0"));
		assertTrue(mscope.contains("p1"));
		assertEquals("p1", mscope.peekFirst());

		mscope.pop();
		assertFalse(mscope.isEmpty());
		assertEquals(1, mscope.size());
		assertEquals("p0", mscope.peekLast());
		assertTrue(mscope.contains("p0"));
		assertEquals("p0", mscope.peekFirst());

		mscope.pop();
		assertTrue(mscope.isEmpty());
		assertEquals(0, mscope.size());

		mscope.pop();
		assertTrue(mscope.isEmpty());
		assertEquals(0, mscope.size());

		mscope.pop();
		assertTrue(mscope.isEmpty());
		assertEquals(0, mscope.size());
	}

	@Test public void testGetValue(){
		IsScopedID_Mutable mscope = new IsScopedID_Mutable_Impl();

		mscope.push("one", TestScope.tt);
		assertEquals(1, mscope.size());
		assertEquals("one", mscope.render());
		assertEquals("one", mscope.peekFirst());
		assertEquals("one", mscope.peekLast());

		mscope.push("two", TestScope.tt);
		assertEquals(2, mscope.size());
		assertEquals("one:two", mscope.render());
		assertEquals("two", mscope.peekFirst());
		assertEquals("one", mscope.peekLast());

		mscope.push("three", TestScope.tt);
		assertEquals(3, mscope.size());
		assertEquals("one:two:three", mscope.render());
		assertEquals("three", mscope.peekFirst());
		assertEquals("one", mscope.peekLast());

		assertEquals("three", mscope.pop());
		assertEquals(2, mscope.size());
		assertEquals("one:two", mscope.render());
		assertEquals("two", mscope.peekFirst());
		assertEquals("one", mscope.peekLast());

		assertEquals("two", mscope.pop());
		assertEquals(1, mscope.size());
		assertEquals("one", mscope.render());
		assertEquals("one", mscope.peekFirst());
		assertEquals("one", mscope.peekLast());

		assertEquals("one", mscope.pop());
		assertEquals(0, mscope.size());
		assertEquals("", mscope.render());
		assertNull(mscope.peekFirst());
		assertNull(mscope.peekLast());
	}

	@Test public void testGetSymbolType(){
		IsScopedID_Mutable mscope=TestScope.testScope();

		assertEquals(TestScope.ttString, mscope.getSymbolType("test"));
		assertEquals(TestScope.ttInteger, mscope.getSymbolType(1));
		assertEquals(TestScope.ttDouble, mscope.getSymbolType(3.1415));
		assertEquals(TestScope.ttBoolean, mscope.getSymbolType(true));

		assertNull(mscope.getSymbolType("1"));
		assertNull(mscope.getSymbolType("3.1415"));
		assertNull(mscope.getSymbolType("true"));
	}

	@Test public void testGetProperties(){
		IsScopedID_Mutable mscope=TestScope.testScope();

		assertEquals(TestScope.ttString, mscope.getProperties("test").get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttInteger, mscope.getProperties(1).get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttDouble, mscope.getProperties(3.1415).get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttBoolean, mscope.getProperties(true).get(PropAttributes.SYMBOL_TYPE));

		assertEquals(TestScope.ttString, mscope.peekLastProperties().get(PropAttributes.SYMBOL_TYPE));
		assertEquals(TestScope.ttBoolean, mscope.peekFirstProperties().get(PropAttributes.SYMBOL_TYPE));
	}

	@Test public void testGetValueSep(){
//		IsScopedID_Mutable mscope = IsScopedID_Mutable.createMutable();
//		Options ol=new Options(mscope.OPTION_SEPARATOR("@@"));
//
//		mscope.push("one", TestScope.tt);
//		assertEquals("one", mscope.getValue(ol).toString());
//
//		mscope.push("two", TestScope.tt);
//		assertEquals("one@@two", mscope.getValue(ol).toString());
//
//		mscope.push("three", TestScope.tt);
//		assertEquals("one@@two@@three", mscope.getValue(ol).toString());
//
//		assertEquals("three", mscope.pop());
//		assertEquals("one@@two", mscope.getValue(ol).toString());
//
//		assertEquals("two", mscope.pop());
//		assertEquals("one", mscope.getValue(ol).toString());
//
//		assertEquals("one", mscope.pop());
//		assertEquals("", mscope.getValue(ol).toString());
	}
}
