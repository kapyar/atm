package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GUIClient.MainFormClient;
import Model.Model;

/*
 * 
 * Bind interface and model(calculating requets to BD)
 */
public class Controller {
	// package access
	private MainFormClient mainForm;
	private Model model;

	public Controller(MainFormClient form, Model model) {
		this.mainForm = form;
		this.model = model;

		mainForm.addMainFormListener(new NumbButtonActionListener());
		mainForm.setVisible(true);
	}

	class NumbButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == mainForm.getMyButton_0()) {
				writeToField("0");
			}
			if (source == mainForm.getMyButton_1()) {
				writeToField("1");
			}
			if (source == mainForm.getMyButton_2()) {
				writeToField("2");
			}
			if (source == mainForm.getMyButton_3()) {
				writeToField("3");
			}
			if (source == mainForm.getMyButton_4()) {
				writeToField("4");
			}
			if (source == mainForm.getMyButton_5()) {
				writeToField("5");
			}
			if (source == mainForm.getMyButton_6()) {
				writeToField("6");
			}
			if (source == mainForm.getMyButton_7()) {
				writeToField("7");
			}
			if (source == mainForm.getMyButton_8()) {
				writeToField("8");
			}
			if (source == mainForm.getMyButton_9()) {
				writeToField("9");
			}
			// ENTER CANCEL DEL BUTTONS

			if (source == mainForm.getMyButton_Enter()) {
				if (model.checkLogIn(mainForm.getTxtCardNumb().getText(),
						mainForm.getTxtPin().getText())) {
					//open main menu
					startMenu();

				} else {
					highlightInputFields(mainForm.getTxtCardNumb(),
							mainForm.getTxtPin());
				}
			}
			// CANCEL
			if (source == mainForm.getMyButton_Cancel()) {
				int t = JOptionPane.showConfirmDialog(mainForm,
						"Are your sure?", "Exit", JOptionPane.YES_NO_OPTION);
				if (t == 0) {
					System.exit(0);
				}

			}
			// deleting
			if (source == mainForm.getBtnDelNumbCard()) {
				String s = mainForm.getTxtCardNumb().getText();
				cleanField(mainForm.getTxtCardNumb());
				if (!s.isEmpty()) {
					s = s.substring(0, s.length() - 1);
					mainForm.getTxtCardNumb().setText(s);
				}
			}// delCardNumb

			if (source == mainForm.getBtnDelPin()) {
				String s = mainForm.getTxtPin().getText();
				cleanField(mainForm.getTxtPin());
				if (!s.isEmpty()) {
					s = s.substring(0, s.length() - 1);
					mainForm.getTxtPin().setText(s);
				}
			}// delPin

		}// action Performed

		
		
		private void startMenu() {
			mainForm.getPnlStart().setVisible(false);
			mainForm.setPnlBalance();
			mainForm.setPnlSide();
			mainForm.setButtons();
			
		}



		private void cleanField(JTextField txtCardNumb) {
			txtCardNumb.setBackground(new Color(255, 255, 255));
		}

		private void highlightInputFields(JTextField txtCardNumb,
				JTextField txtPin) {
			txtCardNumb.setBackground(new Color(123, 255, 0));
			txtPin.setBackground(new Color(123, 255, 0));

		}

		// Need to find how to input into right fields
		private void writeToField(String s) {

			if (mainForm.getRdbtnCardNumb().isSelected()) {
				mainForm.getTxtCardNumb().setText(
						mainForm.getTxtCardNumb().getText() + s);
				cleanField(mainForm.getTxtCardNumb());

			} else if (mainForm.getRdbtPass().isSelected()) {
				mainForm.getTxtPin()
						.setText(mainForm.getTxtPin().getText() + s);
				cleanField(mainForm.getTxtPin());
			}
		}

	}// NumbButtonActionListener

}
