package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.math.Matrix3D;
import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Color;

public class Sphere extends AbstractObject3D {

	private float radius;
	private int horizontalSegments = 10;
	private int verticalSegments = 10;

	public Sphere(float radius) {
		this.radius = radius;
	}

	public int getHorizontalSegments() { return horizontalSegments; }
	public void setHorizontalSegments(int horizontalSegments) { this.horizontalSegments = horizontalSegments; }

	public int getVerticalSegments() { return verticalSegments; }
	public void setVerticalSegments(int verticalSegments) { this.verticalSegments = verticalSegments; }

	public void build() {
		buildObject();
		compile();
	}

	private void buildObject() {
		if (horizontalSegments < 3)
			throw new IllegalArgumentException("Number of horizontal segments cannot be less than 3.");

		if (verticalSegments < 3)
			throw new IllegalArgumentException("Number of vertical segments cannot be less than 3.");

		Vector3D vertical = addVertex(0, radius, 0);
		Vector3D[] prevHorizonal = new Vector3D[horizontalSegments];
		for (int i = 0; i < prevHorizonal.length; i++) {
			prevHorizonal[i] = vertical;
		}

		float verticalStep = 180.0f / verticalSegments;
		float horizontalStep = 360.0f / horizontalSegments;

		for (int v = 0; v < verticalSegments; v++) {
			Vector3D nextVertical = addVertex(new Matrix3D().rotateZ(verticalStep).transform(vertical));
			for (int h = 0; h < horizontalSegments; h++) {
				Vector3D nextHorizontal = addVertex(new Matrix3D().rotateY(horizontalStep).transform(nextVertical));
				addFace(Color.GRAY)
					.addVertex(prevHorizonal[h])
					.addVertex(nextVertical)
					.addVertex(nextHorizontal)
					.addVertex(prevHorizonal[(h+1)%horizontalSegments]);

				if (h > 0)
					prevHorizonal[h] = nextVertical;
				nextVertical = nextHorizontal;
			}
			prevHorizonal[0] = nextVertical;
			vertical = nextVertical;
		}
	}
}
