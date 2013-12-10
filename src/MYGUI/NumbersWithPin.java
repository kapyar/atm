package MYGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumbersWithPin extends Numbers {

	private MetroEditablePin pinView;
	// NumbersWithTextField sizes and coordinates
	private int _W;
	private int _H;
	private int _x;
	private int _y;

	public NumbersWithPin() {
		_W = this.getWidth();
		_H = this.getHeight();
		_x = this.getX();
		_y = this.getY();
		init();
		addInnerListener();
	}

	// Outer ENTER AND CANCEL
	public void addOuterListener(ActionListener a) {
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
		int _xleft = 16;
		int _ytop = 15;
		int deltaY = 50;

		pinView = new MetroEditablePin();
		int textVievWidth = (pinView.getWidth() + _xleft);
		pinView.setLocation(_xleft, _ytop);

		getMyButton_1().setLocation(_xleft, _ytop + deltaY);
		getMyButton_4().setLocation(_xleft, _ytop + deltaY * 2);
		getMyButton_7().setLocation(_xleft, _ytop + deltaY * 3);
		getMyButton_Cancel().setLocation(_xleft, _ytop + deltaY * 5);

		int _xcentral = (textVievWidth - getMyButton_0().getWidth() + _xleft) / 2;
		getMyButton_2().setLocation(_xcentral, _ytop + deltaY);
		getMyButton_5().setLocation(_xcentral, _ytop + deltaY * 2);
		getMyButton_8().setLocation(_xcentral, _ytop + deltaY * 3);
		getMyButton_0().setLocation(_xcentral, _ytop + deltaY * 4);

		int _xright = textVievWidth - getMyButton_0().getWidth();
		getMyButton_3().setLocation(_xright, _ytop + deltaY);
		getMyButton_6().setLocation(_xright, _ytop + deltaY * 2);
		getMyButton_9().setLocation(_xright, _ytop + deltaY * 3);

		getMyButton_Enter().setLocation(
				textVievWidth - getMyButton_Enter().getWidth(),
				_ytop + deltaY * 5);

		add(pinView);

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
			pinView.getPass().setText(pinView.getPass().getText() + s);
		}

	}

	public MetroEditablePin getTextView() {
		return pinView;
	}

}// END InnerListener

