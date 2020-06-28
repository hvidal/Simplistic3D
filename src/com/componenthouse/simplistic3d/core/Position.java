/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.core;

import com.componenthouse.simplistic3d.math.Matrix3D;

public class Position {

	public Matrix3D rotation, scale, translation;

	public Position() {
		rotation = new Matrix3D();
		scale = new Matrix3D();
		translation = new Matrix3D();
	}

	public Position(Position p) {
		this();
		copyPos(p);
	}

	void copyPos(Position p) {
		rotation.copy(p.rotation);
		scale.copy(p.scale);
		translation.copy(p.translation);
	}

	void setRot(float rx, float ry, float rz) {
		rotation.unit();
		incRotation(rx, ry, rz);
	}

	void setScale(float sx, float sy, float sz) {
		scale.unit();
		incScale(sx, sy, sz);
	}

	void setTrans(float tx, float ty, float tz) {
		translation.unit();
		incTranslation(tx, ty, tz);
	}

	public void incRotation(float rx, float ry, float rz) {
		if (rx != 0)
			rotation.rotateX(rx);
		if (ry != 0)
			rotation.rotateY(ry);
		if (rz != 0)
			rotation.rotateZ(rz);
	}

	public void incScale(float sx, float sy, float sz) {
		scale.scale(sx, sy, sz);
	}

	public void incTranslation(float tx, float ty, float tz) {
		translation.translate(tx, ty, tz);
	}

}
