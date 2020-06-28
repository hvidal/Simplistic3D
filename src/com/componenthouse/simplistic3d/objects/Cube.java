package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.core.Face3D;
import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Color;

public class Cube extends AbstractObject3D {

	public Cube(float side) {
		float half = side / 2;
		Vector3D v0 = addVertex(-half, -half, -half);
		Vector3D v1 = addVertex(half, -half, -half);
		Vector3D v2 = addVertex(half, half, -half);
		Vector3D v3 = addVertex(-half, half, -half);
		Vector3D v4 = addVertex(-half, -half, half);
		Vector3D v5 = addVertex(half, -half, half);
		Vector3D v6 = addVertex(half, half, half);
		Vector3D v7 = addVertex(-half, half, half);
		
		Face3D f0 = addFace(Color.GRAY);
		f0.addVertex(v0);
		f0.addVertex(v1);
		f0.addVertex(v2);
		f0.addVertex(v3);

		Face3D f1 = addFace(Color.GRAY);
		f1.addVertex(v4);
		f1.addVertex(v5);
		f1.addVertex(v6);
		f1.addVertex(v7);

		Face3D f2 = addFace(Color.GRAY);
		f2.addVertex(v0);
		f2.addVertex(v1);
		f2.addVertex(v5);
		f2.addVertex(v4);

		Face3D f3 = addFace(Color.GRAY);
		f3.addVertex(v2);
		f3.addVertex(v3);
		f3.addVertex(v7);
		f3.addVertex(v6);

		Face3D f4 = addFace(Color.GRAY);
		f4.addVertex(v0);
		f4.addVertex(v3);
		f4.addVertex(v7);
		f4.addVertex(v4);
		
		Face3D f5 = addFace(Color.GRAY);
		f5.addVertex(v1);
		f5.addVertex(v2);
		f5.addVertex(v6);
		f5.addVertex(v5);
		compile();
	}
}

