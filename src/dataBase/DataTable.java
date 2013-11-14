package dataBase;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DataTable implements TableModel {
	private final String[] acceptedTypes = {"INT", "STRING", "TEXT"};
	private String table;

	
	public DataTable (String table) {
		this.table = table;
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		
		
	}

	@Override
	public Class<?> getColumnClass(int colIndex) {
		
		return null;
	}

	@Override
	public int getColumnCount() {
		
		return 0;
	}

	@Override
	public String getColumnName(int colIndex) {
		
		return null;
	}

	@Override
	public int getRowCount() {
		
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		return null;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		
		
	}

}
