package MYGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MetroEditablePin extends JPanel {

	private MetroPassView pass;
	private MyButton del;

	public MetroEditablePin() {
		
		this.setSize(new Dimension(190, 31));
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		pass = new MetroPassView();
		pass.setBounds(8, 5, 144, 20);
		add(pass);
		pass.setColumns(10);

		del = ButtonFactory.getDelButton("resources\\imagesClient\\del_1.png");
		del.setLocation(160, 3);
		add(del);

		addInnerListener();
	}

	private void addInnerListener() {
		del.addActionListener(new InnerListener());

	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == del) {
				// deleting

				String s = pass.getText();
				cleanField(pass);
				if (!s.isEmpty()) {
					s = s.substring(0, s.length() - 1);
					System.out.println("String after deleting: " + s);
					pass.setText(s);
				}
			}// del

		}

		private void cleanField(MetroPassView txtCardNumb) {
			txtCardNumb.setBackground(new Color(255, 255, 255));
		}
	}// END InnerListener

	public MetroPassView getPass() {
		return pass;
	}

	public MyButton getDel() {
		return del;
	}
}
