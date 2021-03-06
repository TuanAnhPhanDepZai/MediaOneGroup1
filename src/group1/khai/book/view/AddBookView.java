package group1.khai.book.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import group1.khai.db.DBConnector;
import group1.khai.models.Book;
import group1.khai.models.Product;



@SuppressWarnings("serial")
public class AddBookView extends JDialog  implements ActionListener {

	
	private JLabel 		lbID, lbTenSP, lbSoLuong, lbGiaMua, lbGiaBan, lbNXB, lbTacGia;
	private JTextField 	tfID, tfTenSP, tfSoLuong, tfGiaMua, tfGiaBan, tfNXB, tfTacGia;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private DBConnector db;
	private TableBookPanel tableBookPanel;
	
	public AddBookView(DBConnector db, TableBookPanel tableBookPanel) {
		this.db = db;
		this.tableBookPanel=tableBookPanel;
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thêm Sách");

		lbID       	= new JLabel("ID");
		lbTenSP    	= new JLabel("Tên sách");
		lbSoLuong 	= new JLabel("Số lượng");
		lbGiaMua 	= new JLabel("Giá mua");
		lbGiaBan 	= new JLabel("Giá bán");
		lbNXB   	= new JLabel("Nhà XB");
		lbTacGia    = new JLabel("Tác giả");
		
		
		tfID       	= new JTextField(20);
		tfTenSP    	= new JTextField(20);
		tfSoLuong 	= new JTextField(20);
		tfGiaMua   	= new JTextField(20);
		tfGiaBan    = new JTextField(20);
		tfNXB   	= new JTextField(20);
		tfTacGia    = new JTextField(20);
		
		btnThem    = new JButton("Thêm");		btnThem.addActionListener(this);
		btnHuy     = new JButton("Hủy ");	btnHuy.addActionListener(this);
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(8, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(8, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbID);           p2.add(tfID);
		p1.add(lbTenSP);		p2.add(tfTenSP);
		p1.add(lbNXB);			p2.add(tfNXB);
		p1.add(lbTacGia);		p2.add(tfTacGia);
		p1.add(lbSoLuong);		p2.add(tfSoLuong);	
		p1.add(lbGiaMua);		p2.add(tfGiaMua);	 
		p1.add(lbGiaBan);		p2.add(tfGiaBan);	
		
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
		}
		if (e.getSource() == btnThem) {
			SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/DD/hh/mm/ss");
			
			if(checkFormat() == true){
			
				
				try {
					String id 		= tfID.getText();
					String tensp 	= tfTenSP.getText();
					int soluong 	= Integer.parseInt(tfSoLuong.getText());
					double giamua 	= Double.parseDouble(tfGiaMua.getText());
					double giaban 	= Double.parseDouble(tfGiaBan.getText());
					Date time = new Date();
					Timestamp buytime = new Timestamp(time.getYear(), time.getMonth(), time.getDate(), time.getHours(), time.getMinutes(), time.getSeconds(), 0);
					String nxb 		= tfNXB.getText();
					String tacgia = tfTacGia.getText();
					
					
					Book sach = new Book(id,tensp,Product.BOOK,soluong,giamua,giaban,buytime,nxb,tacgia,"");
					
					db.saveBook(sach);
					
					dispose();
					
					List<Book> list = db.getAllBooks();
					tableBookPanel.updateTable(list);
					JOptionPane.showMessageDialog(null, "Thêm sách thành công");
					
					 
				} catch (Exception e1) {
				
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
	

	
	private boolean checkFormat(){
		if(tfID.getText().length()!=8||tfID.getText().charAt(0)!='B'||tfID.getText().charAt(1)!='K') {
			JOptionPane.showMessageDialog(null, "ID phải có 8 kí tự bắt đầu bởi 'BK'", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if (db.findBook(tfID.getText())!=null) {
			JOptionPane.showMessageDialog(null, "ID sách '" + tfID.getText() + "' đã tồn tại!", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} 
	
		else if(tfTenSP.getText().equals(null) || tfTenSP.getText().equals("") ||
				tfSoLuong.getText().equals(null) || tfSoLuong.getText().equals("") ||
				tfNXB.getText().equals(null) || tfNXB.getText().equals("") ||
				tfTacGia.getText().equals(null) || tfTacGia.getText().equals("") ||
				tfID.getText().equals(null) || tfID.getText().equals("") ||
				tfGiaMua.getText().equals(null) || tfGiaMua.getText().equals("") ||
				tfGiaBan.getText().equals(null) || tfGiaBan.getText().equals("") )
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if( Long.parseLong(tfGiaMua.getText()) <0 ||
				 Long.parseLong(tfGiaMua.getText()) <0 ||
				 Integer.parseInt(tfSoLuong.getText()) < 0
				){
			
			JOptionPane.showMessageDialog(null, "Số lượng, giá mua và giá bán phải lớn hơn 0","Cảnh báo",0);
			return false;
		}
		return true;
	}
	

	
	

	

}
