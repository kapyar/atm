package MYGUI;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataBase.Pair;

public class MetroTable extends AbstractTableModel {
	String []   names;
	String []   types;
	Object [][] data;
	Pair<String, List<Integer>> edited;
	
	public MetroTable (Object [][] table_data, String [] col_names, String [] col_types, Pair<String, List<Integer>> edit) {
		data  = table_data;
		names = col_names;
		types = col_types;
		edited = edit;
	}
	
	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	@Override
    public String getColumnName(int column) {
		return names[column];
    }
    
	@Override
    public boolean isCellEditable(int row, int column) {
       if (column == 0 ) {
	        return false;
	   }
       
       return true; 
    }
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		 data[row][col] = value;
		 edited.second().add(row);
	}
	
	public void addRow(Pair<String, List<Integer>> addTo) {  
		Object [][] temp = temp = Arrays.copyOf (data, data.length + 1);
		
		temp[data.length] = new Object[names.length];
		temp[data.length][0] = "new";
		data = temp;
		
		addTo.second().add(data.length);
		
	    this.fireTableDataChanged();
    }  

}
