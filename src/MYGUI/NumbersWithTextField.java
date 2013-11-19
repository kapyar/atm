package MYGUI;

public class NumbersWithTextField extends Numbers {
	private MetroEditablePane textView;

	public NumbersWithTextField() {

		init();
	}

	private void init() {
		getMyButton_Enter().setLocation(119, 264);
		getMyButton_Cancel().setLocation(22, 264);
		getMyButton_0().setLocation(88, 211);
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

}
