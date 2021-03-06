package GUIClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.Controller.OuterStartActionListener;
import Controller.NumberCheker;
import MYGUI.ButtonFactory;
import MYGUI.ConfigGUICLient;
import MYGUI.MetroEditablePane;
import MYGUI.MetroEditablePin;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.JProgressBar;

public class StartFrame extends MetroPanel implements MouseListener {

	// private Font localFont = new Font("Viner Hand ITC", Font.PLAIN, 19);
	private String FontType = ConfigGUICLient.FontType;
	// private String FontType = "Colibry";
	private MyButton myButton_1;
	private MyButton myButton_2;
	private MyButton myButton_3;
	private MyButton myButton_6;
	private MyButton myButton_5;
	private MyButton myButton_4;
	private MyButton myButton_7;
	private MyButton myButton_8;
	private MyButton myButton_9;
	private MyButton myButton_0;
	private MyButton myButton_Enter;
	private MetroPanel panel;

	private MyButton myButton_Cancel;
	private ArrayList<MyButton> listOfComponents = new ArrayList<MyButton>();
	private JRadioButton rdbtnCardNumb;
	private JRadioButton rdbtPass;
	private MetroEditablePane txt;
	private MetroEditablePin pin;

	// StartFrame sizes and coordinates
	private int _W;
	private int _H;
	private int _x;
	private int _y;
	public JProgressBar progressBar;

	public StartFrame() {
		_W = super.getWidth();
		_H = super.getHeight();
		_x = super.getX();
		_y = super.getY();

		System.out.println("Start Frame");
		JLabel lblBalanceTitle = new JLabel("with love from MPK Team ...", JLabel.CENTER);
		lblBalanceTitle.setFont(new Font(FontType, Font.PLAIN, 27));
		lblBalanceTitle.setForeground(new Color(255, 255, 255));
		lblBalanceTitle.setBounds((_W / 2) - ConfigGUICLient._WTL / 2,
				ConfigGUICLient._yTL, ConfigGUICLient._WTL,
				ConfigGUICLient._HTL);
		lblBalanceTitle.setAlignmentX(this.CENTER_ALIGNMENT);

		add(lblBalanceTitle);
		panel = new MetroPanel();
		panel.setBounds((_x + _W / 2) - 220, 116, 440, 296);
		panel.setOpaque(true);
		panel.setBackground(new Color(40, 140, 255));
		panel.setVisible(true);
		panel.setAlignmentX(this.CENTER_ALIGNMENT);
		add(panel);

		myButton_1 = ButtonFactory.getNumbButton("1", '1');
		myButton_1.setLocation(24, 79);
		panel.add(myButton_1);
		listOfComponents.add(myButton_1);

		myButton_2 = ButtonFactory.getNumbButton("2", '2');
		myButton_2.setBounds(76, 79, 42, 42);
		panel.add(myButton_2);
		listOfComponents.add(myButton_2);

		myButton_3 = ButtonFactory.getNumbButton("3", '3');
		myButton_3.setBounds(127, 79, 42, 42);
		panel.add(myButton_3);
		listOfComponents.add(myButton_3);

		myButton_6 = ButtonFactory.getNumbButton("6", '6');
		myButton_6.setBounds(127, 132, 42, 42);
		panel.add(myButton_6);
		listOfComponents.add(myButton_6);

		myButton_5 = ButtonFactory.getNumbButton("5", '5');
		myButton_5.setBounds(76, 132, 42, 42);
		panel.add(myButton_5);
		listOfComponents.add(myButton_5);

		myButton_4 = ButtonFactory.getNumbButton("4", '4');
		myButton_4.setBounds(24, 132, 42, 42);
		panel.add(myButton_4);
		listOfComponents.add(myButton_4);

		myButton_7 = ButtonFactory.getNumbButton("7", '7');
		myButton_7.setBounds(24, 185, 42, 42);
		panel.add(myButton_7);
		listOfComponents.add(myButton_7);

		myButton_8 = ButtonFactory.getNumbButton("8", '8');
		myButton_8.setBounds(76, 185, 42, 42);
		panel.add(myButton_8);
		listOfComponents.add(myButton_8);

		myButton_9 = ButtonFactory.getNumbButton("9", '9');
		myButton_9.setBounds(127, 185, 42, 42);
		panel.add(myButton_9);
		listOfComponents.add(myButton_9);

		myButton_0 = ButtonFactory.getNumbButton("0", '0');
		myButton_0.setBounds(76, 233, 42, 42);
		panel.add(myButton_0);
		listOfComponents.add(myButton_0);

		myButton_Enter = ButtonFactory.getNumbButton("Enter", 'E');
		myButton_Enter.setBounds(327, 233, 87, 42);
		panel.add(myButton_Enter);
		// listOfComponents.add(myButton_Enter);

		myButton_Cancel = ButtonFactory.getNumbButton("Cancel", 'C');
		myButton_Cancel.setBounds(230, 233, 87, 42);
		panel.add(myButton_Cancel);
		listOfComponents.add(myButton_Cancel);

		// ���������� �� ������ �������� �����
		int _xRightElemets = 224;
		int _yRightElemets = 68;
		int _deltaYRE = 35;// delta y right element;

		int _WRightElemets = 190;
		int _HRightElemets = 31;

		JLabel lblNubrerCart = new JLabel("Card number", JLabel.LEFT);
		/*
		 * lblNewLabel.setForeground(Color.WHITE); lblNewLabel.setFont(new
		 * Font("Segoe UI", Font.PLAIN, 11));
		 */
		lblNubrerCart.setForeground(Color.WHITE);
		lblNubrerCart.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNubrerCart.setBounds(224, 84, 190, 15);
		panel.add(lblNubrerCart);

		txt = new MetroEditablePane();
		txt.getDel().setLocation(160, 3);
		txt.getTextField().setBounds(8, 5, 144, 20);
		txt.getTextField().addMouseListener(this);
		txt.setBounds(_xRightElemets, _yRightElemets + _deltaYRE,
				_WRightElemets, _HRightElemets);
		panel.add(txt);

		JLabel lblPin = new JLabel("PIN", JLabel.LEFT);
		lblPin.setForeground(Color.WHITE);
		lblPin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPin.setBounds(224, 154, 190, 15);
		panel.add(lblPin);

		pin = new MetroEditablePin();
		pin.getPass().addMouseListener(this);
		pin.setBounds(_xRightElemets, _yRightElemets + _deltaYRE * 3,
				_WRightElemets, _HRightElemets);
		panel.add(pin);

		rdbtnCardNumb = new JRadioButton("");
		rdbtnCardNumb.setBounds(203, 106, 21, 23);
		rdbtnCardNumb.setBackground(new Color(40, 140, 255));
		rdbtnCardNumb.setSelected(true);
		panel.add(rdbtnCardNumb);

		rdbtPass = new JRadioButton("");
		rdbtPass.setBounds(203, 176, 21, 23);
		rdbtPass.setBackground(new Color(40, 140, 255));
		panel.add(rdbtPass);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCardNumb);
		radioGroup.add(rdbtPass);

