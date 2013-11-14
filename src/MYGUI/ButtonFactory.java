package MYGUI;

import java.awt.Dimension;

import javax.swing.ImageIcon;

public class ButtonFactory {
	
	public static MyButton getNormalButton(){
		MyButton m = new MyButton();
		return m;
	}
	
	public static MyButton getNormalButton(String s){
		MyButton m = new MyButton(s);
		return m;
	}
	
	public static MyButton getIconButton(String pathToImage, String toolTips){
		MyButton m = new MyButton();
		m.setToolTipText(toolTips);
		m.setSize(new Dimension(100, 100));
		ImageIcon start = new ImageIcon(pathToImage);
		m.setIcon(start);
		return m;
	}
	
	public static MyButton getNumbButton(String text,char mnem){
		MyButton m = new MyButton(text);
		m.setSize(new Dimension(42,42));
		m.setMnemonic(mnem);
		return m;
	}
	
}
