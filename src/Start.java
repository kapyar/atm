import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.HashSet;


public class Start extends JFrame {

	private JScrollPane scrollPane;
	private Dimension screenSize; 
	
	
	private JPanel getColumsPane(final String table) {
		final JPanel res = new JPanel();
		
		res.setLayout(null);
		res.setBackground(new Color(51, 153, 255));
		
		final Database db = new Database();
		HashSet<String> colums = db.getColums(table);
		
		int x1 = 100;
		int y1 = 100;
		int btnWidth = 250;
		int btnHeight = 35;
		int delta = 7;
		
		
		for (String colum: colums) {
			
			MyButton tableBtn = new MyButton(colum);
			tableBtn.setBounds(x1, y1, btnWidth, btnHeight);
			final String val = colum;
			
			tableBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(db.getColumComments(table, val));
				}
			});
			
			res.add(tableBtn);
			
			y1 = y1 + btnHeight + delta;
		
		}
		
		MyButton back = new MyButton("Back");
		back.setBounds(x1, y1 + 50, btnWidth, btnHeight);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(res);
			}
		});
		res.add(back);

	    return res;
	}
	
	private void setWindow(Container container) {
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		invalidate();
		validate();
		repaint();
	}

	public Start() {
		super("Database Client");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JPanel contentPane = new MetroPanel();

		scrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);

		int x = (screenSize.width - 800) / 2;
	    int y = (screenSize.height - 600) / 2;
		setBounds(x, y, 800, 600);


		final Database db = new Database();
		HashSet<String> tables = db.getTables();
		
		int x1 = 100;
		int y1 = 100;
		int btnWidth = 250;
		int btnHeight = 35;
		int delta = 7;
		
		
		for (String table: tables) {
			MyButton tableBtn = new MyButton(table);
			tableBtn.setBounds(x1, y1, btnWidth, btnHeight);
			final String val = table;
			
			tableBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setWindow(getColumsPane(val));
				}
			});

			contentPane.add(tableBtn);
			y1 = y1 + btnHeight + delta;
		}


		
	}
	

}
