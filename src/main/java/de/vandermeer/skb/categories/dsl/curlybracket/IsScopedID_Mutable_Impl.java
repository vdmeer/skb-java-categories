package de.vandermeer.skb.categories.dsl.curlybracket;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class IsScopedID_Mutable_Impl extends IsScopedID_Impl implements IsScopedID_Mutable {

	IsScopedID_Mutable_Impl(){
		super(new ArrayDeque<Object>(), new HashMap<Object, Map<PropAttributes, Object>>());
	}

	@Override
	public void push(Object element, IsTokentype symbolType){
		if(element!=null && symbolType!=null){
			this.scope.addFirst(element);

			Map<PropAttributes, Object> newElem = new HashMap<PropAttributes, Object>();
			newElem.put(PropAttributes.SYMBOL_TYPE, symbolType);
			this.properties.put(element, newElem);
		}
	}

	@Override
	public Object pop(){
		Object ret = null;
		try{
			ret = this.scope.removeFirst();
			this.properties.remove(ret);
		}
		catch(Exception ignore){}
		return ret;
	}

	@Override
	public void clear(){
		this.scope.clear();
		this.properties.clear();
	}

	@Override
	public void changeSymbolType(Object element, IsTokentype symbolType){
		if(this.scope.contains(element)){
			Map<PropAttributes, Object> changeElem = this.properties.get(element);
			changeElem.put(PropAttributes.SYMBOL_TYPE, symbolType);
		}
	}

	@Override
	public IsScopedID getStaticScope(){
		return IsScopedID.create(scope, properties);
//
//		Map<Object, Map<PropAttributes, Object>> props = new HashMap<Object, Map<PropAttributes, Object>>();
//		props.putAll(this.properties);
//
//		Deque<Object> sc = new ArrayDeque<Object>();
//		for(Object o:this.scope){
//			sc.addLast(o);
//		}
//
//		return IsScopedID.create(sc, props);
	}
}
