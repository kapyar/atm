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
 * Bind interface and model(calculating requests to BD)
 */
public class Controller {
	// package access
	private MainFormClient mainForm;
	private Model model;

	public Controller(MainFormClient form, Model model) {
		this.mainForm = form;
		this.model = model;

		mainForm.addListenerBalance(new ActionListenerBalance(this));
		mainForm.addMainFormListener(new ActionListenerStart(this));
		
		mainForm.setVisible(true);
	}

	public MainFormClient getView() {
		return mainForm;
	}

	public Model getModel() {
		return model;
	}

}
