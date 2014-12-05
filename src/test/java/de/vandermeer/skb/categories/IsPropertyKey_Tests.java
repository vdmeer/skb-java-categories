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

package de.vandermeer.skb.categories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.vandermeer.skb.base.Skb_Defaults;
import de.vandermeer.skb.categories.kvt.IsKey_String;
import de.vandermeer.skb.categories.kvt.IsPropertyKey;

public class IsPropertyKey_Tests {

	@Test public void testNewPropertyKey(){
		IsPropertyKey key;

		key=IsPropertyKey.create();
		assertNotNull(key);
		assertTrue(key instanceof IsKey_String);
		assertTrue(key instanceof IsPropertyKey);
		assertEquals(Skb_Defaults.DEFAULT_VALUE, key.key());
		assertEquals(Skb_Defaults.DEFAULT_VALUE, key._value());
		assertEquals(Skb_Defaults.DEFAULT_DESCRIPTION, key.getDescription());
		assertEquals("IsPropertyKey(DefaultImpl): " + Skb_Defaults.DEFAULT_VALUE, key.toString());

		key=IsPropertyKey.create(null);
		assertNotNull(key);
		assertTrue(key instanceof IsKey_String);
		assertTrue(key instanceof IsPropertyKey);
		assertEquals(Skb_Defaults.DEFAULT_VALUE, key.key());
		assertEquals(Skb_Defaults.DEFAULT_VALUE, key._value());
		assertEquals(Skb_Defaults.DEFAULT_DESCRIPTION, key.getDescription());
		assertEquals("IsPropertyKey(DefaultImpl): " + Skb_Defaults.DEFAULT_VALUE, key.toString());

		key=IsPropertyKey.create("deadbeef");
		assertNotNull(key);
		assertTrue(key instanceof IsKey_String);
		assertTrue(key instanceof IsPropertyKey);
		assertEquals("deadbeef", key.key());
		assertEquals("deadbeef", key._value());
		assertEquals(Skb_Defaults.DEFAULT_DESCRIPTION, key.getDescription());
		assertEquals("IsPropertyKey(DefaultImpl): deadbeef", key.toString());
	}
}
