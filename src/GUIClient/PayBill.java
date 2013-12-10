package GUIClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;

import MYGUI.MetroEditablePane;
import MYGUI.MyButton;
import MYGUI.Numbers;
import MYGUI.RightPanel;

public class PayBill extends RightPanel {

	private JRadioButton radioButton;
	private MetroEditablePane sum;
	private MetroEditablePane numOfBill;
	private Numbers numbPane;
	private JRadioButton radioButton_1;
	private JProgressBar progressBar;

	public PayBill() {
		setMyTitle("Pay Bill");
		radioButton = new JRadioButton("");
		radioButton.setBackground(new Color(51, 153, 255));
		radioButton.setBounds(344, 356, 21, 23);
		radioButton.setSelected(true);
		add(radioButton);

		sum = new MetroEditablePane();
		sum.setLocation(367, 352);
		add(sum);

		numOfBill = new MetroEditablePane();
		numOfBill.setBounds(367, 295, 210, 31);
		add(numOfBill);

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

		JLabel lblNewLabel = new JLabel("Number of bill");
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

					@Override
					protected String doInBackground() throws Exception {
						progressBar.setVisible(true);
						progressBar.setIndeterminate(true);
						Integer suma = Integer.parseInt(sum.getTextField()
								.getText());
						// use
						try {
							double res = Model.Model.getInstance()
									.doWithdrawal(suma);

							if (res != -1) {
								System.out.println("PayBill: successfully");
							}
						} catch (IOException | InterruptedException
								| ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return "Done";
					}

					protected void done() {
						progressBar.setVisible(false);
					}
				}
				new MyWorker().execute();
			}
			if (source == numbPane.getMyButton_Cancel()) {
				numOfBill.getTextField().setText("");
				sum.getTextField().setText("");
			}

		}

		private void writeIt(String s) {
			if (radioButton.isSelected()) {
				sum.getTextField().setText(sum.getTextField().getText() + s);
			} else if (radioButton_1.isSelected()) {
				numOfBill.getTextField().setText(
						numOfBill.getTextField().getText() + s);
			}
		}

	}// END InnerListener

}
