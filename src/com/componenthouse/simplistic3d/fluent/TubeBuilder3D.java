/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.fluent;

import com.componenthouse.simplistic3d.objects.Object3D;
import com.componenthouse.simplistic3d.objects.Tube;

public class TubeBuilder3D extends AbstractBuilder3D<TubeBuilder3D> {

	// Constructor --------------------------------------------------------------

	public TubeBuilder3D(Object3D object) {
		super(object);
		setBuilder(this);
	}

	// Specific Methods --------------------------------------------------------------

	public TubeBuilder3D withSegments(int segments) {
		((Tube) object).setSegments(segments);
		return this;
	}


	public Object3D build() {
		((Tube) object).build();
		return super.build();
	}
}
