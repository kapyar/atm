package dataBase;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import MYGUI.MetroPanel;
import MYGUI.MetroScrollBar;
import MYGUI.MyButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import MYGUI.ButtonFactory;


public class Start extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JPanel wrapper;
	private JPanel scroll_holder;
	private JPanel navigation_holder;

	private Dimension screenSize; 
	private int window_width = 1000;
	private int window_height = 600;
	private int navi_height = 55;
	
	private List<String> tables;
	private HashMap<String, Object[][]> columns_data_map;
	private HashMap<String, String[]> columns_name_map;
	
	private Stack<Pair<String, Container>> to_add;
	private Stack<Container> navi;
	private Stack<Container> new_navi;
	
	public MyButton getNaviButton ( String btn,final Container goTo, int naviSize, int padding ) {
		
		MyButton temp = new MyButton();
		temp.setPreferredSize(new Dimension(naviSize - padding*2, naviSize - padding*2));
		temp.setMargin(new Insets(padding,padding,padding,padding));
		
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setWindow(goTo);
			}
		});
		
		String fullPath  = "imagesForBaseAdmin\\" + btn + ".png";		
		
		ImageIcon start = new ImageIcon(fullPath);
		temp.setIcon(start);
		
		return temp;
	}
	

	private JTable getColumsPane(final String curr_table) {
		if (columns_data_map.containsKey(curr_table) == false) {
			
			Database db = new Database();
			List<String> colums = db.getColums(curr_table);
			String[] columnNames = new String[colums.size()];
			
			for (int i = 0; i < colums.size(); ++i) {
				columnNames[i] = colums.get(i);
			}
	
			
	
			List<String[]> tData = db.getTableData(curr_table, columnNames);
			Object[][] data = new Object[tData.size()][];
			
			for (int i = 0; i < tData.size(); ++i) {
				data[i] = tData.get(i);
			}
			columns_name_map.put(curr_table, columnNames);
			columns_data_map.put(curr_table, data);
		}
		
		to_add.push(new Pair(ButtonFactory.BACK, getMainPage()));
		
		

		final JTable table = new JTable(columns_data_map.get(curr_table), columns_name_map.get(curr_table));
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		
		return table;

		//System.out.println(db.getColType(curr_table, columnNames[2]));
		
		/*MyButton back = new MyButton("Back");
		back.setBounds(x1, y1 + 50, btnWidth, btnHeight);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setWindow(getMainPage());
			}
		});
		
		res.add(back);
		height += (numButtons * (btnHeight + delta)) + 100;
		res.setPreferredSize(new Dimension(770,height));*/
		
	    //return res;
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
	
	private void setNavi () {
		/*navigation_holder.add(ButtonFactory.getNaviButton(ButtonFactory.BACK, new Container(), navi_height, 5));
		navigation_holder.add(ButtonFactory.getNaviButton(ButtonFactory.FORWARD, new Container(), navi_height, 5));
		navigation_holder.add(ButtonFactory.getNaviButton(ButtonFactory.DELETE, new Container(), navi_height, 5));*/
		
		while (to_add.empty() == false) {
			Pair<String, Container> temp = to_add.pop();
			new_navi.add(getNaviButton(temp.first(), temp.second(), navi_height, 5));
		}
		
		while (navi.empty() == false) {
			navigation_holder.remove(navi.pop());
		}
		
		while (new_navi.empty() == false) {
			Container temp = new_navi.pop();
			navigation_holder.add(temp);
			navi.push(temp);
		}
		
		
		
		navigation_holder.revalidate();
		navigation_holder.repaint();
	}
	
	private void setWindow(Container container) {
		if (scrollPane != null) {
			scroll_holder.remove(scrollPane);
		}

		scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(window_width - 5, window_height - navi_height - 28);
		
		int increment = 20;
		scrollPane.getVerticalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getVerticalScrollBar().setUnitIncrement(increment);
		scrollPane.getHorizontalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getHorizontalScrollBar().setUnitIncrement(increment);
		
		//getContentPane().add(scrollPane);
		
		scroll_holder.add(scrollPane);
		scrollPane.revalidate();
		scrollPane.repaint();
		
		setNavi();
		
		/*invalidate();
		validate();
		repaint();*/
	}
	
	public Start() {
		super("Database Client");
		
		columns_data_map = new HashMap<String, Object[][]>();
		columns_name_map = new HashMap<String, String[]>();
		
		to_add = new Stack<Pair<String, Container>>();
		navi = new Stack<Container>();
		new_navi = new Stack<Container>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - window_width) / 2;
	    int y = (screenSize.height - window_height) / 2;
	    
		setBounds(x, y, window_width, window_height);
		
		init_layouts();
		
		setWindow(getMainPage());
		
		//getContentPane().add(scrollPane);
	}
	
	private void init_layouts() {
		wrapper = new JPanel();
		wrapper.setLayout(null);
		
		scroll_holder = new JPanel();
		scroll_holder.setLayout(null);
		scroll_holder.setSize(window_width, window_height - navi_height - 28);
		scroll_holder.setLocation(0, navi_height);
		
		navigation_holder = new JPanel();
		navigation_holder.setLayout(new FlowLayout(FlowLayout.LEFT));
		navigation_holder.setSize(window_width, navi_height);
		navigation_holder.setBackground(new Color(51, 102, 255));
		
		wrapper.add(scroll_holder);
		wrapper.add(navigation_holder);
		
		add(wrapper);
	}
	
}
