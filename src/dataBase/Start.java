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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import MYGUI.MetroPanel;
import MYGUI.MetroScrollBar;
import MYGUI.MetroTable;
import MYGUI.MyButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
	private HashMap<String, String[]> columns_type_map;
	
	private Stack<Pair<String, Container>> to_add;
	private Stack<Container> navi;
	private Stack<Container> new_navi;
	private MetroTable current_model;
	
	private Database db;
	
	public static boolean error_flag;
	
	
	public Pair<String, List<Integer>> last_edited = new Pair<String, List<Integer>>(null, null);
	public Pair<String, List<Integer>> last_added = new Pair<String, List<Integer>>(null, null);
	
	
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
		temp.setIcon(new ImageIcon(fullPath));
		
		return temp;
	}
	
	public MyButton getAddButton ( String btn, int naviSize, int padding ) {
		MyButton temp = new MyButton();
		temp.setPreferredSize(new Dimension(naviSize - padding*2, naviSize - padding*2));
		temp.setMargin(new Insets(padding,padding,padding,padding));
		
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				current_model.addRow(last_added);
			}
		});
		
		String fullPath  = "imagesForBaseAdmin\\" + btn + ".png";
		temp.setIcon(new ImageIcon(fullPath));

		return temp;
	}
	
	public MyButton getSaveButton ( String btn, int naviSize, int padding ) {
		MyButton temp = new MyButton();
		temp.setPreferredSize(new Dimension(naviSize - padding*2, naviSize - padding*2));
		temp.setMargin(new Insets(padding,padding,padding,padding));
		
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object [][] table_data = current_model.getData();

				for (int i = 0; i < last_added.second().size(); ++i) {
					//System.out.println("Adding indexes: " + last_added.second().get(i));
					db.insertRow(last_added.first(), table_data[last_added.second().get(i)], current_model.getFieldNames(),current_model.getFieldTypes());
				}

				
				for (int i = 0; i < last_edited.second().size(); ++i) {
					db.updateRow(last_added.first(), table_data[last_edited.second().get(i)], current_model.getFieldNames(),current_model.getFieldTypes());
				}
				
				if (!error_flag) {
					JOptionPane.showMessageDialog(null, "Added: " + last_added.second().size() + " rows\n" + "Updated: " + last_edited.second().size() + " rows");
					
					//System.out.println("Updated: " + last_edited.second().size() + " rows");
					//System.out.println("Added: " + last_added.second().size() + " rows");
				}
				
				columns_data_map.remove(last_added.first());
				setWindow(getColumsPane(last_added.first()));
		
			}
		});
		
		String fullPath  = "imagesForBaseAdmin\\" + btn + ".png";
		temp.setIcon(new ImageIcon(fullPath));

		return temp;
	}
	
	
	private JTable getColumsPane(final String curr_table) {
		last_edited.set_first(curr_table);
		last_edited.set_second(new ArrayList<Integer>());
		
		last_added.set_first(curr_table);
		last_added.set_second(new ArrayList<Integer>());
		
		
		
		if (columns_data_map.containsKey(curr_table) == false) {
			
			List<String> colums = db.getColums(curr_table);
			String[] columnNames = new String[colums.size()];
			String [] col_types = db.getColTypes(curr_table, columnNames);
			
			for (int i = 0; i < colums.size(); ++i) {
				columnNames[i] = colums.get(i);
			}
	

			List<String[]> tData = db.getTableData(curr_table, columnNames);

			Object[][] data = new Object[tData.size()][];
			
			for (int i = 0; i < tData.size(); ++i) {
				data[i] = tData.get(i);
			}
			columns_name_map.put(curr_table, columnNames);
			columns_type_map.put(curr_table, col_types);
			columns_data_map.put(curr_table, data);
		}
		
		to_add.push(new Pair(ButtonFactory.BACK, getMainPage()));
		to_add.push(new Pair(ButtonFactory.ADD, new JPanel()));
		to_add.push(new Pair(ButtonFactory.DONE, new JPanel()));
		//to_add.push(new Pair(ButtonFactory.DELETE, new JPanel()));
		

		current_model = new MetroTable(	columns_data_map.get(curr_table), 
										columns_name_map.get(curr_table), 
										columns_type_map.get(curr_table), 
										last_edited);
		
		final JTable table = new JTable (current_model);
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		return table;

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
		
		while (to_add.empty() == false) {
			Pair<String, Container> temp = to_add.pop();
			if (temp.first().equals(ButtonFactory.ADD)) {
				new_navi.add(getAddButton(temp.first(), navi_height, 5));
			} else if (temp.first().equals(ButtonFactory.DONE)) {
				new_navi.add(getSaveButton(temp.first(), navi_height, 5));
			} else  {
				new_navi.add(getNaviButton(temp.first(), temp.second(), navi_height, 5));
			}
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
		
		db = new Database();
		error_flag = false;
		
		columns_data_map = new HashMap<String, Object[][]>();
		columns_name_map = new HashMap<String, String[]>();
		columns_type_map = new HashMap<String, String[]>();
		
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
