/* Copyright 2014 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.skb.categories.kvt;

import de.vandermeer.skb.categories.CategoryIs;
import de.vandermeer.skb.categories.CategoryWithValue;
import de.vandermeer.skb.categories.HasDescription;

/**
 * Category of objects that represent a type.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.2 build 140611 (11-Jun-14) with Java 1.8
 */
public interface IsType<T> extends CategoryIs, CategoryWithValue, HasDescription {
	/**
	 * Returns the type something is of.
	 * @return type
	 */
	default T type(){
		return this._value();
	}

	T _value();
}
