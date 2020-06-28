/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.core;

import com.componenthouse.simplistic3d.math.Vector3D;

import java.util.List;

public class SceneBox {
	Vector3D min;
	Vector3D max;

	public SceneBox(List<Vector3D> vertices) {
		Vector3D first = vertices.get(0);
		float xmin = first.x(), xmax = xmin;
		float ymin = first.y(), ymax = ymin;
		float zmin = first.z(), zmax = zmin;

		for (int i = 1; i < vertices.size(); i++) {
			Vector3D v = vertices.get(i);
			float x = v.x();
			if (x < xmin) xmin = x;
			if (x > xmax) xmax = x;

			float y = v.y();
			if (y < ymin) ymin = y;
			if (y > ymax) ymax = y;

			float z = v.x();
			if (z < zmin) zmin = z;
			if (z > zmax) zmax = z;
		}

		min = new Vector3D(xmin, ymin, zmin);
		max = new Vector3D(xmax, ymax, zmax);
	}

	public void combine(SceneBox bb) {
		Vector3D v = bb.min;
		if (v.x() < min.x()) min.x(v.x());
		if (v.y() < min.y()) min.y(v.y());
		if (v.z() < min.z()) min.z(v.z());

		v = bb.max;
		if (v.x() > max.x()) max.x(v.x());
		if (v.y() > max.y()) max.y(v.y());
		if (v.z() > max.z()) max.z(v.z());
	}

	public Vector3D getCenter() {
		float xmid = (min.x() + max.x()) / 2;
		float ymid = (min.y() + max.y()) / 2;
		float zmid = (min.z() + max.z()) / 2;
		return new Vector3D(xmid, ymid, zmid);
	}

	public float getWidth() {
		return max.x() - min.x();
	}

	public float getHeight() {
		return max.y() - min.y();
	}

	public float getDepth() {
		return max.z() - min.z();
	}

}
