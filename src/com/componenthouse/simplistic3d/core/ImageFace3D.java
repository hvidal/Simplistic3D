/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.core;

import com.componenthouse.simplistic3d.contrib.PerspectiveFilter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;

public class ImageFace3D extends Face3D {

	public static enum GradientDirection {
		TOP_TO_DOWN,
		LEFT_TO_RIGHT
	}

	private int width;
	private int height;
	private BufferedImage texture;

	public ImageFace3D() {}

	public void setTexture(Image texture) {
		this.texture = new BufferedImage(texture.getWidth(null), texture.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = this.texture.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(texture, 0, 0, null);
		g.dispose();
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setGradient(Color color1, Color color2, GradientDirection direction) {
		int w = width * 10;
		int h = height * 10;
		this.texture = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = this.texture.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint paint;
		if (direction == GradientDirection.TOP_TO_DOWN)
			paint = new GradientPaint(0f, 0f, color1, 0f, h, color2);
		else
			paint = new GradientPaint(0f, 0f, color1, w, 0f, color2);
		g.setPaint(paint);
		g.fillRect(0, 0, w, h);
		g.dispose();
	}

	public boolean visible() {
		return true;
	}

	protected void paintFace(Graphics2D g, int xvert[], int yvert[], Color color) {
		if (texture == null) {
			super.paintFace(g, xvert, yvert, color);
			return;
		}

		boolean widthZero = xvert[0] == xvert[1] && xvert[1] == xvert[2] && xvert[2] == xvert[3];
		boolean heightZero = yvert[0] == yvert[1] && yvert[1] == yvert[2] && yvert[2] == yvert[3];
		if (widthZero || heightZero)
			return;

		int smallerX = xvert[3];
		int smallerY = yvert[3];
		for (int i = 0; i < 3; i++) {
			if (xvert[i] < smallerX) {
				smallerX = xvert[i];
			}
			if (yvert[i] < smallerY)
				smallerY = yvert[i];
		}

		PerspectiveFilter perspectiveFilter = new PerspectiveFilter(
				xvert[3], yvert[3],
				xvert[2], yvert[2],
				xvert[1], yvert[1],
				xvert[0], yvert[0]);
		try {
			g.drawImage(texture, perspectiveFilter, smallerX, smallerY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
