package de.vandermeer.skb.categories.dsl.curlybracket.types;

import static org.junit.Assert.assertEquals;

import javax.management.RuntimeErrorException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.vandermeer.skb.categories.IsID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID_Mutable;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype_tests;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype_tests.TestTokens;

public class IsAbstractType_Tests {

	@Rule public ExpectedException exception = ExpectedException.none();

	@Test public void testConstructorWExceptions(){
		exception.expect(RuntimeErrorException.class);

		IsAbstractTypeConstant.create(null, null, exception);
		IsAbstractTypeReference.create(null, null, null);
		IsAbstractTypeVariable.create(null, null);
	}

	@Test public void testTypeVarialbe(){
		this._typeVariableTest(IsID.create("one"), "ident one", "one");
		this._typeVariableTest(IsID.create(2), "ident two", new Integer(2).toString());
		this._typeVariableTest(IsID.create(3.0), "ident three", new Double(3.0).toString());
		this._typeVariableTest(IsID.create("4"), "ident four", "4");
	}

	private void _typeVariableTest(IsID ident, String description, String dereferenced){
		IsAbstractTypeVariable ti = IsAbstractTypeVariable.create(ident, description);

		assertEquals(dereferenced, ti.getDereferencedType());
		assertEquals(ident, ti.type());
		assertEquals(ident, ti._value());
		assertEquals(description, ti.getDescription());

		//TODO
		//assertEquals(ti.getClass().getSimpleName()+"["+ti.getID().getClass().getSimpleName()+"["+ident.id()+"]"+"]", ti.toString());
	}

	@Test public void testTypeConstant(){
		IsScopedID_Mutable scope = IsScopedID_Mutable.createMutable();

		scope.push("one", IsTokentype.create("string", "string", null));
		this._typeVariableTest(scope.getStaticScope(), IsTokentype.create("string", "string", null), "bt one");

		scope.clear();
		scope.push(2, IsTokentype.create("integer", "integer", null));
		this._typeVariableTest(scope.getStaticScope(), IsTokentype.create("integer", "integer", null), "bt two");

		scope.clear();
		scope.push(3.0, IsTokentype.create("boolean", "boolean", null));
		this._typeVariableTest(scope.getStaticScope(), IsTokentype.create("boolean", "booelan", null), "bt two");

		scope.clear();
		scope.push("4", IsTokentype.create("double", "double", null));
		this._typeVariableTest(scope.getStaticScope(), IsTokentype.create("double", "double", null), "bt four");
	}

	private void _typeVariableTest(IsScopedID scIdent, IsTokentype type, Object description){
		IsAbstractTypeConstant bt = IsAbstractTypeConstant.create(scIdent, type, description);

		assertEquals(type, bt.type());
		assertEquals(type, bt._value());
		assertEquals(type.getDereferencedType(), bt.getDereferencedType());
		assertEquals(description, bt.getDescription());
		assertEquals(scIdent, bt.getScopedID());
		assertEquals(scIdent._value(), bt.getScopedID()._value());

		//TODO
		//assertEquals(bt.getClass().getSimpleName()+"["+scIdent.getClass().getSimpleName()+"["+scIdent._value()+"]"+"::="+bt.type()+"]", bt.toString());
	}

	@Test public void testTypeReferenceWConstant(){
		IsScopedID_Mutable scope = IsScopedID_Mutable.createMutable();
		scope.push("a", TestTokens.STRING);

		IsAbstractTypeConstant bt1 = IsAbstractTypeConstant.create(scope.getStaticScope(), TestTokens.STRING, "a base type");

		scope.clear();
		scope.push("scope1", IsTokentype_tests.TestTokens.REFERENCE);
		IsScopedID sc1=scope.getStaticScope();
		scope.push("scope2", IsTokentype_tests.TestTokens.REFERENCE);
		IsScopedID sc2=scope.getStaticScope();
		scope.push("scope3", TestTokens.REFERENCE);
		IsScopedID sc3=scope.getStaticScope();
		scope.push("scope4", TestTokens.REFERENCE);
		IsScopedID sc4=scope.getStaticScope();

		IsAbstractTypeReference tr1 = IsAbstractTypeReference.create(sc1, bt1, "type ref 11");
		IsAbstractTypeReference tr2 = IsAbstractTypeReference.create(sc2, tr1, "type ref 22");
		IsAbstractTypeReference tr3 = IsAbstractTypeReference.create(sc3, tr2, "type ref 33");
		IsAbstractTypeReference tr4 = IsAbstractTypeReference.create(sc4, tr3, "type ref 44");

		assertEquals(TestTokens.STRING.type, tr1.getDereferencedType());
		assertEquals(TestTokens.STRING.type, tr2.getDereferencedType());
		assertEquals(TestTokens.STRING.type, tr3.getDereferencedType());
		assertEquals(TestTokens.STRING.type, tr4.getDereferencedType());

		assertEquals(sc1, tr1.type());
		assertEquals(sc2, tr2.type());
		assertEquals(sc3, tr3.type());
		assertEquals(sc4, tr4.type());

		assertEquals(sc1, tr1._value());
		assertEquals(sc2, tr2._value());
		assertEquals(sc3, tr3._value());
		assertEquals(sc4, tr4._value());

		assertEquals(bt1, tr1.getTypeReference());
		assertEquals(tr1, tr2.getTypeReference());
		assertEquals(tr2, tr3.getTypeReference());
		assertEquals(tr3, tr4.getTypeReference());

		assertEquals("IsAbstractTypeReference[scope1::=IsAbstractTypeConstant[IsScopedID[a]::=TestTokens[string]]]", tr1.toString());
		assertEquals("[IsScopedID[scope1], IsScopedID[a]]", tr1.getInheritanceTree().toString());

		assertEquals("IsAbstractTypeReference[scope1:scope2::=IsAbstractTypeReference[scope1::=IsAbstractTypeConstant[IsScopedID[a]::=TestTokens[string]]]]", tr2.toString());
		assertEquals("[IsScopedID[scope1:scope2], IsScopedID[scope1], IsScopedID[a]]", tr2.getInheritanceTree().toString());

		assertEquals("IsAbstractTypeReference[scope1:scope2:scope3::=IsAbstractTypeReference[scope1:scope2::=IsAbstractTypeReference[scope1::=IsAbstractTypeConstant[IsScopedID[a]::=TestTokens[string]]]]]", tr3.toString());
		assertEquals("[IsScopedID[scope1:scope2:scope3], IsScopedID[scope1:scope2], IsScopedID[scope1], IsScopedID[a]]", tr3.getInheritanceTree().toString());

		assertEquals("IsAbstractTypeReference[scope1:scope2:scope3:scope4::=IsAbstractTypeReference[scope1:scope2:scope3::=IsAbstractTypeReference[scope1:scope2::=IsAbstractTypeReference[scope1::=IsAbstractTypeConstant[IsScopedID[a]::=TestTokens[string]]]]]]", tr4.toString());
		assertEquals("[IsScopedID[scope1:scope2:scope3:scope4], IsScopedID[scope1:scope2:scope3], IsScopedID[scope1:scope2], IsScopedID[scope1], IsScopedID[a]]", tr4.getInheritanceTree().toString());
	}

