/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.core;

import com.componenthouse.simplistic3d.objects.Object3D;
import com.componenthouse.simplistic3d.objects.Wall;
import com.componenthouse.simplistic3d.math.Vector3D;
import com.componenthouse.simplistic3d.math.PlaneEquation;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BSPTree {
	private BSPTree front;
	private BSPTree back;
	private Wall wall;
	private List<Object3D> objects;

	public BSPTree(Object3D object3D) {
		if (object3D instanceof Wall) {
			wall = (Wall) object3D;
		} else {
			objects = new ArrayList<Object3D>();
			objects.add(object3D);
		}
	}

	public void addToTree(Object3D object3D) {
		if (wall == null) {
			if (object3D instanceof Wall) {
				wall = (Wall) object3D;
				for (Object3D object : objects) {
					addToTree(object);
				}
			} else {
				objects.add(object3D);
			}
		} else {
			Vector3D normal = wall.getNormal();
			PlaneEquation planeEquation = new PlaneEquation(normal, wall.getCenter());
			float value = planeEquation.distanceTo(object3D.getCenter());
			if (value > 0) {
				if (front != null)
					front.addToTree(object3D);
				else
					front = new BSPTree(object3D);
			} else {
				if (back != null)
					back.addToTree(object3D);
				else
					back = new BSPTree(object3D);
			}
		}
	}

	public void setPaintOrder(List<Object3D> objects) {
		if (this.objects != null) {
			sort();
			objects.addAll(this.objects);
			return;
		}

		if (wall.isFrontVisible()) {
			if (back != null)
				back.setPaintOrder(objects);

			objects.add(wall);

			if (front != null)
				front.setPaintOrder(objects);
		} else {
			if (front != null)
				front.setPaintOrder(objects);

			objects.add(wall);

			if (back != null)
				back.setPaintOrder(objects);
		}
	}

	private void sort() {
		Collections.sort(objects, new Comparator<Object3D>() {
			public int compare(Object3D o1, Object3D o2) {
				return (int) (o1.avgZ() - o2.avgZ());
			}
		});
	}

}
