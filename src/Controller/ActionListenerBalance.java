package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUIClient.MainFormClient;
import Model.Model;

public class ActionListenerBalance implements ActionListener {

	private Model m;
	private MainFormClient v;

	public ActionListenerBalance(Controller controller) {
		System.out.println("Construct ActionListenerBalance");
		this.m = controller.getModel();
		this.v = controller.getView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		System.out.println("Hatuna - matata");
		if(source == v.getBtnBalance()){
			System.out.println("Hatuna - matata");
		}

	}

}
