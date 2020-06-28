/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.componenthouse.simplistic3d.controls;

import com.componenthouse.simplistic3d.JSimplistic3D;
import com.componenthouse.simplistic3d.math.Matrix3D;

import javax.swing.Timer;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrientedOrbitControl implements UserControl{

	private Point previous;
	private Matrix3D originalRot;
	private float currentHorizontal = 0f;
	private float currentVertical = 0f;
	private int minHorizontal = -180;
	private int maxHorizontal = 180;
	private int minVertical = -180;
	private int maxVertical = 180;
	private Timer timer;

	public OrientedOrbitControl horizontalRange(int minHorizontal, int maxHorizontal) {
		this.minHorizontal = minHorizontal;
		this.maxHorizontal = maxHorizontal;
		return this;
	}

	public OrientedOrbitControl verticalRange(int minVertical, int maxVertical) {
		this.minVertical = minVertical;
		this.maxVertical = maxVertical;
		return this;
	}

	public OrientedOrbitControl initialPosition(int horizontal, int vertical) {
		currentHorizontal = horizontal;
		currentVertical = vertical;
		return this;
	}

	private void paint(Point point, JSimplistic3D jSimplistic3D) {
		if (originalRot == null)
			originalRot = new Matrix3D(jSimplistic3D.getViewerPosition().rotation);

		if (previous != null) {
			currentHorizontal += point.x - previous.x;
			currentVertical += previous.y - point.y;

			currentHorizontal = currentHorizontal > maxHorizontal? maxHorizontal : currentHorizontal < minHorizontal? minHorizontal : currentHorizontal;
			currentVertical = currentVertical > maxVertical? maxVertical : currentVertical < minVertical? minVertical : currentVertical;

			jSimplistic3D.getViewerPosition().rotation.copy(originalRot);
			jSimplistic3D.getViewerPosition().incRotation(0, currentHorizontal, 0);
			jSimplistic3D.computeMatrix(true);
			jSimplistic3D.getViewerPosition().incRotation(currentVertical, 0, 0);
			jSimplistic3D.repaint();
		}
		previous = point;
	}

	public void installUserControl(final JSimplistic3D jSimplistic3D) {
		installListeners(jSimplistic3D);
		runAnimation(jSimplistic3D);
	}

	private void installListeners(final JSimplistic3D jSimplistic3D) {
		jSimplistic3D.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if (timer == null)
					paint(e.getPoint(), jSimplistic3D);
			}

			public void mouseMoved(MouseEvent e) {}
		});

		jSimplistic3D.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) { previous = null; }
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}

	private void runAnimation(final JSimplistic3D jSimplistic3D) {
		if (currentHorizontal != 0 || currentVertical != 0) {
			final float nSteps = 30f;
			final float horizontal = currentHorizontal;
			final float incrementH = horizontal / nSteps;
			currentHorizontal = 0;

			final float vertical = currentVertical;
			final float incrementV = vertical / nSteps;
			currentVertical = 0;
			timer = new Timer(20, new ActionListener() {
				private int step = 0;
				public void actionPerformed(ActionEvent e) {
					currentHorizontal += incrementH;
					currentVertical += incrementV;
					paint(new Point(), jSimplistic3D);
					step++;
					if (step == nSteps-1) {
						timer.stop();
						previous = null;
						timer = null;
					}
				}
			});
			jSimplistic3D.setStartTimer(timer);
		}
	}

}
