package de.vandermeer.skb.categories.dsl.curlybracket.types;

import javax.management.RuntimeErrorException;

import de.vandermeer.skb.base.Skb_Defaults;
import de.vandermeer.skb.categories.HasID;
import de.vandermeer.skb.categories.IsID;

public interface IsAbstractTypeVariable extends IsAbstractType, HasID {

	@Override
	default IsID type(){
		return this._value();
	}

	@Override IsID _value();

	static IsAbstractTypeVariable create(IsID type, Object description){
		if(type==null){
			throw new RuntimeErrorException(new Error("null ident type object: identtype==null"));
		}
		if(type.id()==null){
			throw new RuntimeErrorException(new Error("null ident type ident: identtype.getIdent()==null"));
		}

		return new IsAbstractTypeVariable(){
			@Override public String getDereferencedType() {return type.id().toString();}
			@Override public IsID _value() {return type;}
			@Override public IsID getID() {return type;}
			@Override public Object getDescription() {return (description==null)?Skb_Defaults.DEFAULT_DESCRIPTION:description;}

			//@Override public String toString(){return toLog(IsAbstractTypeConstant.class);}
			@Override public String toString(){return IsAbstractTypeVariable.class.getSimpleName() + "[" + type + "]";}
		};
	}
}
