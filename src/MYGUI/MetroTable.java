package MYGUI;

import javax.swing.table.AbstractTableModel;

public class MetroTable extends AbstractTableModel {
	String []   names;
	String []   types;
	Object [][] data;
	
	public MetroTable (Object [][] table_data, String [] col_names, String [] col_types) {
		data  = table_data;
		names = col_names;
		types = col_types;
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

}
