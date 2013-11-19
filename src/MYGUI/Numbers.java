package MYGUI;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

public class Numbers extends MetroPanel {

	private MyButton myButton_1;
	private ArrayList<MyButton> listOfComponents;
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
	private MyButton myButton_Cancel;

	public Numbers() {
		setBounds(204, 116, 207, 264);
		setBackground(new Color(40, 140, 255));
		setVisible(true);

		listOfComponents = new ArrayList<MyButton>();
		
		myButton_1 = ButtonFactory.getNumbButton("1", '1');
		myButton_1.setLocation(24, 9);
		add(myButton_1);
		listOfComponents.add(myButton_1);

		myButton_2 = ButtonFactory.getNumbButton("2", '2');
		myButton_2.setBounds(76, 9, 42, 42);
		add(myButton_2);
		listOfComponents.add(myButton_2);

		myButton_3 = ButtonFactory.getNumbButton("3", '3');
		myButton_3.setBounds(127, 9, 42, 42);
		add(myButton_3);
		listOfComponents.add(myButton_3);

		myButton_6 = ButtonFactory.getNumbButton("6", '6');
		myButton_6.setBounds(127, 62, 42, 42);
		add(myButton_6);
		listOfComponents.add(myButton_6);

		myButton_5 = ButtonFactory.getNumbButton("5", '5');
		myButton_5.setBounds(76, 62, 42, 42);
		add(myButton_5);
		listOfComponents.add(myButton_5);

		myButton_4 = ButtonFactory.getNumbButton("4", '4');
		myButton_4.setBounds(24, 62, 42, 42);
		add(myButton_4);
		listOfComponents.add(myButton_4);

		myButton_7 = ButtonFactory.getNumbButton("7", '7');
		myButton_7.setBounds(24, 115, 42, 42);
		add(myButton_7);
		listOfComponents.add(myButton_7);

		myButton_8 = ButtonFactory.getNumbButton("8", '8');
		myButton_8.setBounds(76, 115, 42, 42);
		add(myButton_8);
		listOfComponents.add(myButton_8);

		myButton_9 = ButtonFactory.getNumbButton("9", '9');
		myButton_9.setBounds(127, 115, 42, 42);
		add(myButton_9);
		listOfComponents.add(myButton_9);

		myButton_0 = ButtonFactory.getNumbButton("0", '0');
		myButton_0.setBounds(76, 163, 42, 42);
		add(myButton_0);
		listOfComponents.add(myButton_0);

		myButton_Enter = ButtonFactory.getNumbButton("Enter", 'E');
		myButton_Enter.setBounds(107, 216, 87, 42);
		add(myButton_Enter);
		listOfComponents.add(myButton_Enter);

		myButton_Cancel = ButtonFactory.getNumbButton("Cancel", 'C');
		myButton_Cancel.setBounds(10, 216, 87, 42);
		add(myButton_Cancel);
		listOfComponents.add(myButton_Cancel);
	}

	public MyButton getMyButton_1() {
		return myButton_1;
	}

	public ArrayList<MyButton> getListOfComponents() {
		return listOfComponents;
	}

	public MyButton getMyButton_2() {
		return myButton_2;
	}

	public MyButton getMyButton_3() {
		return myButton_3;
	}

	public MyButton getMyButton_6() {
		return myButton_6;
	}

	public MyButton getMyButton_5() {
		return myButton_5;
	}

	public MyButton getMyButton_4() {
		return myButton_4;
	}

	public MyButton getMyButton_7() {
		return myButton_7;
	}

	public MyButton getMyButton_8() {
		return myButton_8;
	}

	public MyButton getMyButton_9() {
		return myButton_9;
	}

	public MyButton getMyButton_0() {
		return myButton_0;
	}

	public MyButton getMyButton_Enter() {
		return myButton_Enter;
	}

	public MyButton getMyButton_Cancel() {
		return myButton_Cancel;
	}

}
