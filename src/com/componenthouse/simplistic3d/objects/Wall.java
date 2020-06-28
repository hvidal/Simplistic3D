package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.core.ImageFace3D;
import com.componenthouse.simplistic3d.core.Face3D;
import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Image;
import java.awt.Color;

public class Wall extends AbstractObject3D {

	private ImageFace3D imageFace3D;

	public Wall(int width, int height) {
		float halfWidth = width / 2;
		float halfHeight = height / 2;
		Vector3D v0 = addVertex(-halfWidth, -halfHeight, 0);
		Vector3D v1 = addVertex(halfWidth, -halfHeight, 0);
		Vector3D v2 = addVertex(halfWidth, halfHeight, 0);
		Vector3D v3 = addVertex(-halfWidth, halfHeight, 0);

		imageFace3D = (ImageFace3D) addFace(null);
		imageFace3D.addVertex(v0).addVertex(v1).addVertex(v2).addVertex(v3);
		imageFace3D.setSize(width, height);
		compile();
	}

	protected Face3D newFace3D(Color color) {
		return new ImageFace3D();
	}

	public boolean isFrontVisible() {
		return imageFace3D.isFrontVisible();
	}

	public void setTexture(Image texture) {
		imageFace3D.setTexture(texture);
	}

	public void setGradient(Color color1, Color color2, ImageFace3D.GradientDirection direction) {
		imageFace3D.setGradient(color1, color2, direction);
	}



	public Vector3D getNormal() {
		return imageFace3D.normal();
	}
}
