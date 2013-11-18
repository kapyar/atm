package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUIClient.Balance;
import GUIClient.MainFormClient;
import GUIClient.StartFrame;


public class Controller{
	
	private StartFrame start;
	private Balance balance;
	private MainFormClient mainConteiner;
	
	
	
	public Controller(MainFormClient mainConteiner,StartFrame start, Balance balance){
		System.out.println("Constructing Controller");
		this.mainConteiner = mainConteiner;
		this.start = start;
		this.balance = balance;
		this.mainConteiner.resetPanel(start);
		this.start.addOuterListener(new OuterStartActionListener());
		this.balance.addNavigationListeners(new NavigationListeners());
		System.out.println("must be visiable");
		this.mainConteiner.setVisible(true);
		
	}

	
	 public class OuterStartActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == start.getMyButton_Enter()){
				System.out.println("Pressed the Enter");
			//	balance.addNavigationListeners(new NavigationListeners());
				mainConteiner.resetPanel(balance);
			}
		}
		 
	 }//END OuterStartActionListener
	 
	 class NavigationListeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object source = e.getSource();
			
			if(source == balance.getBtnBalance()){
				System.out.println("Balance!!");	
			}
			
			if(source == balance.getBtnPayBill()){
				System.out.println("Must change the panels");
				balance.resetRightPanel(start);
			}
		}
		 
	 }//END NavigationListeners
}
