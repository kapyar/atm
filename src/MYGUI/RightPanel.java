package MYGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import GUIClient.ConfigGUICLient;

public class RightPanel extends MetroPanel {
	private JLabel lblBalance;
	// RightPanel sizes and coordinates
		private int _W;
		private int _H;
		private int _x;
		private int _y;
		/** delta between General Panel (MetroPanel) and RightPanel*/
		private int deltaBMPRP = 115; 
		
	public RightPanel() {
		_W = super.getWidth()-deltaBMPRP;
		_H = super.getHeight();
		_x = super.getX()+deltaBMPRP;
		_y = super.getY();
		
		lblBalance = new JLabel("",JLabel.CENTER);
		lblBalance.setSize(500, 50);
		lblBalance.setFont(new Font(ConfigGUICLient.FontType, Font.PLAIN, 27));
		lblBalance.setForeground(new Color(255, 255, 255));
		lblBalance.setBounds(((_W)/2)-ConfigGUICLient._WTL/2,ConfigGUICLient._yTL, ConfigGUICLient._WTL, ConfigGUICLient._HTL);
		lblBalance.setAlignmentX(CENTER_ALIGNMENT);
		add(lblBalance);
		setBounds(_x, _y, _W, _H);
		
	}
	
	public void setMyTitle(String s){
		lblBalance.setText(s);
	}

}
