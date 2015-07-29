package de.vandermeer.skb.categories.dsl.curlybracket;

import java.util.Deque;
import java.util.Map;

import de.vandermeer.skb.base.categories.IsFactory;
import de.vandermeer.skb.categories.dsl.curlybracket.IsScopedID.PropAttributes;

public abstract class CB_Factory implements IsFactory {

	public static IsScopedID createScopedID(Deque<Object> scope, Map<Object, Map<PropAttributes, Object>> properties){
		return new IsScopedID_Impl(scope, properties);
	}

	public static IsScopedID_Mutable createScopedID_Mutable(){
		return new IsScopedID_Mutable_Impl();
	}
}