		progressBar = new JProgressBar();
		progressBar.setBounds(180, 412, 440, 29);
		progressBar.setVisible(false);
		add(progressBar);

		addInnerListener();

	}
	
	public JRadioButton getCardRadioBtn() {
		return rdbtnCardNumb;
	}

	public void clearFields() {
		txt.getTextField().setText("");
		pin.getPass().setText("");
		rdbtnCardNumb.setFocusable(true);
	}

	public boolean checkInputData() {
		if (!NumberCheker.isNumber(txt.getTextField().getText())) {
			System.out.println("Incorect nuber cart");
			return false;
		}
		if (!NumberCheker.isNumber(pin.getPass().getText())) {
			System.out.println("Incorect PIN code");
			return false;
		}
		if (txt.getTextField().getText().equals("")
				|| pin.getPass().getText().equals("")) {
			return false;
		}
		return true;
	}

	public void addOuterListener(OuterStartActionListener m) {
		myButton_Enter.addActionListener(m);
	}

	public MyButton getMyButton_Enter() {
		return myButton_Enter;
	}

	private void addInnerListener() {
		for (MyButton b : listOfComponents) {
			b.addActionListener(new ActionListenerStart());
		}

	}

	public StartFrame getStart() {
		return this;
	}

	public class ActionListenerStart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == myButton_0) {
				writeToField("0");
			}
			if (source == myButton_1) {
				writeToField("1");
			}
			if (source == myButton_2) {
				writeToField("2");
			}
			if (source == myButton_3) {
				writeToField("3");
			}
			if (source == myButton_4) {
				writeToField("4");
			}
			if (source == myButton_5) {
				writeToField("5");
			}
			if (source == myButton_6) {
				writeToField("6");
			}
			if (source == myButton_7) {
				writeToField("7");
			}
			if (source == myButton_8) {
				writeToField("8");
			}
			if (source == myButton_9) {
				writeToField("9");
			}
			// CANCEL
			if (source == myButton_Cancel) {
				int t = JOptionPane.showConfirmDialog(panel, "Are your sure?",
						"Exit", JOptionPane.YES_NO_OPTION);
				if (t == 0) {
					System.exit(0);
				}
			}

		}// action Performed

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
			if (rdbtnCardNumb.isSelected()) {
				txt.getTextField().setText(txt.getTextField().getText() + s);
				cleanField(txt.getTextField());

			} else if (rdbtPass.isSelected()) {
				pin.getPass().setText(pin.getPass().getText() + s);
				cleanField(pin.getPass());
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == txt.getTextField()) {
			// System.out.println("txt");
			rdbtnCardNumb.setSelected(true);
		}
		if (source == pin.getPass()) {
			// System.out.println("pin");
			rdbtPass.setSelected(true);
		}
		// System.out.println("("+e.getX()+":"+e.getY()+")");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// empty method

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// empty method

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// empty method
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// empty method

	}

	public MetroEditablePane getTxt() {
		return txt;
	}

	public MetroEditablePin getPin() {
		return pin;
	}
}
