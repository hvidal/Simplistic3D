package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.core.Face3D;
import com.componenthouse.simplistic3d.core.Position;
import com.componenthouse.simplistic3d.math.Matrix3D;
import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObject3D implements Object3D {
	
	protected Position position = new Position();
	protected List<Face3D> faces = new ArrayList<Face3D>();
	protected String name;
	protected Color color = Color.GRAY;

	protected Vector3D center = new Vector3D();
	protected Vector3D originalCenter = new Vector3D();

	protected List<Vector3D> vertices = new ArrayList<Vector3D>();
	protected List<Vector3D> normals = new ArrayList<Vector3D>();
	
	protected List<Vector3D> originalVertices = new ArrayList<Vector3D>();
	protected List<Vector3D> originalNormals = new ArrayList<Vector3D>();

	public float avgZ() {
		float avg = faces.get(0).avgZ();
		for (int i = 1; i < faces.size(); i++) {
			avg += faces.get(i).avgZ();
		}
		return avg / (float) faces.size();
	}

	public List<Vector3D> getVertices() { return vertices; }

	public String getName() { return name; }
	public void setName(String name) {
		this.name = name;
	}

	public void setColor(int rgba) {
		setColor(new Color(rgba));
	}

	public void setColor(Color color) {
		this.color = color;
		for (Face3D face3D : faces) {
			face3D.setColor(color);
		}
	}	

	public void rotate(float rotateX, float rotateY, float rotateZ) {
		position.incRotation(rotateX, rotateY, rotateZ);
	}

	public void translate(float translateX, float translateY, float translateZ) {
		position.incTranslation(translateX, translateY, translateZ);
	}

	public void scale(float sx, float sy, float sz) {
		position.incScale(sx, sy, sz);
		compile();
	}

	protected Vector3D addVertex(Vector3D v) {
		if (!vertices.contains(v)) {
			Vector3D vertex = v.getClone();
			vertices.add(vertex);
			originalVertices.add(vertex.getClone());
			return vertex;
		}
		return null;
	}

	protected Vector3D addVertex(float x, float y, float z) {
		return addVertex(new Vector3D(x, y, z));
	}

	public Face3D addFace(Color color) {
		Face3D face3D = newFace3D(color);
		faces.add(face3D);
		return face3D;
	}

	protected Face3D newFace3D(Color color) {
		return new Face3D(color);
	}

	private Matrix3D camera(Position viewerPosition) {
		return new Matrix3D()
				.multiply(position.rotation)
				.multiply(position.scale)
				.multiply(position.translation)
				.multiply(viewerPosition.rotation)
				.multiply(viewerPosition.scale)
				.multiply(viewerPosition.translation);
	}

	private void computeNormals(Position viewerPosition) {
		new Matrix3D()
			.multiply(position.rotation)
			.multiply(viewerPosition.rotation)
			.transform(originalNormals, normals);
	}

	public void transformToCameraSpace(Position viewerPosition) {
		Matrix3D m = camera(viewerPosition);
		m.transform(originalCenter, center);
		m.transform(originalVertices, vertices);
		computeNormals(viewerPosition);
	}

	public void transformToScreenSpace(Position viewerPosition, Matrix3D matrix, int width, int height, float zTarget) {
		// Recalc vertices
		Matrix3D m = camera(viewerPosition);
		m.multiply(matrix);
		m.transform(originalCenter, center);
		m.transform(originalVertices, vertices);

		for (Face3D face3D : faces) {
			face3D.computeCenter();
		}

		computeNormals(viewerPosition);

		float halfWidth = width / 2;
		float halfHeight = height / 2;
		for (Vector3D vertex : vertices) {
			if (vertex.z() < 0) {
				float p = zTarget / vertex.z();
				vertex.x(halfWidth + vertex.x() * p);
				vertex.y(halfHeight - vertex.y() * p);
			}
		}
	}

	public void paint(Graphics g, float ambientLight) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Face3D face3D : faces) {
			if (face3D.visible())
				face3D.paint(g, ambientLight);
		}
	}

	public Face3D getFaceAt(int x, int y) {
		for (Face3D face3D : faces) {
			if (face3D.visible() && face3D.getFaceAt(x, y))
				return face3D;
		}
		return null;
	}

	public Vector3D getCenter() {
		return center;
	}

	public void computeCenter() {
		originalCenter.reset();
		for (Vector3D original : originalVertices)
			originalCenter.add(original);
		originalCenter.divide(originalVertices.size());
	}

	protected void compile() {
		originalNormals.clear();
		normals.clear();

		computeCenter();

		Matrix3D m = new Matrix3D();
		m.multiply(position.scale);
		for (Vector3D vertex : vertices) {
			m.transform(vertex, vertex);
			m.transform(originalCenter, center);
		}

		for (Face3D face3D : faces) {
			face3D.computeNormal();			
			Vector3D v = originalCenter.getClone();
			v.subtract(face3D.getVertexAt(0));
			if (v.dot(face3D.normal()) > 0)
				face3D.normal().multiply(-1.0f);

			originalNormals.add(face3D.normal());
			face3D.normal(new Vector3D());
			normals.add(face3D.normal());
		}
	}

}
