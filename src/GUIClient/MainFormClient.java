package GUIClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import dataBase.MetroPanel;
import dataBase.MyButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import net.miginfocom.swing.MigLayout;*/
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;

public class MainFormClient extends JFrame {

	private Dimension btnDimention;

	public MainFormClient() throws IOException {
		setResizable(false);
		setSize(new Dimension(800, 600));
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources\\imagesClient\\pig.png"));
		btnDimention = new Dimension(100, 100);
		
		MetroPanel mainPanel = new MetroPanel();
		getContentPane().add(mainPanel);
		ImageIcon start = new ImageIcon(
				"resources\\imagesClient\\balance_100x100.png");	
		
		JPanel pnlSideLeft = new JPanel();
		pnlSideLeft.setBounds(0, 0, 113, 571);
		
		pnlSideLeft.setBackground(new Color(255,255,255));
//		JScrollPane scrollPane = new JScrollPane(pnlSideLeft,
//				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		pnlSideLeft.setLayout(new ScrollPaneLayout());
		
		
		MyButton btnNewButton = new MyButton();
		btnNewButton.setAlignmentY(0.0f);
		btnNewButton.setIcon(start);
		pnlSideLeft.add(btnNewButton);
	
		
//		
//		MyButton myButton = new MyButton();
//		pnlSideLeft.add(myButton);
//		
//		MyButton myButton_1 = new MyButton();
//		pnlSideLeft.add(myButton_1);
//		
//		MyButton myButton_2 = new MyButton();
//		pnlSideLeft.add(myButton_2);

		
		//scrollPane.setBounds(0, 0, 140, 571);
		//mainPanel.add(scrollPane);
		mainPanel.add(pnlSideLeft);
		pnlSideLeft.setLayout(new BoxLayout(pnlSideLeft, BoxLayout.Y_AXIS));
		
		MyButton myButton = new MyButton();
		myButton.setAlignmentY(0.0f);
		pnlSideLeft.add(myButton);
		
		MyButton myButton_1 = new MyButton();
		myButton_1.setAlignmentY(0.0f);
		pnlSideLeft.add(myButton_1);
		
		MyButton myButton_2 = new MyButton();
		myButton_2.setAlignmentY(0.0f);
		pnlSideLeft.add(myButton_2);
		
		MyButton myButton_3 = new MyButton();
		myButton_3.setAlignmentY(0.0f);
		pnlSideLeft.add(myButton_3);
		
		MyButton myButton_4 = new MyButton();
		myButton_4.setAlignmentY(0.0f);
		pnlSideLeft.add(myButton_4);

	}
}
