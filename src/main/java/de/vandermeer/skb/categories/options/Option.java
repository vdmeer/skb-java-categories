package de.vandermeer.skb.categories.options;

import de.vandermeer.skb.base.Skb_Defaults;
import de.vandermeer.skb.base.Skb_ToStringStyle;
import de.vandermeer.skb.categories.HasDescription;
import de.vandermeer.skb.categories.kvt.IsKey;
import de.vandermeer.skb.categories.kvt.IsValue;
import de.vandermeer.skb.categories.kvt.KeyValueType;

public interface Option<V> extends HasDescription, KeyValueType<String, V, String> {

	default String toLog(Class<?> clazz){
		return Skb_ToStringStyle.kv(clazz, getKey()._value(), getValue()._value(), getDescription()).toString();
	}

	static <V> Option<V> create(final IsKey<String> key, final IsValue<V> value, final OptionType type, final String description){
		return new Option<V>(){
			@Override public IsKey<String> getKey() {return key;}
			@Override public OptionType getValueType() {return type;}
			@Override public IsValue<V> getValue() {return value;}
			@Override public String toString() {return toLog(Option.class);}
			@Override public Object getDescription() {return (description==null)?Skb_Defaults.DEFAULT_DESCRIPTION:description;}
		};
	}

	static <V> Option<V> create(final String key, V value, String description){
		OptionType type = null;
		if(value.getClass().equals(Boolean.class)){
			type = OptionType.BOOLEAN;
		}
		else if(value.getClass().equals(Character.class)){
			type = OptionType.CHARACHTER;
		}
		else if(value.getClass().equals(String.class)){
			type = OptionType.STRING;
		}
		else if(value.getClass().equals(Double.class)){
			type = OptionType.DOUBLE;
		}
		else if(value.getClass().equals(Integer.class)){
			type = OptionType.INTEGER;
		}
		else if(new char[]{}.getClass().isAssignableFrom(value.getClass())){
			type = OptionType.CHARACTER_ARRAY;
		}
		else{
			throw new RuntimeException("unknown type for option");
		}
		return Option.create(IsKey.create(key), IsValue.create(value), type, description);
	}

	static <V> Option<V> create(final String key, V value){
		return Option.create(key, value, null);
	}

}
