package nhom1.Khai.View.Book;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import nhom1.Khai.db.DBConnector;



@SuppressWarnings("serial")
public class AddBookView extends JDialog   {

	
	private JLabel 		lbID, lbTenSP, lbSoLuong, lbGiaMua, lbGiaBan, lbNgayNhapCuoi, lbNXB, lbTacGia;
	private JTextField 	tfID, tfTenSP, tfSoLuong, tfGiaMua, tfGiaBan, tfNgayNhapCuoi, tfNXB, tfTacGia;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private DBConnector db;
	private TableBookPanel tableBookPanel;
	
	public AddBookView(DBConnector db, TableBookPanel tableBookPanel) {
		this.db = db;
		this.tableBookPanel=tableBookPanel;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thêm nhân viên");

		lbID       	= new JLabel("ID");
		lbTenSP    	= new JLabel("Tên sách");
		lbSoLuong 	= new JLabel("Số lượng");
		lbGiaMua 	= new JLabel("Giá mua");
		lbGiaBan 	= new JLabel("Giá bán");
		lbNgayNhapCuoi = new JLabel("Ngày nhập");
		lbNXB   	= new JLabel("Nhà XB");
		lbTacGia    = new JLabel("Tác giả");
		
		
		tfID       	= new JTextField(20);
		tfTenSP    	= new JTextField(20);
		tfSoLuong 	= new JTextField(20);
		tfGiaMua   	= new JTextField(20);
		tfGiaBan    = new JTextField(20);
		tfNgayNhapCuoi 	= new JTextField(20);
		tfNXB   	= new JTextField(20);
		tfTacGia    = new JTextField(20);
		
		btnThem    = new JButton("Thêm");		
		btnHuy     = new JButton("Hủy ");	
		
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
		p1.add(lbNgayNhapCuoi);	p2.add(tfNgayNhapCuoi);
		
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	

	
	

	

}
