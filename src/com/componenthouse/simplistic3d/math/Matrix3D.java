/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.math;

import java.util.List;

// http://www.gamedev.net/reference/articles/article877.asp

public class Matrix3D {

	private static final float DEGREE_TO_RADIANS = (float) (Math.PI / 180);

	float ax, ay, az;
	float bx, by, bz;
	float cx, cy, cz;
	float xo, yo, zo;

	public Matrix3D() {
		unit();
	}

	public Matrix3D(Matrix3D m) {
		copy(m);
	}

	public Matrix3D unit() {
		ax = 1.0f; ay = 0.0f; az = 0.0f;
		bx = 0.0f; by = 1.0f; bz = 0.0f;
		cx = 0.0f; cy = 0.0f; cz = 1.0f;
		xo = 0.0f; yo = 0.0f; zo = 0.0f;
		return this;
	}

	public void copy(Matrix3D m) {
		ax = m.ax; ay = m.ay; az = m.az;
		bx = m.bx; by = m.by; bz = m.bz;
		cx = m.cx; cy = m.cy; cz = m.cz;
		xo = m.xo; yo = m.yo; zo = m.zo;
	}

	public Matrix3D rotateX(float theta) {
		float cosTheta = (float) Math.cos(theta * DEGREE_TO_RADIANS);
		float sinTheta = (float) Math.sin(theta * DEGREE_TO_RADIANS);

		float tyx = ay * cosTheta + az * sinTheta;
		float tyy = by * cosTheta + bz * sinTheta;
		float tyz = cy * cosTheta + cz * sinTheta;
		float tyo = yo * cosTheta + zo * sinTheta;

		float tzx = az * cosTheta - ay * sinTheta;
		float tzy = bz * cosTheta - by * sinTheta;
		float tzz = cz * cosTheta - cy * sinTheta;
		float tzo = zo * cosTheta - yo * sinTheta;

		ay = tyx;
		by = tyy;
		cy = tyz;
		yo = tyo;
		az = tzx;
		bz = tzy;
		cz = tzz;
		zo = tzo;
		return this;
	}

	public Matrix3D rotateY(float theta) {
		float cosTheta = (float) Math.cos(theta * DEGREE_TO_RADIANS);
		float sinTheta = (float) Math.sin(theta * DEGREE_TO_RADIANS);

		float txx = ax * cosTheta + az * sinTheta;
		float txy = bx * cosTheta + bz * sinTheta;
		float txz = cx * cosTheta + cz * sinTheta;
		float txo = xo * cosTheta + zo * sinTheta;

		float tzx = az * cosTheta - ax * sinTheta;
		float tzy = bz * cosTheta - bx * sinTheta;
		float tzz = cz * cosTheta - cx * sinTheta;
		float tzo = zo * cosTheta - xo * sinTheta;

		ax = txx;
		bx = txy;
		cx = txz;
		xo = txo;
		az = tzx;
		bz = tzy;
		cz = tzz;
		zo = tzo;
		return this;
	}

	public Matrix3D rotateZ(float theta) {
		float cosTheta = (float) Math.cos(theta * DEGREE_TO_RADIANS);
		float sinTheta = (float) Math.sin(theta * DEGREE_TO_RADIANS);

		float tyx = ay * cosTheta + ax * sinTheta;
		float tyy = by * cosTheta + bx * sinTheta;
		float tyz = cy * cosTheta + cx * sinTheta;
		float tyo = yo * cosTheta + xo * sinTheta;

		float txx = ax * cosTheta - ay * sinTheta;
		float txy = bx * cosTheta - by * sinTheta;
		float txz = cx * cosTheta - cy * sinTheta;
		float txo = xo * cosTheta - yo * sinTheta;

		ay = tyx;
		by = tyy;
		cy = tyz;
		yo = tyo;
		ax = txx;
		bx = txy;
		cx = txz;
		xo = txo;
		return this;
	}

	public void scale(float f) {
		ax *= f;
		bx *= f;
		cx *= f;
		xo *= f;
		ay *= f;
		by *= f;
		cy *= f;
		yo *= f;
		az *= f;
		bz *= f;
		cz *= f;
		zo *= f;
	}

	public void scale(float xf, float yf, float zf) {
		ax *= xf;
		bx *= xf;
		cx *= xf;
		xo *= xf;
		ay *= yf;
		by *= yf;
		cy *= yf;
		yo *= yf;
		az *= zf;
		bz *= zf;
		cz *= zf;
		zo *= zf;
	}

	public void translate(float x, float y, float z) {
		xo += x;
		yo += y;
		zo += z;
	}

	public Matrix3D multiply(Matrix3D m) {
		float txx = ax * m.ax + ay * m.bx + az * m.cx;
		float txy = bx * m.ax + by * m.bx + bz * m.cx;
		float txz = cx * m.ax + cy * m.bx + cz * m.cx;
		float txo = xo * m.ax + yo * m.bx + zo * m.cx + m.xo;

		float tyx = ax * m.ay + ay * m.by + az * m.cy;
		float tyy = bx * m.ay + by * m.by + bz * m.cy;
		float tyz = cx * m.ay + cy * m.by + cz * m.cy;
		float tyo = xo * m.ay + yo * m.by + zo * m.cy + m.yo;

		float tzx = ax * m.az + ay * m.bz + az * m.cz;
		float tzy = bx * m.az + by * m.bz + bz * m.cz;
		float tzz = cx * m.az + cy * m.bz + cz * m.cz;
		float tzo = xo * m.az + yo * m.bz + zo * m.cz + m.zo;

		ax = txx;
		bx = txy;
		cx = txz;
		xo = txo;
		ay = tyx;
		by = tyy;
		cy = tyz;
		yo = tyo;
		az = tzx;
		bz = tzy;
		cz = tzz;
		zo = tzo;
		return this;
	}

	public Vector3D transform(Vector3D v) {
		Vector3D transformed = new Vector3D();
		transform(v, transformed);
		return transformed;
	}

	public void transform(Vector3D v, Vector3D tv) {
		float x = v.x();
		float y = v.y();
		float z = v.z();
		tv.x(x * ax + y * bx + z * cx + xo);
		tv.y(x * ay + y * by + z * cy + yo);
		tv.z(x * az + y * bz + z * cz + zo);
	}

	public void transform(List<Vector3D> source, List<Vector3D> target) {
		int i = 0;
		for (Vector3D v : source) {
			Vector3D t = target.get(i++);
			transform(v, t);
		}
	}

	public String toString() {
		return "[" + ax + ", " + ay + ", " + az + "]\n"
				+ "[" + bx + ", " + by + ", " + bz + "]\n"
				+ "[" + cx + ", " + cy + ", " + cz + "]\n"
				+ "[" + xo + ", " + yo + ", " + zo + "]\n";
	}

}
