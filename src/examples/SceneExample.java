/*
Copyright (c) 2004 Hugo Vidal Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package examples;

import com.componenthouse.simplistic3d.JSimplistic3D;
import com.componenthouse.simplistic3d.controls.OrbitControl;
import com.componenthouse.simplistic3d.controls.OrientedOrbitControl;
import com.componenthouse.simplistic3d.fluent.Fluent3D;
import com.componenthouse.simplistic3d.objects.Object3D;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

public class SceneExample {

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {}

		JFrame frame = new JFrame("Basic Scene 3D - Example");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(createMainPanel(), BorderLayout.CENTER);
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static Component createMainPanel() {
		JSimplistic3D scene1 = createScene1();
		JSimplistic3D scene2 = createScene2();
		JSimplistic3D scene3 = createScene3();
		JSimplistic3D scene4 = createScene4();

		JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
		panel.add(scene1);
		panel.add(scene2);
		panel.add(scene3);
		panel.add(scene4);
		return panel;
	}

	private static JSimplistic3D createScene1() {
		JSimplistic3D simplistic3D = new JSimplistic3D();

		Object3D[] objects = {
			Fluent3D.text3D("X").withColor(Color.BLACK).withFont("Garamond", Font.PLAIN, 20).translate(5, 1, 0).build(),
			Fluent3D.cylinder(.2f, 5f).rotate(0, 0, -90).build(),
			Fluent3D.cone(.6f, 1.5f).withSegments(15).rotate(0, 0, -90).translate(5, 0, 0).build(),

			Fluent3D.text3D("Y").withColor(Color.BLUE).translate(0, 6, 0).build(),
			Fluent3D.cylinder(.2f, 5f).build(),
			Fluent3D.cone(.6f, 1.5f).withSegments(15).translate(0, 5, 0).build(),

			Fluent3D.text3D("Z").translate(0, 0, 6).build(),
			Fluent3D.cylinder(.2f, 5f).rotate(-90, 0, 0).build(),
			Fluent3D.cone(.6f, 1.5f).withSegments(15).rotate(-90, 0, 0).translate(0, 0, 5).build()
		};

		simplistic3D.addObjects3D(objects);
		simplistic3D.setUserControl(new OrbitControl());
		return simplistic3D;
	}

	private static JSimplistic3D createScene2() {
		JSimplistic3D simplistic3D = new JSimplistic3D();
		Object3D sphere = Fluent3D.sphere(1.0f).withHorizontalSegments(20).withVerticalSegments(20).build();
		Object3D cube2 = Fluent3D.cube(1.0f).withTranslucentColor(0x7084C6E9).translate(0, 0, 5).build();
		Object3D cube3 = Fluent3D.cube(1.0f).withTranslucentColor(0x70057000).translate(0, 0, -5).build();
		Object3D cylinder = Fluent3D.cylinder(1.0f, 3.0f).translate(5, 0, 0).build();
		Object3D cone = Fluent3D.cone(1.0f, 3.0f).translate(-5, 0, 0).build();

		simplistic3D.addObject3D(sphere);
		simplistic3D.addObject3D(cube2);
		simplistic3D.addObject3D(cube3);
		simplistic3D.addObject3D(cylinder);
		simplistic3D.addObject3D(cone);
		simplistic3D.setUserControl(new OrientedOrbitControl().initialPosition(-10, -40));
		return simplistic3D;
	}

	private static JSimplistic3D createScene3() {
		JSimplistic3D simplistic3D = new JSimplistic3D();

		Image map = null;
		try {
			URL imageUrl = SceneExample.class.getResource("images/brasil.png");
			map = ImageIO.read(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object3D brazil = Fluent3D.face(20, 20).withTexture(map).rotate(90, 0, 0).build();
		simplistic3D.addObject3D(brazil);

		Object3D cRio = Fluent3D.cylinder(0.5f, 3.0f).withName("Rio de Janeiro").withColor(0xC6E984).translate(5.3f, 0.2f, 4.3f).build();
		Object3D cSaoPaulo = Fluent3D.cylinder(0.5f, 2.0f).withName("S�o Paulo").withColor(0x84C6E9).translate(2.5f, 0.2f, 4.3f).build();
		Object3D cMinas = Fluent3D.cylinder(0.5f, 1.1f).withName("Minas Gerais").withColor(0xC684E9).translate(4.5f, 0.2f, 2.3f).build();
		Object3D cBahia = Fluent3D.cylinder(0.5f, 1.1f).withName("Bahia").withColor(0x5684A9).translate(6.5f, 0.2f, -0.7f).build();
		Object3D cParana = Fluent3D.cylinder(0.5f, 1.1f).withName("Paran�").withColor(0xC10000).translate(1.2f, 0.2f, 5.5f).build();
		Object3D cFlorianopolis = Fluent3D.cylinder(0.5f, 1.1f).withName("Florian�polis").withColor(0x96C100).translate(2f, 0.2f, 6.8f).build();
		Object3D cRioGrandeSul = Fluent3D.cylinder(0.5f, 1.1f).withName("Rio Grande do Sul").withColor(0xE5B766).translate(0.5f, 0.2f, 8f).build();

		simplistic3D.addObject3D(cSaoPaulo);
		simplistic3D.addObject3D(cRio);
		simplistic3D.addObject3D(cMinas);
		simplistic3D.addObject3D(cBahia);
		simplistic3D.addObject3D(cParana);
		simplistic3D.addObject3D(cFlorianopolis);
		simplistic3D.addObject3D(cRioGrandeSul);

		simplistic3D.setNameAsToolTipText(true);
		simplistic3D.setUserControl(new OrientedOrbitControl().verticalRange(-90, 0).horizontalRange(-90, 90).initialPosition(-10, -40));
		return simplistic3D;
	}

	private static JSimplistic3D createScene4() {
		JSimplistic3D simplistic3D = new JSimplistic3D();

		Object3D wall1 = Fluent3D.face(12, 5)
			.withGradientTopDown(new Color(0xC4EBFF), new Color(0x201B5674, true))
			.translate(1f, 2.5f, -5f)
			.build();

		Object3D wall2 = Fluent3D.face(12, 5)
			.withGradientTopDown(new Color(0xC4EBFF), new Color(0x201B5674, true))
			.rotate(0, -90, 0)
			.translate(-5f, 2.5f, 1f)
			.build();

		Object3D wall3 = Fluent3D.face(12, 5)
			.withColor(Color.LIGHT_GRAY)
			.translate(5f, 2.5f, 1f)
			.build();

		Object3D wall4 = Fluent3D.face(5, 5)
			.withTranslucentColor(0x807777AA)
			.rotate(0, -90, 0)
			.translate(0, 2.5f, 4f)
			.build();

		simplistic3D.addObject3D(wall1);
		simplistic3D.addObject3D(wall2);
		simplistic3D.addObject3D(wall3);
		simplistic3D.addObject3D(wall4);

//		Object3D c = Fluent3D.cube(1f).translate(-6f, 2.5f, 1f).build();
//		simplistic3D.addObject3D(c);
//
//		c = Fluent3D.cube(1f).translate(-4f, 2.5f, 1f).build();
//		simplistic3D.addObject3D(c);

		float side = 0.6f;
		float half = side / 2.0f;

		for (int x = -5; x <= 5; x++) {
			for (int z = -5; z <= 5; z++) {
				float height = (float) (Math.random() * 2.0f);
				Object3D c = Fluent3D.cube(side).withName("Value: " + height).translate(x + half + 0.2f, height/2.0f, z + half + 0.2f).scale(1f, height, 1f).build();
				simplistic3D.addObject3D(c);
			}
		}

		simplistic3D.setNameAsToolTipText(true);
		simplistic3D.setUserControl(new OrientedOrbitControl().verticalRange(-90, 0).initialPosition(-10, -40));
		return simplistic3D;
	}

}
