/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.core;

import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Face3D {
	protected List<Vector3D> vertices = new ArrayList<Vector3D>();
	protected Vector3D center;
	protected Vector3D normal;
	protected Color color;

	public Face3D(Color c) {
		this.color = c;
		center = new Vector3D();
	}

	Face3D() {
		this(Color.GRAY);
	}

	public Vector3D normal() { return normal; }
	public void normal(Vector3D normal) { this.normal = normal; }

	public Vector3D getVertexAt(int pos) {
		return vertices.get(pos);
	}

	public Face3D addVertex(Vector3D vertex) {
		if (vertex != null && !vertices.contains(vertex))
			vertices.add(vertex);
		return this;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFrontVisible() {
		return normal != null && center.dot(normal) < 0;
	}

	public boolean visible() {
		return isFrontVisible();
	}

	public void paint(Graphics g, float ambientLight) {
		float z = -center.dot(normal);
		Color faceColor = color;
		if (z > 0) {
			z *= 1 - ambientLight;
			ambientLight *= 255;
			float red = ambientLight + z * color.getRed();
			float green = ambientLight + z * color.getGreen();
			float blue = ambientLight + z * color.getBlue();
			faceColor = new Color((int) red, (int) green, (int) blue, color.getAlpha());
		}
		int verticesX[] = new int[vertices.size()];
		int verticesY[] = new int[vertices.size()];
		int i = 0;
		for (Vector3D vertex : vertices) {
			verticesX[i] = (int) vertex.x();
			verticesY[i] = (int) vertex.y();
			i++;
		}
		paintFace((Graphics2D) g, verticesX, verticesY, faceColor);
	}

	protected void paintFace(Graphics2D g, int verticesX[], int verticesY[], Color color) {
		g.setColor(color);
		g.fillPolygon(verticesX, verticesY, verticesX.length);
	}

	public boolean getFaceAt(int x, int y) {
		int verticesX[] = new int[vertices.size()];
		int verticesY[] = new int[vertices.size()];
		int i = 0;
		for (Vector3D vector3D : vertices) {
			verticesX[i] = (int) vector3D.x();
			verticesY[i] = (int) vector3D.y();
			i++;
		}
		Polygon p = new Polygon(verticesX, verticesY, vertices.size());
		return p.contains(x, y);
	}

	public void computeCenter() {
		center.set(0, 0, 0);
		for (Vector3D vector3D : vertices) {
			center.add(vector3D);
		}
		center.divide(vertices.size());
		center.normalize();
	}

	public void computeNormal() {
		normal = new Vector3D();

		int n = vertices.size();
		Vector3D first = vertices.get(0);
		Vector3D last = vertices.get(n - 1);
		Vector3D prev = new Vector3D(last, first);

		for (int i = 0; i < n - 1; i++) {
			Vector3D v = new Vector3D(vertices.get(i), vertices.get(i+1));
			normal.add(Vector3D.crossProduct(prev, v));
			prev = v;
		}
		normal.normalize();
	}

	public float avgZ() {
		float avg = vertices.get(0).z();
		for (int i = 1; i < vertices.size(); i++) {
			Vector3D vertex = vertices.get(i);
			avg += vertex.z();
		}
		return avg / (float) vertices.size();
	}
}
