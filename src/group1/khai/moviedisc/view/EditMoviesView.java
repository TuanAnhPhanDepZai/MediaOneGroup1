package group1.khai.moviedisc.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import group1.khai.models.MovieDisc;


public class EditMoviesView extends JDialog implements ActionListener {

	
	private JLabel 		 lbTenSP, lbSoLuong, lbGiaMua, lbGiaBan, lbDaoDien, lbDienVien;
	private JTextField 	 tfTenSP, tfSoLuong, tfGiaMua, tfGiaBan, tfDaoDien, tfDienVien;
	private JPanel p1, p2,p3;
	private JButton btnSua, btnHuy;
	private DBConnector db;
	private TableMoviesPanel tableMoviesPanel;
	private MovieDisc diaphim;
	public EditMoviesView(DBConnector db, TableMoviesPanel tableMoviesPanel,MovieDisc diaphim) {
		this.db = db;
		this.tableMoviesPanel=tableMoviesPanel;
		this.diaphim=diaphim;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Sửa thông tin đĩa phim");

	
		lbTenSP    	= new JLabel("Tên đĩa phim");
		lbSoLuong 	= new JLabel("Số lượng");
		lbGiaMua 	= new JLabel("Giá mua");
		lbGiaBan 	= new JLabel("Giá bán");

		lbDaoDien   	= new JLabel("Đạo diễn");
		lbDienVien    = new JLabel("Diễn viên");
		
		

		tfTenSP    	= new JTextField(20); tfTenSP.setText(diaphim.getProductName());
		tfSoLuong 	= new JTextField(20); tfSoLuong.setText(Long.toString(diaphim.getProductQuota()));
		tfGiaMua   	= new JTextField(20); tfGiaMua.setText(Double.toString(diaphim.getBuyPrice()));
		tfGiaBan    = new JTextField(20); tfGiaBan.setText(Double.toString(diaphim.getSellPrice()));
		tfDaoDien   	= new JTextField(20); tfDaoDien.setText(diaphim.getDirectorName());
		tfDienVien    = new JTextField(20); tfDienVien.setText(diaphim.getActorName());
		
		
		btnSua    = new JButton("Sửa");		btnSua.addActionListener(this);
		btnHuy     = new JButton("Hủy ");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(8, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(8, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbTenSP);		p2.add(tfTenSP);
		p1.add(lbDaoDien);			p2.add(tfDaoDien);
		p1.add(lbDienVien);		p2.add(tfDienVien);
		p1.add(lbSoLuong);		p2.add(tfSoLuong);	
		p1.add(lbGiaMua);		p2.add(tfGiaMua);	 
		p1.add(lbGiaBan);		p2.add(tfGiaBan);	

		
		
		p3.add(btnSua);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		

		
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnHuy) {
			this.dispose();
		}
		else if(e.getSource()==btnSua) {
			if(tfTenSP.getText().equals(null) || tfTenSP.getText().equals("") ||
					tfSoLuong.getText().equals(null) || tfSoLuong.getText().equals("") ||
					tfDaoDien.getText().equals(null) || tfDaoDien.getText().equals("") ||
					tfDienVien.getText().equals(null) || tfDaoDien.getText().equals("") ||
					tfGiaMua.getText().equals(null) || tfGiaMua.getText().equals("") ||
					tfGiaBan.getText().equals(null) || tfGiaBan.getText().equals("") )
			{
				JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			}
			else if(diaphim.getProductName().equals(tfTenSP.getText())&&diaphim.getProductQuota()==Long.parseLong(tfSoLuong.getText())
					&&diaphim.getBuyPrice()==Double.parseDouble(tfGiaMua.getText())
					&&diaphim.getSellPrice()==Double.parseDouble(tfGiaBan.getText())
					&&diaphim.getActorName().equals(tfDienVien.getText())
					&&diaphim.getDirectorName().equals(tfDaoDien.getText())) {
				JOptionPane.showMessageDialog(null, "Chưa thay đổi");
			}
			else {
				diaphim.setProductName(tfTenSP.getText());
				diaphim.setProductQuota(Long.parseLong(tfSoLuong.getText()));
				diaphim.setBuyPrice(Double.parseDouble(tfGiaMua.getText()));
				diaphim.setSellPrice(Double.parseDouble(tfGiaBan.getText()));
				diaphim.setActorName(tfDaoDien.getText());
				diaphim.setDirectorName(tfDaoDien.getText());
				db.updateMovieDisc(diaphim);
				this.dispose();
				List<MovieDisc> list = db.getAllMovieDiscs();
				tableMoviesPanel.updateTable(list);
			}
		}
		
	}

	
}