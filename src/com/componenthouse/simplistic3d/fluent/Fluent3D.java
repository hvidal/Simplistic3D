/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.fluent;

import com.componenthouse.simplistic3d.objects.Cube;
import com.componenthouse.simplistic3d.objects.Sphere;
import com.componenthouse.simplistic3d.objects.Text3D;
import com.componenthouse.simplistic3d.objects.Wall;
import com.componenthouse.simplistic3d.objects.Tube;

public class Fluent3D {

	private Fluent3D() {} // never

	public static ObjectBuilder3D cube(float side) {
		return new ObjectBuilder3D(new Cube(side));
	}

	public static TextBuilder3D text3D(String text) {
		return new TextBuilder3D(new Text3D(text));
	}

	public static TubeBuilder3D cylinder(float radius, float height) {
		return new TubeBuilder3D(new Tube(radius, radius, height));
	}

	public static TubeBuilder3D cone(float radius, float height) {
		return new TubeBuilder3D(new Tube(0f, radius, height));
	}

	public static SphereBuilder3D sphere(float radius) {
		return new SphereBuilder3D(new Sphere(radius));
	}

	public static WallBuilder3D face(int width, int height) {
		return new WallBuilder3D(new Wall(width, height));
	}
}
