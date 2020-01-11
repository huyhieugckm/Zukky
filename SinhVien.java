package testDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SinhVien {
	private String MaSV;
	public String getMaSV() {
		return MaSV;
	}
	public void setMaSV(String maSV) {
		MaSV = maSV;
	}
	private String HoSV;
	private String TenSV;
	private String NgaySinh;
	private String NoiSinh; 
	public String getHoSV() {
		return HoSV;
	}
	public void setHoSV(String hoSV) {
		HoSV = hoSV;
	}
	public String getTenSV() {
		return TenSV;
	}
	public void setTenSV(String tenSV) {
		TenSV = tenSV;
	}
	public String getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getNoiSinh() {
		return NoiSinh;
	}
	public void setNoiSinh(String noiSinh) {
		NoiSinh = noiSinh;
	}
	ConnectData con = new ConnectData();
	public void MenuSinhVien(){
		Scanner sc = new Scanner(System.in);
		int d;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Them ho so sinh vien.");
			System.out.println("2. Sua thong tin sinh vien.");
			System.out.println("3. Tim kiem sinh vien.");
			System.out.println("4. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			d = sc.nextInt();
			switch(d) {
			case 1: AddSV();
					break;
			case 2: EditSV();
					break;
			case 3: FindSV();
					break;
			case 4: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 4.");
			}
		}while(flag);
	}
	public void AddSV() {
		Scanner sc = new Scanner(System.in);
		String sqlInsert = "INSERT INTO SinhVien "
                + " VALUE(?,?,?,?,?);";
				System.out.print("Nhap Ma Sinh Vien: ");
				MaSV=sc.nextLine();
				System.out.print("Nhap Ho Sinh Vien: ");
				HoSV=sc.nextLine();
				System.out.print("Nhap Ten Sinh Vien: ");
				TenSV = sc.nextLine();
				System.out.print("Nhap Ngay Sinh: ");
				NgaySinh = sc.nextLine();
				System.out.print("Nhap Noi Sinh: ");
				NoiSinh = sc.nextLine();
			try {
				con.ConnectDb();
				PreparedStatement ps = con.conn.prepareStatement(sqlInsert);
				ps.setString(1,MaSV);
				ps.setString(2, HoSV);
				ps.setString(3, TenSV);
				ps.setString(4, NgaySinh);
				ps.setString(5, NoiSinh);
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			con.CloseDb();
	}
	public void EditSV() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Sinh Vien Muon Sua: ");
		MaSV = sc.nextLine();
		System.out.print("Nhap Ho Sinh Vien Moi: ");
		HoSV=sc.nextLine();
		System.out.print("Nhap Ten Tem Vien Moi: ");
		TenSV = sc.nextLine();
		System.out.print("Nhap Ngay Sinh Moi: ");
		NgaySinh = sc.nextLine();
		System.out.print("Nhap Noi Sinh Moi: ");
		NoiSinh = sc.nextLine();
		String sqlUpdate = "UPDATE SinhVien SET HoSV = ?, TenSV = ?, NgaySinh = ?, NoiSinh = ? WHERE MaSV = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlUpdate);
			ps.setString(1,	HoSV);
			ps.setString(2, TenSV);
			ps.setString(3, NgaySinh);
			ps.setString(4, NoiSinh);
			ps.setString(5, MaSV);
			ps.executeUpdate();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void FindSV() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Sinh Vien Muon Tim: ");
		MaSV = sc.nextLine();
		String sqlFind ="SELECT MaSV FROM SinhVien WHERE IN MaSV = ?;";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlFind);
			ps.setString(1,MaSV);
			String sqlOut ="SELECT * FROM SinhVien WHERE MaSV = ?;";
			PreparedStatement ps1 = con.conn.prepareStatement(sqlOut);
			ps1.setString(1,MaSV);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				System.out.println("MaSV: "+rs.getString(1)
									+"\tHoSV: "+rs.getString(2)
									+"\tTenSV: "+rs.getString(3)
									+"\t\tNgay Sinh: "+rs.getString(4)
									+"\tNoi Sinh :"+rs.getString(5));
			}
		}catch(Exception e) {}
		con.CloseDb();
	}
}
