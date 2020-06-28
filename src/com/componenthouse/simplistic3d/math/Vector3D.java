/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.math;

public class Vector3D {
	private float x, y, z;

	public Vector3D() {}

	public Vector3D(Vector3D v) {
		set(v.x, v.y, v.z);
	}

	public Vector3D(float x, float y, float z) {
		set(x, y, z);
	}

	public Vector3D(Vector3D origin, Vector3D destination) {
		set(
			destination.x - origin.x,
			destination.y - origin.y,
			destination.z - origin.z);
	}

	public float x() { return x; }
	public void x(float x) { this.x = x; }

	public float y() { return y; }
	public void y(float y) { this.y = y; }

	public float z() { return z; }
	public void z(float z) { this.z = z; }

	public Vector3D getClone() {
		return new Vector3D(x, y, z);
	}

	public Vector3D set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3D reset() {
		x = y = z = 0f;
		return this;
	}

	public Vector3D add(Vector3D v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vector3D subtract(Vector3D v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public Vector3D multiply(float c) {
		x *= c;
		y *= c;
		z *= c;
		return this;
	}

	public Vector3D divide(float c) {
		x /= c;
		y /= c;
		z /= c;
		return this;
	}

	public float dot(Vector3D v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
		return new Vector3D(
				v1.y * v2.z - v2.y * v1.z,
				v2.x * v1.z - v1.x * v2.z,
				v1.x * v2.y - v2.x * v1.y
		);
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3D normalize() {
		float t = length();
		x /= t;
		y /= t;
		z /= t;
		return this;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vector3D vector3D = (Vector3D) o;
		if (Float.compare(vector3D.x, x) != 0) return false;
		if (Float.compare(vector3D.y, y) != 0) return false;
		if (Float.compare(vector3D.z, z) != 0) return false;
		return true;
	}

	public int hashCode() {
		int result;
		result = x != 0f ? Float.floatToIntBits(x) : 0;
		result = 31 * result + y != 0f ? Float.floatToIntBits(y) : 0;
		result = 31 * result + z != 0f ? Float.floatToIntBits(z) : 0;
		return result;
	}

	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}
}