	@Test public void testTypeReferenceWVariable(){
		IsScopedID_Mutable scope = IsScopedID_Mutable.createMutable();
//		scope.push("a", DslFactory.get.specialTokentype("variable", "a variable type"));

		IsID iA=IsID.create("a");
//		IsID iB=IsID.create("b");
//		IsID iC=IsID.create("c");
//		IsID iD=IsID.create("d");

		IsAbstractTypeVariable tv1 = IsAbstractTypeVariable.create(iA, "a variable type");
//		IsAbstractTypeVariable tv2 = IsAbstractTypeVariable.create(iB, "a variable type");
//		IsAbstractTypeVariable tv3 = IsAbstractTypeVariable.create(iC, "a variable type");
//		IsAbstractTypeVariable tv4 = IsAbstractTypeVariable.create(iD, "a variable type");

		scope.clear();
		scope.push("scope1", TestTokens.REFERENCE);
		IsScopedID sc1=scope.getStaticScope();
		scope.push("scope2", TestTokens.REFERENCE);
		IsScopedID sc2=scope.getStaticScope();
		scope.push("scope3", TestTokens.REFERENCE);
		IsScopedID sc3=scope.getStaticScope();
		scope.push("scope4", TestTokens.REFERENCE);
		IsScopedID sc4=scope.getStaticScope();

		IsAbstractTypeReference tr1 = IsAbstractTypeReference.create(sc1, tv1, "type ref 11");
		IsAbstractTypeReference tr2 = IsAbstractTypeReference.create(sc2, tr1, "type ref 22");
		IsAbstractTypeReference tr3 = IsAbstractTypeReference.create(sc3, tr2, "type ref 33");
		IsAbstractTypeReference tr4 = IsAbstractTypeReference.create(sc4, tr3, "type ref 44");

		assertEquals("a", tr1.getDereferencedType());
		assertEquals(iA.id(), tr2.getDereferencedType());
		assertEquals("a", tr3.getDereferencedType());
		assertEquals(iA.id(), tr4.getDereferencedType());

		assertEquals(sc1, tr1.type());
		assertEquals(sc2, tr2.type());
		assertEquals(sc3, tr3.type());
		assertEquals(sc4, tr4.type());

		assertEquals(sc1, tr1._value());
		assertEquals(sc2, tr2._value());
		assertEquals(sc3, tr3._value());
		assertEquals(sc4, tr4._value());

		assertEquals(tv1, tr1.getTypeReference());
		assertEquals(tr1, tr2.getTypeReference());
		assertEquals(tr2, tr3.getTypeReference());
		assertEquals(tr3, tr4.getTypeReference());

		assertEquals("IsAbstractTypeReference[scope1::=IsAbstractTypeVariable[IsID[a]]]", tr1.toString());
		assertEquals("[IsScopedID[scope1], IsID[a]]", tr1.getInheritanceTree().toString());

		assertEquals("IsAbstractTypeReference[scope1:scope2::=IsAbstractTypeReference[scope1::=IsAbstractTypeVariable[IsID[a]]]]", tr2.toString());
		assertEquals("[IsScopedID[scope1:scope2], IsScopedID[scope1], IsID[a]]", tr2.getInheritanceTree().toString());

		assertEquals("IsAbstractTypeReference[scope1:scope2:scope3::=IsAbstractTypeReference[scope1:scope2::=IsAbstractTypeReference[scope1::=IsAbstractTypeVariable[IsID[a]]]]]", tr3.toString());
		assertEquals("[IsScopedID[scope1:scope2:scope3], IsScopedID[scope1:scope2], IsScopedID[scope1], IsID[a]]", tr3.getInheritanceTree().toString());

		assertEquals("IsAbstractTypeReference[scope1:scope2:scope3:scope4::=IsAbstractTypeReference[scope1:scope2:scope3::=IsAbstractTypeReference[scope1:scope2::=IsAbstractTypeReference[scope1::=IsAbstractTypeVariable[IsID[a]]]]]]", tr4.toString());
		assertEquals("[IsScopedID[scope1:scope2:scope3:scope4], IsScopedID[scope1:scope2:scope3], IsScopedID[scope1:scope2], IsScopedID[scope1], IsID[a]]", tr4.getInheritanceTree().toString());
	}
}
