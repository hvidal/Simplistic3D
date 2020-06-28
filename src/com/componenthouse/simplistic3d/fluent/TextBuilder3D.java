/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.fluent;

import com.componenthouse.simplistic3d.objects.Text3D;

import java.awt.Font;

public class TextBuilder3D extends AbstractBuilder3D<TextBuilder3D> {

	// Constructor --------------------------------------------------------------

	public TextBuilder3D(Text3D text3D) {
		super(text3D);
		setBuilder(this);
	}

	// Specific Methods --------------------------------------------------------------

	public TextBuilder3D withFont(Font font) {
		((Text3D) object).setFont(font);
		return builder;
	}

	public TextBuilder3D withFont(String name, int style, int size) {
		((Text3D) object).setFont(new Font(name, style, size));
		return builder;
	}
}
