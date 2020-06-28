package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.core.Face3D;
import com.componenthouse.simplistic3d.math.Matrix3D;
import com.componenthouse.simplistic3d.math.Vector3D;

public class Tube extends AbstractObject3D {

	private float radiusTop;
	private float radiusBottom;
	private float height;
	private int segments = 8;

	public Tube(float radiusTop, float radiusBottom, float height) {
		this.radiusTop = radiusTop;
		this.radiusBottom = radiusBottom;
		this.height = height;
	}

	public int getSegments() { return segments; }
	public void setSegments(int segments) { this.segments = segments; }

	public void build() {
		buildObject();
		compile();
	}

	private void buildObject() {
		if (segments < 3)
			throw new IllegalArgumentException("Number of segments cannot be less than 3.");

		Vector3D top = new Vector3D(radiusTop, height, 0);
		Vector3D bottom = new Vector3D(radiusBottom, 0, 0);

		Vector3D[] vTop = new Vector3D[segments];
		Vector3D[] vBottom = new Vector3D[segments];

		vTop[0] = addVertex(top);
		vBottom[0] = addVertex(bottom);

		Face3D topFace = addFace(color)
				.addVertex(vTop[0]);

		Face3D bottomFace = addFace(color)
				.addVertex(vBottom[0]);

		for (int i = 1; i < segments; i++) {
			Matrix3D rotator = new Matrix3D().rotateY(360.0f / segments * i);

			Vector3D rotatedTop = rotator.transform(top);
			Vector3D rotatedBottom = rotator.transform(bottom);

			vTop[i] = addVertex(rotatedTop);
			vTop[i] = vTop[i] == null? vTop[i-1] : vTop[i];

			vBottom[i] = addVertex(rotatedBottom);
			vBottom[i] = vBottom[i] == null? vBottom[i-1] : vBottom[i];

			addFace(color)
				.addVertex(vBottom[i-1])
				.addVertex(vTop[i-1])
				.addVertex(vTop[i])
				.addVertex(vBottom[i]);

			topFace.addVertex(vTop[i]);
			bottomFace.addVertex(vBottom[i]);
		}

		// Last face of the cycle
		addFace(color)
			.addVertex(vBottom[segments-1])
			.addVertex(vTop[segments-1])
			.addVertex(vTop[0])
			.addVertex(vBottom[0]);
	}
}
