package MYGUI;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataBase.Pair;

public class MetroTable extends AbstractTableModel {
	String []   names;
	String []   types;
	Object [][] data;
	Pair<String, List<Integer>> edited;
	Pair<String, List<Integer>> removed;
	private int originalSize;
	
	public MetroTable (Object [][] table_data, String [] col_names, String [] col_types, Pair<String, List<Integer>> edit, Pair<String, List<Integer>> remove) {
		data  = table_data;
		names = col_names;
		types = col_types;
		edited = edit;
		removed = remove;
		originalSize = data.length;
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
	public Class getColumnClass(int column)
    {
		String type = types[column];
		
        //return data[0][column].getClass();
		if (type.equals("int")) {
			return Integer.class;
		} else if (type.equals("double")) {
			return Double.class;
		} else if (type.equals("tinyint")) {
			return Boolean.class;
		} else if (type.equals("timestamp")) {
			return java.sql.Timestamp.class;
		} else if (type.equals("boolean")) {
			return Boolean.class;
		} else {
			return String.class;
		}

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
		 if (col != (names.length - 1) && row < originalSize && !edited.second().contains(row)) {
			 edited.second().add(row);
		 }
		 
		 if ( col == names.length - 1 && row < originalSize ) {
			 //System.out.println(value);
			 if ((Boolean)value == true) {
				 removed.second().add(row);
			 } else {
				 //removed.second().remove(row);
			 	for(Iterator<Integer> itr = removed.second().iterator();itr.hasNext();) {  
			 		Integer element = itr.next();  
		            if(element.equals(row))  
		            {  
		                itr.remove();  
		            }  
		        }  
			 }
			 
			 /*for (int i = 0; i < removed.second().size(); ++i) {
				 System.out.print(removed.second().get(i) + ",  ");
			 }
			 System.out.println();*/
		 }
	}
	
	public void addRow(Pair<String, List<Integer>> addTo) {  
		Object [][] temp = Arrays.copyOf (data, data.length + 1);

		temp[data.length] = new Object[names.length];
		temp[data.length][0] = "new";
		
		data = temp;	
		
		addTo.second().add(data.length-1);
		
	    this.fireTableDataChanged();
    }  
	
	public Object [][] getData() {
		return data;
	}
	
	public String [] getFieldTypes() {
		return types;
	}
	
	public String [] getFieldNames() {
		return names;
	}

}
