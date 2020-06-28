/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.math;

import junit.framework.TestCase;

public class Matrix3DTest extends TestCase {

	public void testCreation() {
		Matrix3D m = new Matrix3D();
		assertEquals("ax is wrong", 1f, m.ax);
		assertEquals("ay is wrong", 0f, m.ay);
		assertEquals("az is wrong", 0f, m.az);

		assertEquals("bx is wrong", 0f, m.bx);
		assertEquals("by is wrong", 1f, m.by);
		assertEquals("bz is wrong", 0f, m.bz);

		assertEquals("cx is wrong", 0f, m.cx);
		assertEquals("cy is wrong", 0f, m.cy);
		assertEquals("cz is wrong", 1f, m.cz);

		assertEquals("xo is wrong", 0f, m.xo);
		assertEquals("yo is wrong", 0f, m.yo);
		assertEquals("zo is wrong", 0f, m.zo);
	}

}
