package GUIClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import MYGUI.MetroPanel;
import MYGUI.MyButton;
import MYGUI.RightPanel;

public class Balance extends RightPanel {

	private MyButton btnOnDissplay;
	private MyButton btnPrint;
	private JLabel lblNewLabel;
	private JProgressBar progressBar;

	public Balance() {

		setMyTitle("Balance");

		btnOnDissplay = new MyButton("On display");
		btnOnDissplay.setBounds(0, 338, 132, 45);
		add(btnOnDissplay);

		btnPrint = new MyButton("Print");
		btnPrint.setBounds(553, 338, 132, 45);
		add(btnPrint);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblNewLabel.setBounds(154, 113, 432, 70);
		add(lblNewLabel);

		progressBar = new JProgressBar();
		progressBar.setBounds(191, 105, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);

		//addInnerListener();
	}

	public void addOuterListener(ActionListener a){
		btnOnDissplay.addActionListener(a);
		btnPrint.addActionListener(a);
	}

	public MyButton getBtnOnDissplay() {
		return btnOnDissplay;
	}

	public MyButton getBtnPrint() {
		return btnPrint;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
//	private void addInnerListener() {
//		btnOnDissplay.addActionListener(new ActionListenerBalance());
//		btnPrint.addActionListener(new ActionListenerBalance());
//	}

//	private class ActionListenerBalance implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			Object source = e.getSource();
//			System.out.println("In the ActionListenerBalance");
//
//			double balance = 0;
//			if (source == btnOnDissplay) {
//				try {
//					balance = Model.Model.getInstance().doBalance();
//					System.out.println("balance: " + balance);
//				} catch (InterruptedException | ExecutionException
//						| IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				lblNewLabel
//						.setText("Your current balance: " + balance + " UAH");
//			}
//
//			if (source == btnPrint) {
//				try {
//					balance = Model.Model.getInstance().doBalance();
//				} catch (InterruptedException | ExecutionException
//						| IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				DateFormat dateFormat = new SimpleDateFormat(
//						"yyyy/MM/dd HH:mm:ss");
//				Date date = new Date();
//				// System.out.println(dateFormat.format(date));
//				int t = JOptionPane.showConfirmDialog(Balance.this, date
//						+ "\nYour current balance:" + balance + " UAH",
//						"Balance", JOptionPane.PLAIN_MESSAGE,
//						JOptionPane.NO_OPTION);
//
//				lblNewLabel.setText("Please take your receipt");
//			}
//
//		}
//
//	}
}// END ActionListenerBalance
