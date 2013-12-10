package GUIClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import MYGUI.MyButton;
import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;

public class ContactList extends RightPanel {
	private JLabel lblNewLabel;
	private NumbersWithTextField panel;
	private JProgressBar progressBar;

	public ContactList() {
		setMyTitle("Contat List");

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblNewLabel.setBounds(256, 113, 223, 45);
		add(lblNewLabel);

		panel = new NumbersWithTextField();
		panel.setBounds(super.getWidth() / 2 - 115, 210, 230, 326);
		panel.getMyButton_Enter().setText("Add");
		panel.getMyButton_Cancel().setText("Clear");
		add(panel);

		progressBar = new JProgressBar();
		progressBar.setBounds(191, 105, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);

		panel.addOuterListener(new InnerListener());

	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == panel.getMyButton_Enter()) {
				class MyWorker extends SwingWorker<String, Object> {

					@Override
					protected String doInBackground() throws Exception {
						progressBar.setVisible(true);
						progressBar.setIndeterminate(true);
						Integer id = Integer.parseInt(panel.getTextView()
								.getTextField().getText());
						Model.Model.getInstance().addFriend(id);

						return "Done";
					}

				}
			}

		}

	}// END InnerListener

}
