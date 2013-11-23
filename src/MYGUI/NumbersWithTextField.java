package MYGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumbersWithTextField extends Numbers {
	private  MetroEditablePane textView;
	// NumbersWithTextField sizes and coordinates
		private int _W;
		private int _H;
		private int _x;
		private int _y;

		
		
	public NumbersWithTextField() {
		_W = this.getWidth();
		_H = this.getHeight();
		_x = this.getX();
		_y = this.getY();
		init();
		addInnerListener();
	}
//Outer ENTER AND CANCEL
	public void addOuterListener(ActionListener a){
		getMyButton_Cancel().addActionListener(a);
		getMyButton_Enter().addActionListener(a);
	}
	
	private void addInnerListener() {
		// this is not inner
		getListOfComponents().remove(getMyButton_Cancel());
		getListOfComponents().remove(getMyButton_Enter());

		for (MyButton a : getListOfComponents()) {
			a.addActionListener(new InnerListener());
		}

	}
	
	private void init() {
		this.getX();
		getMyButton_Enter().setLocation(119, 264);
		getMyButton_Cancel().setLocation(22, 264);
		getMyButton_0().setLocation(_x, _y);
		getMyButton_7().setLocation(36, 163);
		getMyButton_8().setLocation(88, 163);
		getMyButton_9().setLocation(139, 163);
		getMyButton_6().setLocation(139, 110);
		getMyButton_3().setLocation(139, 57);
		getMyButton_4().setLocation(36, 110);
		getMyButton_1().setLocation(36, 57);
		getMyButton_5().setLocation(88, 110);
		getMyButton_2().setLocation(88, 57);
		textView = new MetroEditablePane();
		textView.setLocation(16, 15);
		add(textView);

	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == getMyButton_0()) {
				writeIt("0");
			}
			if (source == getMyButton_1()) {
				writeIt("1");
			}
			if (source == getMyButton_2()) {
				writeIt("2");
			}
			if (source == getMyButton_3()) {
				writeIt("3");
			}
			if (source == getMyButton_4()) {
				writeIt("4");
			}
			if (source == getMyButton_5()) {
				writeIt("5");
			}
			if (source == getMyButton_6()) {
				writeIt("6");
			}
			if (source == getMyButton_7()) {
				writeIt("7");
			}
			if (source == getMyButton_8()) {
				writeIt("8");
			}
			if (source == getMyButton_9()) {
				writeIt("9");
			}

		}

		private void writeIt(String s) {
			textView.getTextField().setText(
					textView.getTextField().getText() + s);
		}

	}

	public MetroEditablePane getTextView() {
		return textView;
	}

}// END InnerListener

