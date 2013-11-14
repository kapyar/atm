package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import GUIClient.MainFormClient;
import Model.Model;

public class ActionListenerStart implements ActionListener {

	private Model m;
	private MainFormClient v;

	public ActionListenerStart(Controller c) {
		System.out.println("Constructor ActionListenerStart");
		m = c.getModel();
		v = c.getView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == v.getMyButton_0()) {
			writeToField("0");
		}
		if (source == v.getMyButton_1()) {
			writeToField("1");
		}
		if (source == v.getMyButton_2()) {
			writeToField("2");
		}
		if (source == v.getMyButton_3()) {
			writeToField("3");
		}
		if (source == v.getMyButton_4()) {
			writeToField("4");
		}
		if (source == v.getMyButton_5()) {
			writeToField("5");
		}
		if (source == v.getMyButton_6()) {
			writeToField("6");
		}
		if (source == v.getMyButton_7()) {
			writeToField("7");
		}
		if (source == v.getMyButton_8()) {
			writeToField("8");
		}
		if (source == v.getMyButton_9()) {
			writeToField("9");
		}
		// ENTER CANCEL DEL BUTTONS

		if (source == v.getMyButton_Enter()) {
			if (m.checkLogIn(v.getTxtCardNumb().getText(), v.getTxtPin()
					.getText())) {
				v.hideStart();
				v.showWork();

			} else {
				highlightInputFields(v.getTxtCardNumb(), v.getTxtPin());
			}
		}
		// CANCEL
		if (source == v.getMyButton_Cancel()) {
			int t = JOptionPane.showConfirmDialog(v, "Are your sure?", "Exit",
					JOptionPane.YES_NO_OPTION);
			if (t == 0) {
				System.exit(0);
			}

		}
		// deleting
		if (source == v.getBtnDelNumbCard()) {
			String s = v.getTxtCardNumb().getText();
			cleanField(v.getTxtCardNumb());
			if (!s.isEmpty()) {
				s = s.substring(0, s.length() - 1);
				v.getTxtCardNumb().setText(s);
			}
		}// delCardNumb

		if (source == v.getBtnDelPin()) {
			String s = v.getTxtPin().getText();
			cleanField(v.getTxtPin());
			if (!s.isEmpty()) {
				s = s.substring(0, s.length() - 1);
				v.getTxtPin().setText(s);
			}
		}// delPin

	}// action Performed

	private void cleanField(JTextField txtCardNumb) {
		txtCardNumb.setBackground(new Color(255, 255, 255));
	}

	private void highlightInputFields(JTextField txtCardNumb, JTextField txtPin) {
		txtCardNumb.setBackground(new Color(123, 255, 0));
		txtPin.setBackground(new Color(123, 255, 0));

	}

	// Need to find how to input into right fields
	private void writeToField(String s) {

		if (v.getRdbtnCardNumb().isSelected()) {
			v.getTxtCardNumb().setText(v.getTxtCardNumb().getText() + s);
			cleanField(v.getTxtCardNumb());

		} else if (v.getRdbtPass().isSelected()) {
			v.getTxtPin().setText(v.getTxtPin().getText() + s);
			cleanField(v.getTxtPin());
		}
	}

}// End of class

