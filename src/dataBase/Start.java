package dataBase;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Start extends JFrame {

	private JScrollPane scrollPane;
	private Dimension screenSize; 
	
	private List<String> tables;

	private JPanel getColumsPane(final String table) {
		JPanel res = new MetroPanel();
		
		Database db = new Database();
		List<String> colums = db.getColums(table);

		
		int x1 = 100;
		int y1 = 100;
		int btnWidth = 250;
		int btnHeight = 35;
		int delta = 7;
		int numButtons = 0;
		int height = y1;
		
		 new Font("Century Gothic", Font.BOLD, 14);
         
         /*JLabel centerLabel = new JLabel("Center");
         centerLabel.setVerticalAlignment(JLabel.CENTER);
         centerLabel.setHorizontalAlignment(JLabel.CENTER);
         centerLabel.setPreferredSize(labelSize);
         centerLabel.setBorder(solidBorder);
         centerLabel.setFont(font);
         alignmentPanel.add(centerLabel);*/
		

		for (String colum: colums) {
			
			MyButton tableBtn = new MyButton(colum);
			tableBtn.setBounds(x1, y1, btnWidth, btnHeight);
			final String val = colum;
			
			tableBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//System.out.println(db.getColumComments(table, val));
					//setWindow(getMainPage());
				}
			});
			
			res.add(tableBtn);
			++numButtons;
			y1 = y1 + btnHeight + delta;
		}
		
		MyButton back = new MyButton("Back");
		back.setBounds(x1, y1 + 50, btnWidth, btnHeight);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setWindow(getMainPage());
			}
		});
		
		res.add(back);
		height += (numButtons * (btnHeight + delta)) + 100;
		res.setPreferredSize(new Dimension(770,height));
		
	    return res;
	}
	
	private JPanel getMainPage() {
		JPanel contentPane = new MetroPanel();
		
		if (this.tables == null) {
			Database db = new Database();
			this.tables = db.getTables();
		}
		
		ImageIcon image = new ImageIcon("imagesForATM\\db.png");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		label.setBounds(130, 140, 120, 120);
		contentPane.add(label);
		
		int x1 = 400;
		int y1 = 70;
		int btnWidth = 250;
		int btnHeight = 35;
		int delta = 7;
		int numButtons = 0;
		int height = y1;
		
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
			++numButtons;
			y1 = y1 + btnHeight + delta;
		}
		
		height += (numButtons * (btnHeight + delta)) + 100;
		contentPane.setPreferredSize(new Dimension(770,height));
		
		return contentPane;
	}
	
	private void setWindow(Container container) {
		if (scrollPane != null) {
			getContentPane().remove(scrollPane);
		}
		
		scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 800, 600);
		
		int increment = 20;
		scrollPane.getVerticalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getVerticalScrollBar().setUnitIncrement(increment);
		scrollPane.getHorizontalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getHorizontalScrollBar().setUnitIncrement(increment);
		
		getContentPane().add(scrollPane);
		invalidate();
		validate();
		repaint();
	}

	public Start() {
		super("Database Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - 800) / 2;
	    int y = (screenSize.height - 600) / 2;
		setBounds(x, y, 800, 600);

		setWindow(getMainPage());
		getContentPane().add(scrollPane);
	}
	

}
