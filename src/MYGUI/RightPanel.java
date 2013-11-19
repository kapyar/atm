package MYGUI;

import java.awt.Font;

import javax.swing.JLabel;

public class RightPanel extends MetroPanel {
	private JLabel lblBalance;

	public RightPanel() {
		super();
		lblBalance = new JLabel();
		lblBalance.setFont(new Font("Viner Hand ITC", Font.PLAIN, 27));
		lblBalance.setBounds(305, 62, 300, 36);
		add(lblBalance);
		setBounds(115, 0, 685, 600);
	}
	
	public void setMyTitle(String s){
		lblBalance.setText(s);
	}

}
