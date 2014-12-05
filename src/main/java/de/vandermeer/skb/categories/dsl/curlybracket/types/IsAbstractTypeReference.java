package de.vandermeer.skb.categories.dsl.curlybracket.types;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import de.vandermeer.skb.base.Skb_Defaults;
import de.vandermeer.skb.base.utils.Skb_ClassUtils;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID;

public interface IsAbstractTypeReference extends IsAbstractType {

	@Override
	default IsScopedID type(){
		return this._value();
	}

	@Override IsScopedID _value();

	IsAbstractType getTypeReference();

	List<Object> getInheritanceTree();

	static IsAbstractTypeReference create(final IsScopedID scopedIdent, final IsAbstractType type, final Object description){
		if(type==null){
			throw new RuntimeErrorException(new Error("null type object: type==null"));
		}
		if(type.getDereferencedType()==null){
			throw new RuntimeErrorException(new Error("null dereferenced type object: type.getDereferencedType()==null"));
		}

		return new IsAbstractTypeReference(){
			@Override public String getDereferencedType() {return type.getDereferencedType();}
			@Override public IsScopedID _value() {return scopedIdent;}
			@Override public IsAbstractType getTypeReference() {return type;}

			@Override
			public List<Object> getInheritanceTree() {
				List<Object> ret = new ArrayList<Object>();
				ret.add(scopedIdent);
				if(Skb_ClassUtils.INSTANCE_OF(IsAbstractTypeConstant.class).test(type)){
					ret.add(((IsAbstractTypeConstant)type).getScopedID());
				}
				else if(Skb_ClassUtils.INSTANCE_OF(IsAbstractTypeVariable.class).test(type)){
					ret.add(((IsAbstractTypeVariable)type).getID());
				}
				else if(Skb_ClassUtils.INSTANCE_OF(IsAbstractTypeReference.class).test(type)){
					ret.addAll(((IsAbstractTypeReference)type).getInheritanceTree());
				}
				return ret;
			}

			@Override public Object getDescription() {return (description==null)?Skb_Defaults.DEFAULT_DESCRIPTION:description;}

			//@Override public String toString(){return toLog(IsAbstractTypeReference.class);}
			@Override public String toString(){return IsAbstractTypeReference.class.getSimpleName() + "[" + scopedIdent.render() + "::=" + type + "]";}
		};
	}
}
