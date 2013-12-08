package MYGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;

import GUIClient.ConfigGUICLient;

public class ButtonFactory {

	public static final String BACK = "back";
	public static final String FORWARD = "forward";
	public static final String DELETE = "delete";
	public static final String ADD = "add";
	public static final String DONE = "done";

	public static MyButton getNormalButton() {
		MyButton m = new MyButton();
		return m;
	}

	public static MyButton getNormalButton(String s) {
		MyButton m = new MyButton(s);
		return m;
	}

	public static MyButton getIconButton(String pathToImage, String toolTips) {
		MyButton m = new MyButton();
		m.setToolTipText(toolTips);
		m.setSize(new Dimension(100, 100));
		ImageIcon start = new ImageIcon(pathToImage);
		m.setIcon(start);
		return m;
	}

	public static MyButton getNumbButton(String text, char mnem) {
		MyButton m = new MyButton(text);
		m.setSize(new Dimension(42, 42));
		m.setMnemonic(mnem);
		return m;
	}

	public static MyButton getDelButton(String pathToImage) {
		MyButton m = new MyButton();
		m.setSize(new Dimension(25, 25));
		ImageIcon start = new ImageIcon(pathToImage);
		m.setIcon(start);
		return m;

	}

	public static MyButton getNaviButton(String btn, Container goTo,
			int naviSize, int padding) {

		MyButton temp = new MyButton();
		temp.setPreferredSize(new Dimension(naviSize - padding * 2, naviSize
				- padding * 2));
		temp.setMargin(new Insets(padding, padding, padding, padding));

		String fullPath = "imagesForBaseAdmin\\" + btn + ".png";

		ImageIcon start = new ImageIcon(fullPath);
		temp.setIcon(start);

		return temp;
	}

	public static MyButton getSelectBtn(String s) {
		MyButton m = new MyButton(s);
		m.setSize(132, 45);
		return m;
	}
	
	public static MyButton getCheckNavi(String s) {
		MyButton m = new MyButton(s);
		m.setSize(132, 45);
		m.setFont(new Font(ConfigGUICLient.FontType, Font.PLAIN, 19));
		return m;
	}

}
