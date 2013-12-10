package GUIClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;

import MYGUI.CheckView;
import MYGUI.MetroEditablePane;
import MYGUI.MyButton;
import MYGUI.Numbers;
import MYGUI.RightPanel;
import Model.Model;
import Starter.Test;

public class AddMoneyPhone extends RightPanel implements MouseListener {

	private JRadioButton radioButton;
	private MetroEditablePane suma;
	private MetroEditablePane numbOfBill;
	private Numbers numbPane;
	private JRadioButton radioButton_1;
	private JProgressBar progressBar;

	public AddMoneyPhone() {
		setMyTitle("Add Phone Money");

		radioButton = new JRadioButton("");
		radioButton.setBackground(new Color(51, 153, 255));
		radioButton.setBounds(344, 356, 21, 23);
		radioButton.setSelected(true);
		add(radioButton);

		suma = new MetroEditablePane();
		suma.getTextField().addMouseListener(this);
		suma.setLocation(367, 352);
		add(suma);

		numbOfBill = new MetroEditablePane();
		numbOfBill.getTextField().addMouseListener(this);
		numbOfBill.setBounds(367, 295, 210, 31);
		add(numbOfBill);

		numbPane = new Numbers();
		numbPane.setLocation(142, 284);
		add(numbPane);

		radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(SystemColor.textHighlight);
		radioButton_1.setBounds(344, 299, 21, 23);
		add(radioButton_1);

		ButtonGroup b = new ButtonGroup();
		b.add(radioButton);
		b.add(radioButton_1);

		JLabel lblNewLabel = new JLabel("Phone Number");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(367, 280, 88, 14);
		add(lblNewLabel);

		JLabel lblHowMuch = new JLabel("How Much\r\n");
		lblHowMuch.setForeground(Color.WHITE);
		lblHowMuch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblHowMuch.setBounds(367, 337, 69, 14);
		add(lblHowMuch);

		progressBar = new JProgressBar();
		progressBar.setBounds(191, 105, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);

		addInnerListener();

	}

	private void addInnerListener() {
		for (MyButton a : numbPane.getListOfComponents()) {
			a.addActionListener(new InnerListener());
		}
	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == numbPane.getMyButton_0()) {
				writeIt("0");
			}
			if (source == numbPane.getMyButton_1()) {
				writeIt("1");
			}
			if (source == numbPane.getMyButton_2()) {
				writeIt("2");
			}
			if (source == numbPane.getMyButton_3()) {
				writeIt("3");
			}
			if (source == numbPane.getMyButton_4()) {
				writeIt("4");
			}
			if (source == numbPane.getMyButton_5()) {
				writeIt("5");
			}
			if (source == numbPane.getMyButton_6()) {
				writeIt("6");
			}
			if (source == numbPane.getMyButton_7()) {
				writeIt("7");
			}
			if (source == numbPane.getMyButton_8()) {
				writeIt("8");
			}
			if (source == numbPane.getMyButton_9()) {
				writeIt("9");
			}

			if (source == numbPane.getMyButton_Enter()) {
				class MyWorker extends SwingWorker<String, Object> {

					double bln = 0;
					double res = 0;
					CheckView checkView;

					@Override
					protected String doInBackground() throws Exception {
						Test.getController().getWrap().setDisablePnlSide();
						progressBar.setVisible(true);
						progressBar.setIndeterminate(true);

						Integer d = Integer.parseInt(suma.getTextField()
								.getText());

						Model.getInstance().doWithdrawal(d);

						bln = Model.getInstance().doBalance();
						checkView = new CheckView((int) bln);

						return "Done";
					}

					protected void done() {
						progressBar.setVisible(false);
						if (res == -1) {
						       JOptionPane.showConfirmDialog(AddMoneyPhone.this,
						         "Not enough money", "Error",
						         JOptionPane.PLAIN_MESSAGE,
						         JOptionPane.NO_OPTION);
						       clearField();
						   }
						Test.getController().getWrap()
								.resetRightPanel(checkView);
					}

				}
				new MyWorker().execute();

			}
			if (source == numbPane.getMyButton_Cancel()) {
				suma.getTextField().setText("");
				numbOfBill.getTextField().setText("");
			}

		}

		private void writeIt(String s) {
			if (radioButton.isSelected()) {
				suma.getTextField().setText(suma.getTextField().getText() + s);
			} else if (radioButton_1.isSelected()) {
				numbOfBill.getTextField().setText(
						numbOfBill.getTextField().getText() + s);
			}
		}

	}// END InnerListener

	
	private void clearField() {
		   suma.getTextField().setText("");
		   numbOfBill.getTextField().setText("");

		  }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == suma.getTextField()) {
			// System.out.println("txt");
			radioButton.setSelected(true);
		}
		if (source == numbOfBill.getTextField()) {
			// System.out.println("pin");
			radioButton_1.setSelected(true);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
