/* 대전광역시 미세먼지 농도 출력 */
package dust;

import java.awt.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Vector;

import javax.swing.*;

public class main extends JFrame {
	private Polygon polygon[] = new Polygon[5];
	private ValueDTO value = new ValueDTO();
	private APIConnection api = new APIConnection();
	private Parser parser = new Parser();
	private String json = null;
	
	public main() {
		setTitle("대전 미세먼지");
		setSize(370, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		mapPanel mapPanel = new mapPanel();
		mapPanel.setBounds(1, 1, 370, 450);
		add(mapPanel);
		
		setVisible(true);
	}
	class mapPanel extends JPanel {
		public mapPanel() {
			setBackground(Color.WHITE);
			
			for(int i =0; i < polygon.length; i++) {
				polygon[i] = new Polygon();
			}
			
			int j_x[] = {162, 208, 229, 213, 226, 219, 243, 200, 157 };
			int j_y[] = {302, 378, 341, 320, 303, 281, 271, 214, 283 };
			int s_x[] = {76, 67, 83, 100, 124, 148, 147, 159, 154, 198, 170, 138, 144, 130, 102 };
			int s_y[] = {312, 329, 366, 359, 403, 367, 311, 302, 283, 212, 178, 205, 227, 229, 297 };
			int y_x[] = {76, 100, 128, 141, 134, 170, 199, 209, 245, 251, 232, 289, 285, 271, 268, 253, 246, 189, 190, 168, 149, 132, 76, 40 };
			int y_y[] = {309, 294, 227, 225, 205, 174, 208, 195, 202, 190, 160, 83, 78, 82, 55, 60, 80, 79, 45, 26, 41, 104, 126, 278 };
			int d_x[] = {210, 229, 280, 272, 285, 308, 261, 254, 247, 210, 202, 246, 245, 222, 229, 217, 232};
			int d_y[] = {379, 394, 341, 326, 218, 200, 202, 193, 207, 199, 211, 270, 274, 282, 304, 321, 339 };
			int dd_x[] = {261, 309, 316, 338, 339, 313, 310, 293, 276, 235 };
			int dd_y[] = {198, 197, 154, 146, 144, 119, 119, 136, 105, 160 };
			
			polygon[0].xpoints = dd_x;
			polygon[0].ypoints = dd_y;
			polygon[0].npoints = 10;
			polygon[1].xpoints = d_x;
			polygon[1].ypoints = d_y;
			polygon[1].npoints = 17;
			polygon[2].xpoints = s_x;
			polygon[2].ypoints = s_y;
			polygon[2].npoints = 15;
			polygon[3].xpoints = y_x;
			polygon[3].ypoints = y_y;
			polygon[3].npoints = 24;
			polygon[4].xpoints = j_x;
			polygon[4].ypoints = j_y;
			polygon[4].npoints = 9;
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int pm10Value;
			
			try {
				json = api.getJson();
				parser.Parsing(json, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i =0; i < value.getSize(); i++) {
				pm10Value = Integer.parseInt(value.getPm10Value(i));
				if(pm10Value < 30) {
					g.setColor(Color.BLUE);
				}
				else if(pm10Value > 30 && pm10Value < 80) {
					g.setColor(Color.GREEN);
				}
				else if(pm10Value > 80 && pm10Value < 150) {
					g.setColor(Color.ORANGE);
				}
				else {
					g.setColor(Color.RED);
				}
				g.fillPolygon(polygon[i]);
			}
			g.setColor(Color.BLACK);
			g.drawString("대덕", 272, 140);
			g.drawString(value.getPm10Value(0), 275, 155);
			g.drawString("동구", 245, 220);
			g.drawString(value.getPm10Value(1), 249, 237);
			g.drawString("서구", 105, 305);
			g.drawString(value.getPm10Value(2), 110, 323);
			g.drawString("유성", 148, 107);
			g.drawString(value.getPm10Value(3), 151, 125);
			g.drawString("중구", 180, 260);
			g.drawString(value.getPm10Value(4), 185, 278);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("file.encoding", "UTF-8");
		Field charset;
		try {
			charset = Charset.class.getDeclaredField("defaultCharset");
			charset.setAccessible(true);
			charset.set(null, null);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new main();
	}

}
