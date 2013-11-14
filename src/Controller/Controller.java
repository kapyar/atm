package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUIClient.MainFormClient;
import Model.Model;

/*
 * 
 * Bind interface and model(calculating requets to BD)
 */
public class Controller {
	//package access
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
			 
			 if(source == mainForm.getMyButton_0())
			 {
				writeToField("0");
			 }

		}//action Performed

		private void writeToField(String s) {
			if(mainForm.getTxtCardNumb().isFocusable()){
				mainForm.getTxtCardNumb().setText(mainForm.getTxtCardNumb().getText()+s);
			}
			else if(mainForm.getTxtPin().isFocusable()){
				mainForm.getTxtPin().setText(mainForm.getTxtPin().getText()+s);
			}
			
		}

	}//NumbButtonActionListener

}
