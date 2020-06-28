/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.fluent;

import com.componenthouse.simplistic3d.objects.Wall;
import com.componenthouse.simplistic3d.core.ImageFace3D;

import java.awt.Image;
import java.awt.Color;

public class WallBuilder3D extends AbstractBuilder3D<WallBuilder3D> {

	// Constructor --------------------------------------------------------------

	public WallBuilder3D(Wall face) {
		super(face);
		setBuilder(this);
	}

	// Specific Methods --------------------------------------------------------------

	public WallBuilder3D withTexture(Image texture) {
		((Wall) object).setTexture(texture);
		return this;
	}

	public WallBuilder3D withGradientTopDown(Color up, Color down) {
		((Wall) object).setGradient(up, down, ImageFace3D.GradientDirection.TOP_TO_DOWN);
		return this;
	}

	public WallBuilder3D withGradientLeftToRight(Color left, Color right) {
		((Wall) object).setGradient(left, right, ImageFace3D.GradientDirection.LEFT_TO_RIGHT);
		return this;
	}
}