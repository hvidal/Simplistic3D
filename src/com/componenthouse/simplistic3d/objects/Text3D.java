package com.componenthouse.simplistic3d.objects;

import com.componenthouse.simplistic3d.contrib.PerspectiveFilter;
import com.componenthouse.simplistic3d.contrib.TransformFilter;
import com.componenthouse.simplistic3d.core.Face3D;
import com.componenthouse.simplistic3d.math.Vector3D;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Text3D extends AbstractObject3D {

	private static BufferedImage DUMMY_IMAGE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

	private static PerspectiveFilter FILTER = new PerspectiveFilter();
	static {
		FILTER.setInterpolation(TransformFilter.BILINEAR);
	}

	private BufferedImage image;
	private String text;
	private Font font = new Font("Arial", Font.BOLD, 16);
	private Color color = Color.BLACK;

	public Text3D(String text) {
		super();
		setText(text);
		int w = (int) Math.round(image.getWidth()/10.0);
		int h = (int) Math.round(image.getHeight()/10.0);
		Vector3D v0 = addVertex(0, 0, 0);
		Vector3D v1 = addVertex(w, 0, 0);
		Vector3D v2 = addVertex(w, h, 0);
		Vector3D v3 = addVertex(0, h, 0);
		Face3D f0 = addFace(Color.BLACK);
		f0.addVertex(v0);
		f0.addVertex(v1);
		f0.addVertex(v2);
		f0.addVertex(v3);
		compile();
	}

	public Font getFont() { return font; }
	public void setFont(Font font) {
		this.font = font;
		updateImage();
	}

	public String getText() { return text; }
	public void setText(String text) {
		this.text = text;
		updateImage();
	}

	public Color getColor() { return color; }
	public void setColor(int rgba) {
		setColor(new Color(rgba, true));
	}
	public void setColor(Color color) {
		this.color = color;
		updateImage();
	}

	private synchronized void updateImage() {
		Graphics2D g2 = DUMMY_IMAGE.createGraphics();
		g2.setFont(font);
		FontMetrics metrics = g2.getFontMetrics();
		Rectangle2D stringBounds = metrics.getStringBounds(text, g2);

		image = new BufferedImage(Math.round((float) stringBounds.getWidth()), Math.round((float) stringBounds.getHeight()), BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(color);
		g2.setFont(font);
		g2.drawString(text, 0, (float) stringBounds.getHeight() - metrics.getDescent());
		g2.dispose();
	}

	protected Face3D newFace3D(Color color) {
		return new FaceFont3D(color);
	}

	class FaceFont3D extends Face3D {

		public FaceFont3D(Color color) {
			super(color);
		}

		public boolean visible() {
			return true;
		}

		protected void paintFace(Graphics2D g, int xvert[], int yvert[], Color color) {
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

			FILTER.setCorners(
					xvert[3], yvert[3],
					xvert[2], yvert[2],
					xvert[1], yvert[1],
					xvert[0], yvert[0]);
			try {
				g.drawImage(image, FILTER, smallerX, smallerY);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
