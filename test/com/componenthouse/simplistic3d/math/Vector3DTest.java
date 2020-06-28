/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.math;

import junit.framework.TestCase;

public class Vector3DTest extends TestCase {

	private static final float TOLERANCE = 0.001f;

	public void testCreation() {
		Vector3D v = new Vector3D();
		assertEquals("x should be 0", 0f, v.x());
		assertEquals("y should be 0", 0f, v.y());
		assertEquals("z should be 0", 0f, v.z());
	}

	public void testCreation2() {
		Vector3D a = new Vector3D(10f, 11f, 12f);
		Vector3D b = new Vector3D(-5.1f, 7.2f, -20.1f);
		Vector3D v = new Vector3D(a, b);
		assertEquals("x is wrong", -15.1f, v.x(), TOLERANCE);
		assertEquals("y is wrong", -3.8f, v.y(), TOLERANCE);
		assertEquals("z is wrong", -32.1f, v.z(), TOLERANCE);
	}

	public void testSet() {
		Vector3D v = new Vector3D();
		v.set(1f, 2f, 3f);
		assertEquals("x should be 1", 1f, v.x(), TOLERANCE);
		assertEquals("y should be 2", 2f, v.y(), TOLERANCE);
		assertEquals("z should be 3", 3f, v.z(), TOLERANCE);
	}

	public void testClone() {
		Vector3D v = new Vector3D();
		v.set(8f, 9f, 10f);
		Vector3D clone = v.getClone();
		assertEquals("Objects should be equal", v, clone);
		assertEquals("x should be 1", 8f, clone.x(), TOLERANCE);
		assertEquals("y should be 2", 9f, clone.y(), TOLERANCE);
		assertEquals("z should be 3", 10f, clone.z(), TOLERANCE);
	}

	public void testReset() {
		Vector3D v = new Vector3D();
		v.set(8f, 9f, 10f).reset();
		assertEquals("x should be 0", 0f, v.x(), TOLERANCE);
		assertEquals("y should be 0", 0f, v.y(), TOLERANCE);
		assertEquals("z should be 0", 0f, v.z(), TOLERANCE);
	}

	public void testAdd() {
		Vector3D v1 = new Vector3D(10f, 20f, 30f);
		Vector3D v2 = new Vector3D(15f, 22f, 5f);
		v1.add(v2);
		assertEquals("x should be 25", 25f, v1.x(), TOLERANCE);
		assertEquals("y should be 42", 42f, v1.y(), TOLERANCE);
		assertEquals("z should be 35", 35f, v1.z(), TOLERANCE);
	}

	public void testSubtract() {
		Vector3D v1 = new Vector3D(10f, 20f, 30f);
		Vector3D v2 = new Vector3D(15f, 22f, 5f);
		v1.subtract(v2);
		assertEquals("x should be -5", -5f, v1.x(), TOLERANCE);
		assertEquals("y should be -2", -2f, v1.y(), TOLERANCE);
		assertEquals("z should be 25", 25f, v1.z(), TOLERANCE);
	}

	public void testMultiply() {
		Vector3D v1 = new Vector3D(10f, 20f, -30f);
		v1.multiply(2.5f);
		assertEquals("x should be 25", 25f, v1.x(), TOLERANCE);
		assertEquals("y should be 50", 50f, v1.y(), TOLERANCE);
		assertEquals("z should be -75", -75f, v1.z(), TOLERANCE);
	}

	public void testDivide() {
		Vector3D v1 = new Vector3D(10f, 20f, -30f);
		v1.divide(2.5f);
		assertEquals("x should be 4", 4f, v1.x(), TOLERANCE);
		assertEquals("y should be 8", 8f, v1.y(), TOLERANCE);
		assertEquals("z should be -12", -12f, v1.z(), TOLERANCE);
	}

	public void testDot() {
		Vector3D v1 = new Vector3D(5.5f, 7.8f, 9.1f);
		Vector3D v2 = new Vector3D(6f, 8f, 10f);
		float dot = v1.dot(v2);
		float expected = 5.5f * 6f + 7.8f * 8f + 9.1f * 10f;
		assertEquals("x should be " + expected, expected, dot, TOLERANCE);
	}

	public void testCrossProduct() {
		Vector3D v1 = new Vector3D(0f, 5f, 0f);
		Vector3D v2 = new Vector3D(5f, 0f, 0f);
		Vector3D expected = new Vector3D(0f, 0f, -25f);
		assertEquals("Invalid cross product", expected, Vector3D.crossProduct(v1, v2));

		v1 = new Vector3D(5f, 5f, 0f);
		v2 = new Vector3D(5f, 0f, 5f);
		expected = new Vector3D(25f, -25f, -25f);
		assertEquals("Invalid cross product", expected, Vector3D.crossProduct(v1, v2));
	}

	public void testLength() {
		Vector3D v1 = new Vector3D(0f, 5f, 0f);
		assertEquals("Length should be 5", 5f, v1.length(), TOLERANCE);

		v1.set(5f, 5f, 0f);
		assertEquals("Length should be 7.0710f", 7.0710f, v1.length(), TOLERANCE);

		v1.set(5f, 5f, 5f);
		assertEquals("Length should be 8.6602f", 8.6602f, v1.length(), TOLERANCE);
	}

	public void testNormalize() {
		Vector3D v1 = new Vector3D(0f, 5f, 0f);
		Vector3D expected = new Vector3D(0f, 1f, 0f);
		assertEquals("Normalized vector is wrong", expected, v1.normalize());

		v1 = new Vector3D(5f, 0f, 0f);
		expected = new Vector3D(1f, 0f, 0f);
		assertEquals("Normalized vector is wrong", expected, v1.normalize());

		v1 = new Vector3D(0f, 0f, 5f);
		expected = new Vector3D(0f, 0f, 1f);
		assertEquals("Normalized vector is wrong", expected, v1.normalize());

		v1 = new Vector3D(1f, 1f, 1f);
		v1.normalize();
		assertEquals("Normalized x is wrong", .577f, v1.x(), TOLERANCE);
		assertEquals("Normalized y is wrong", .577f, v1.y(), TOLERANCE);
		assertEquals("Normalized z is wrong", .577f, v1.z(), TOLERANCE);
	}

}
