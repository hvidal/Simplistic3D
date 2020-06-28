/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.fluent;

import com.componenthouse.simplistic3d.objects.Object3D;

import java.awt.Color;

public class AbstractBuilder3D<T extends AbstractBuilder3D> {

	protected Object3D object;
	protected T builder;

	public AbstractBuilder3D(Object3D object) {
		this.object = object;
	}

	protected void setBuilder(T builder) {
		this.builder = builder;
	}

	// Fluent Methods --------------------------------------------------------------

	public T withName(String name) {
		object.setName(name);
		return builder;
	}

	public T withColor(int rgba) {
		object.setColor(rgba);
		return builder;
	}

	public T withColor(Color color) {
		object.setColor(color);
		return builder;
	}

	public T withTranslucentColor(int rgba) {
		object.setColor(new Color(rgba, true));
		return builder;
	}

	public T rotate(float rotateX, float rotateY, float rotateZ) {
		object.rotate(rotateX, rotateY, rotateZ);
		return builder;
	}

	public T translate(float translateX, float translateY, float translateZ) {
		object.translate(translateX, translateY, translateZ);
		return builder;
	}

	public T scale(float scaleX, float scaleY, float scaleZ) {
		object.scale(scaleX, scaleY, scaleZ);
		return builder;
	}

	public Object3D build() {
		return object;
	}

}
