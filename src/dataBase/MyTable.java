package dataBase;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class MyTable extends JTable {

public class MyButtonRenderer extends JButton implements TableCellRenderer, TableCellEditor {
    private int selectedRow;
    private int selectedColumn;
    private final List<MyListener> listener = new ArrayList<MyListener>();

    public MyButtonRenderer() {
        super();
        addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (MyListener l : listener) {
                    //l.tableButtonClicked(selectedRow, selectedColumn);
                    //сообщаем всем слушател€м о событии
                }
			}
        });
    }

    
    public Component getTableCellRendererComponent(JTable a, Object b, boolean c, boolean d, int e, int f) {
        setFocusable(false);
        return this;
    }

    public boolean stopCellEditing() {
    	return true;
    }
    
    public Object getCellEditorValue() {	
    	return null;
    }
    
    public boolean isCellEditable(EventObject anEvent) {	
    	return true;
    }
    
    public boolean shouldSelectCell(EventObject anEvent) {
    	return true;
    }

    public Component getTableCellEditorComponent(JTable a, Object b, boolean c, int d, int e) {
        //selectedRow = row;
        //selectedColumn = column;
        setFocusable(false);
        return this;
    }

    public void addTableButtonListener(MyListener l) {
    	listener.add(l);
    }
    
    public void removeTableButtonListener(MyListener l) {
    	listener.remove(l);
    }


	@Override
	public void addCellEditorListener(CellEditorListener l) {
		//listener.add(l);
	}


	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}
	
	}


	public MyTable(){
	        MyButtonRenderer button = new MyButtonRenderer();
	        button.addTableButtonListener(new MyListener() {
	        	
	        });
	
	//«адаем рендер "кнопка"дл€ первой колонки таблицы. ¬ажно сделать 2 разных рендера дл€ просмотра и редактировани€
	        this.getColumnModel().getColumn(0).setCellRenderer(new MyButtonRenderer());
	        this.getColumnModel().getColumn(0).setCellEditor(button);
	}

    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer result = super.getCellRenderer(row, column);
        if (result instanceof MyButtonRenderer)
            return result;
        return result;
    }
}