/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d;

import com.componenthouse.simplistic3d.controls.UserControl;
import com.componenthouse.simplistic3d.core.BSPTree;
import com.componenthouse.simplistic3d.core.Position;
import com.componenthouse.simplistic3d.core.SceneBox;
import com.componenthouse.simplistic3d.math.Matrix3D;
import com.componenthouse.simplistic3d.math.Vector3D;
import com.componenthouse.simplistic3d.objects.Object3D;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Create Component <> Model separation
 * TODO: Clean up code
 * TODO: Zoom & Translate controls
 * TODO: Create more realistic animation (initial position)
 * TODO: Static labels
 * TODO: Fix intersecting objects (water level)
 * TODO: Add wireframe parameter
 * TODO: Search tooltip using BSP tree
 * TODO: Fix color of wall (one side doesn't get correctly painted)
 */

public class JSimplistic3D extends JPanel {

	private boolean compiled = false;
	private Position viewerPosition = new Position();

	private List<Object3D> objects = new ArrayList<Object3D>();

	public Matrix3D matrix;
	private float ambientLight;
	static final float defaultAmbient = 0.0f;
	private float minScale;
	static final float defaultMinScale = 0.7f;
	private float maxScale;
	static final float defaultMaxScale = 1.0f;
	private SceneBox sceneBox;
	public float zCamera, zTarget;
	private Timer startTimer;

	private BSPTree bspTree = null;
	private List<Object3D> paintOrder = new ArrayList<Object3D>();

	public JSimplistic3D() {
		ambientLight = defaultAmbient;
		minScale = defaultMinScale;
		maxScale = defaultMaxScale;
		if (ambientLight < 0 || ambientLight > 1)
			ambientLight = defaultAmbient;
		if (minScale > maxScale) {
			minScale = defaultMinScale;
			maxScale = defaultMaxScale;
		}
	}

	public float getAmbientLight() { return ambientLight; }
	public void setAmbientLight(float ambientLight) { this.ambientLight = ambientLight; }

	public Position getViewerPosition() { return viewerPosition; }
	public void setViewerPosition(Position viewerPosition) { this.viewerPosition = viewerPosition; }

	public void setStartTimer(Timer timer) { this.startTimer = timer; }

	public void addObject3D(Object3D object3D) {
		objects.add(object3D);
		compiled = false;
	}

	public void addObjects3D(Object3D[] objects) {
		for (Object3D object : objects) {
			addObject3D(object);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (objects.isEmpty())
			return;

		if (startTimer != null) {
			startTimer.start();
			startTimer = null;
		}

		computeMatrix(false);

		// Move to view point
		for (Object3D object3D : objects) {
			object3D.transformToScreenSpace(viewerPosition, matrix, getWidth(), getHeight(), zTarget);
		}
		// Paint objects
		buildBSPTree();
		paintOrder.clear();
		bspTree.setPaintOrder(paintOrder);
		for (Object3D object3D : paintOrder) {
			object3D.paint(g, ambientLight);
		}
	}

	private void buildBSPTree() {
		if (bspTree == null) {
			bspTree = new BSPTree(objects.get(0));
			for (int i = 1; i < objects.size(); i++) {
				bspTree.addToTree(objects.get(i));
			}
		}
	}

	public synchronized Object3D getObjectAt(int x, int y) {
		// Search in reverse order
		for (int i = objects.size()-1; i >= 0; i--) {
			Object3D object3D =  objects.get(i);
			if (object3D.getFaceAt(x, y) != null)
				return object3D;
		}
		return null;
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		compiled = false;
	}

	public synchronized void computeMatrix(boolean force) {
		if (!compiled || force) {
			if (sceneBox == null)
				computeSceneBox();
			Vector3D c = sceneBox.getCenter();
			float f1 = getWidth() / sceneBox.getWidth();
			float f2 = getHeight() / sceneBox.getHeight();
			float f = 0.7f * Math.min(f1, f2);
			matrix = new Matrix3D();
			matrix.translate(-c.x(), -c.y(), -c.z());
			matrix.scale(f, f, f);
			float dz = f * sceneBox.getDepth();
			float h1 = dz * minScale / (maxScale - minScale);
			zCamera = h1 + dz / 2;
			zTarget = -h1 * maxScale;
			matrix.translate(0, 0, -zCamera);
			bspTree = null;
			compiled = true;
		}
	}

	private void computeSceneBox() {
		sceneBox = null;
		for (Object3D object3D : objects) {
			object3D.transformToCameraSpace(viewerPosition);
			if (sceneBox == null)
				sceneBox = new SceneBox(object3D.getVertices());
			else
				sceneBox.combine(new SceneBox(object3D.getVertices()));
		}
	}

	public void setUserControl(UserControl userControl) {
		userControl.installUserControl(this);
	}

	public void setNameAsToolTipText(boolean showNameAsToolTipText) {
		if (showNameAsToolTipText)
			addMouseMotionListener(tooltipListener);
		else
			removeMouseMotionListener(tooltipListener);
	}

	private MouseMotionListener tooltipListener = new MouseMotionListener() {
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			for (int i = objects.size()-1; i >= 0; i--) {
				Object3D object3D =  objects.get(i);
				if (object3D.getName() != null && object3D.getFaceAt(e.getX(), e.getY()) != null) {
					setToolTipText(object3D.getName());
					return;
				}
			}
			setToolTipText(null);
		}
	};

}

