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
		
		
		int _W = 180;
		int _H = 264;
		
		
		int  _x = 10;
		int _y = 0;
		int _deltaX = 0;
		int _deltaY =50;
		setBounds(204, 116, _W, _H);
		this.setBackground(new Color(51, 153,255 ));
		// setBackground(new Color(40, 140, 255));
		setVisible(true);

		listOfComponents = new ArrayList<MyButton>();

		myButton_1 = ButtonFactory.getNumbButton("1", '1');
		myButton_1.setLocation(_x+_deltaX*0, _y+_deltaY*0);
		add(myButton_1);
		listOfComponents.add(myButton_1);

		myButton_4 = ButtonFactory.getNumbButton("4", '4');
		myButton_4.setBounds(_x+_deltaX*0, _y+_deltaY*1,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_4);
		listOfComponents.add(myButton_4);
		
		myButton_7 = ButtonFactory.getNumbButton("7", '7');
		myButton_7.setBounds(_x+_deltaX*0, _y+_deltaY*2,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_7);
		listOfComponents.add(myButton_7);
		
		myButton_2 = ButtonFactory.getNumbButton("2", '2');
		myButton_2.setBounds(_x+((_W-_x)/2-ConfigGUICLient.bNS/2), _y+_deltaY*0,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_2);
		listOfComponents.add(myButton_2);

		myButton_5 = ButtonFactory.getNumbButton("5", '5');
		myButton_5.setBounds(_x+((_W-_x)/2-ConfigGUICLient.bNS/2), _y+_deltaY*1,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_5);
		listOfComponents.add(myButton_5);

		myButton_8 = ButtonFactory.getNumbButton("8", '8');
		myButton_8.setBounds(_x+((_W-_x)/2-ConfigGUICLient.bNS/2), _y+_deltaY*2,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_8);
		listOfComponents.add(myButton_8);
		
		
		myButton_0 = ButtonFactory.getNumbButton("0", '0');
		myButton_0.setBounds(_x+((_W-_x)/2-ConfigGUICLient.bNS/2), _y+_deltaY*3,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_0);
		listOfComponents.add(myButton_0);
		
		
		myButton_3 = ButtonFactory.getNumbButton("3", '3');
		myButton_3.setBounds((_W-ConfigGUICLient.bNS), _y+_deltaY*0,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_3);
		listOfComponents.add(myButton_3);

		myButton_6 = ButtonFactory.getNumbButton("6", '6');
		myButton_6.setBounds((_W-ConfigGUICLient.bNS), _y+_deltaY*1,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_6);
		listOfComponents.add(myButton_6);
	
		myButton_9 = ButtonFactory.getNumbButton("9", '9');
		myButton_9.setBounds((_W-ConfigGUICLient.bNS), _y+_deltaY*2,
				ConfigGUICLient.bNS,ConfigGUICLient.bNS);
		add(myButton_9);
		listOfComponents.add(myButton_9);

		myButton_Cancel = ButtonFactory.getNumbButton("Cancel", 'C');
		myButton_Cancel.setBounds(_x, _y+_deltaY*4, ConfigGUICLient.btnEW, ConfigGUICLient.bNS);
		add(myButton_Cancel);
		listOfComponents.add(myButton_Cancel);

		myButton_Enter = ButtonFactory.getNumbButton("Enter", 'E');
		myButton_Enter.setBounds((_W-myButton_Cancel.getWidth()), _y+_deltaY*4, ConfigGUICLient.btnEW, ConfigGUICLient.bNS);
		add(myButton_Enter);
		listOfComponents.add(myButton_Enter);

		
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
