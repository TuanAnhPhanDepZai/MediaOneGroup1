package group1.khai.customer.view;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import group1.khai.models.Customer;



@SuppressWarnings("serial")
public class TableCusPanel extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private JLabel lbName;
	private String[] columns = { "ID", "Họ tên", "Số điện thoại", "Point" };

	public TableCusPanel() {
		setLayout(new BorderLayout(10, 0));
		// setBorder(BorderFactory.createEtcheBorder(EtchedBorder.RAISED));
		// setBorder(BorderFactory.createEtchedBorder());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// create table
		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		loadData(table);

		// add tabel to scroll
		scroll = new JScrollPane();
		// scroll.setPreferredSize();
		scroll.setViewportView(table);
		add(scroll, BorderLayout.CENTER);
		add(namePanel(),BorderLayout.NORTH);
	}

//	public JPanel createTablePanel() {
//	
//		panel.add(scroll, BorderLayout.CENTER);
//		return panel;
//
//	}
	private JPanel namePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		//create label
				lbName = new JLabel("DANH SÁCH KHÁCH HÀNG");
				lbName.setHorizontalAlignment(JLabel.CENTER);
			panel.add(lbName,BorderLayout.CENTER);
			
			return panel;
	}
	private void loadData(JTable table) {
		String[][] data = null;

		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(tableModel);

	}

	public void updateTable(List<Customer> list){
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		String[][] data =  new String[list.size()][columns.length];
		for(int i=0;i<list.size();i++){
			Customer nv = list.get(i);
			data[i][0] = nv.getID();
			data[i][1] =nv.getFullName();
			data[i][2] =nv.getTelephoneNumber();
			data[i][3] =format.format(nv.getPoint()).toString();
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

}
