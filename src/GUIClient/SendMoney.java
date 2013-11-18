package GUIClient;

import MYGUI.ButtonFactory;
import MYGUI.MetroEditablePane;
import MYGUI.MyButton;
import MYGUI.RightPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;

public class SendMoney extends RightPanel{

	private MetroEditablePane how;
	private MetroEditablePane whome;
	private MyButton btnOK;
	private MyButton mbtnCancel;

	public SendMoney(){
		
		JLabel lblNewLabel = new JLabel("Send Money");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 27));
		lblNewLabel.setBounds(274, 64, 161, 28);
		add(lblNewLabel);
		
		how = new MetroEditablePane();
		how.setLocation(93, 234);
		add(how);
		
		whome = new MetroEditablePane();
		whome.setBounds(428, 234, 190, 31);
		add(whome);
		
		btnOK = ButtonFactory.getSelectBtn("Send");
		btnOK.setLocation(150, 433);
		add(btnOK);
		
		mbtnCancel = ButtonFactory.getSelectBtn("Cancel");
		mbtnCancel.setBounds(428, 433, 132, 45);
		add(mbtnCancel);
		
		addInnerListener();
		
	}

	private void addInnerListener() {
		
		
	}
}
