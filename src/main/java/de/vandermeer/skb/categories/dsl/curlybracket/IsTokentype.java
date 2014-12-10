package de.vandermeer.skb.categories.dsl.curlybracket;

import javax.management.RuntimeErrorException;

import de.vandermeer.skb.base.Skb_Defaults;

public interface IsTokentype extends IsDerefType {

	static IsTokentype create(final String type, final String derefType, final Object description){
		if(type==null){
			throw new RuntimeErrorException(new Error("null token type object: tokentype==null"));
		}

		return new IsTokentype() {
			@Override public String _value() {return type;}
			@Override public String getDereferencedType() {return derefType;}

			@Override public Object getDescription() {return (description==null)?Skb_Defaults.DEFAULT_DESCRIPTION:description;}

			//@Override public String toString(){return toLog(IsTokentype.class);}
			@Override public String toString(){return IsTokentype.class.getSimpleName() + "[" + type + "]";}
		};
	}
}
