package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.core.Position;
import com.componenthouse.simplistic3d.core.Face3D;
import com.componenthouse.simplistic3d.math.Matrix3D;
import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public interface Object3D {

	public String getName();
	public void setName(String name);

	public void setColor(int rgba);
	public void setColor(Color color);

	public void rotate(float rotateX, float rotateY, float rotateZ);
	public void translate(float translateX, float translateY, float translateZ);
	public void scale(float sx, float sy, float sz);

	public List<Vector3D> getVertices();

	public Face3D getFaceAt(int x, int y);

	public Vector3D getCenter();
	public float avgZ();
	public void transformToCameraSpace(Position viewerPosition);
	public void transformToScreenSpace(Position viewerPosition, Matrix3D matrix, int width, int height, float zTarget);
	public void paint(Graphics g, float ambientLight);
}
