package de.vandermeer.skb.categories.dsl.curlybracket.types;

import javax.management.RuntimeErrorException;

import de.vandermeer.skb.base.Skb_Defaults;
import de.vandermeer.skb.categories.dsl.curlybracket.HasScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;
import de.vandermeer.skb.categories.dsl.curlybracket.IsTokentype;

public interface IsAbstractTypeConstant extends IsAbstractType, HasScopedID {

	@Override
	default IsTokentype type(){
		return this._value();
	}

	@Override
	IsTokentype _value();

	static IsAbstractTypeConstant create(IsScopedID scIdent, IsTokentype type, Object description){
		if(type==null){
			throw new RuntimeErrorException(new Error("null topen type object: tokentype==null"));
		}
		return new IsAbstractTypeConstant(){
			@Override public String getDereferencedType() {return type.getDereferencedType();}
			@Override public IsScopedID getScopedID() {return scIdent;}
			@Override public IsTokentype _value() {return type;}
			@Override public Object getDescription() {return (description==null)?Skb_Defaults.DEFAULT_DESCRIPTION:description;}

			//@Override public String toString(){return toLog(IsAbstractTypeConstant.class);}
			@Override public String toString(){return IsAbstractTypeConstant.class.getSimpleName() + "[" + scIdent + "::=" + type + "]";}
		};
	}
}
