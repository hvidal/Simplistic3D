/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.fluent;

import com.componenthouse.simplistic3d.objects.Sphere;
import com.componenthouse.simplistic3d.objects.Object3D;

public class SphereBuilder3D extends AbstractBuilder3D<SphereBuilder3D> {

	// Constructor --------------------------------------------------------------

	public SphereBuilder3D(Object3D object) {
		super(object);
		setBuilder(this);
	}

	// Specific Methods --------------------------------------------------------------

	public SphereBuilder3D withHorizontalSegments(int segments) {
		((Sphere) object).setHorizontalSegments(segments);
		return this;
	}

	public SphereBuilder3D withVerticalSegments(int segments) {
		((Sphere) object).setVerticalSegments(segments);
		return this;
	}

	public Object3D build() {
		((Sphere) object).build();
		return super.build();
	}
}
