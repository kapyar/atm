package MYGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import Controller.NumberCheker;

public class MetroEditablePane extends JPanel {

	private MetroTextView textField;
	private MyButton del;

	public MetroEditablePane() {
		this.setSize(new Dimension(210 , 31));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		textField = new MetroTextView();
		textField.getMargin();
		textField.setBounds(8, 5, this.getWidth()-45, 20);
		add(textField);
		textField.setColumns(10);

		del = ButtonFactory.getDelButton("resources\\imagesClient\\del_1.png");
		del.setLocation(this.getWidth()-30, 3);
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

				String s = textField.getText();
				cleanField(textField);
				if (!s.isEmpty()) {
					s = s.substring(0, s.length() - 1);
					System.out.println("String after deleting: " + s);
					textField.setText(s);
				}
			}// del

		}

		private void cleanField(JTextField txtCardNumb) {
			txtCardNumb.setBackground(new Color(255, 255, 255));
		}
	}// END InnerListener

	public MetroTextView getTextField() {
		if (!NumberCheker.isNumber(textField.getText()))
		{
			//textField.setText("");
//			JOptionPane.showConfirmDialog(start,
//					"Wrong input data", "Error",
//					JOptionPane.PLAIN_MESSAGE,
//					JOptionPane.NO_OPTION);
//			//start.clearFields();
		}
		return textField;
	}

	public MyButton getDel() {
		return del;
	}

}
