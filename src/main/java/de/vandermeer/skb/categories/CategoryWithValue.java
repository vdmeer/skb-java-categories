package de.vandermeer.skb.categories;

import java.util.Comparator;

import de.vandermeer.skb.base.Skb_Defaults;
import de.vandermeer.skb.base.Skb_ToStringStyle;
import de.vandermeer.skb.base.Skb_Transformer;

public interface CategoryWithValue {

	/**
	 * Returns the value of an IS category.
	 * @return value of an IS category
	 */
	Object _value();

	default String toLog(Class<?> clazz){
		return Skb_ToStringStyle.parentKV(clazz, Skb_Defaults.DefaultImpl.class, this._value()).toString();
	}

	/**
	 * Standard comparator, useful for SortedLists etc
	 */
	Comparator<CategoryWithValue> comparator=new Comparator<CategoryWithValue>() {
		@Override public int compare(CategoryWithValue to1, CategoryWithValue to2){
			String s1 = (to1==null||to1._value()==null)?"":to1._value().toString();
			String s2 = (to2==null||to2._value()==null)?"":to2._value().toString();
			return s1.compareTo(s2);
		}
	};

	/**
	 * Returns a transformer that takes an object and returns its value as an object if the object is of type {@link CategoryWithValue}.
	 * @return object to object transformer
	 */
	static Skb_Transformer<Object, Object> CAT_TO_VALUE(){
		return new Skb_Transformer<Object, Object>(){
			@Override public Object transform(Object o){
				if(o instanceof CategoryWithValue){
					return (((CategoryWithValue)o)._value());
				}
				return null;
			}
		};
	}

	/**
	 * Returns the value a given object of type {@link CategoryWithValue} as object.
	 * @param input object
	 * @return value object or null
	 */
	static Object GET_VALUE(Object in){
		return CategoryWithValue.CAT_TO_VALUE().transform(in);
	}

	/**
	 * Returns a transformer that takes an object and returns its value as a string if the object is of type {@link CategoryWithValue}.
	 * @return object to string transformer
	 */
	static Skb_Transformer<Object, String> CAT_TO_VALUESTRING(){
		return new Skb_Transformer<Object, String>(){
			@Override public String transform(Object o){
				Object ret=CategoryWithValue.CAT_TO_VALUE().transform(o);
				if(ret!=null){
					return ret.toString();
				}
				return null;
			}
		};
	}

	/**
	 * Returns the value a given object of type {@link CategoryWithValue} as string.
	 * @param input object
	 * @return value string or null
	 */
	static String GET_VALUESTRING(Object in){
		return CategoryWithValue.CAT_TO_VALUESTRING().transform(in);
	}

}
