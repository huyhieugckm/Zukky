package testDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class GiaoVien {
	private String MaGV;
	private String HoGV;
	public String getHoGV() {
		return HoGV;
	}
	public void setHoGV(String hoGV) {
		HoGV = hoGV;
	}
	public String getTenGV() {
		return TenGV;
	}
	public void setTenGV(String tenGV) {
		TenGV = tenGV;
	}
	public String getDonVi() {
		return DonVi;
	}
	public void setDonVi(String donVi) {
		DonVi = donVi;
	}
	ConnectData con = new ConnectData();
	public String getMaGV() {
		return MaGV;
	}
	public void setMaGV(String maGV) {
		MaGV = maGV;
	}
	private String TenGV;
	private String DonVi;
	public void MenuGiaoVien(){
		Scanner sc = new Scanner(System.in);
		int c;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Them ho so giao vien.");
			System.out.println("2. Sua thong tin giao vien.");
			System.out.println("3. In danh sach giao vien.");
			System.out.println("4. Tim kiem giao vien.");
			System.out.println("5. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			c = sc.nextInt();
			switch(c) {
			case 1: AddGV();
					break;
			case 2: EditGV();
					break;
			case 3: OutpGV();
					break;
			case 4: FindGV();
					break;
			case 5: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 5.");
			}
		}while(flag);
	}
	public void AddGV() {
		Scanner sc = new Scanner(System.in);
		String sqlInsert = "INSERT INTO GiaoVien "
                + " VALUE(?,?,?,?);";
				System.out.print("Nhap Ma Giao Vien: ");
				MaGV=sc.nextLine();
				System.out.print("Nhap Ho Giao Vien: ");
				HoGV=sc.nextLine();
				System.out.print("Nhap Ten Giao Vien: ");
				TenGV = sc.nextLine();
				System.out.print("Nhap Don Vi Cong Tac: ");
				DonVi = sc.nextLine();
			try {
				con.ConnectDb();
				PreparedStatement ps = con.conn.prepareStatement(sqlInsert);
				ps.setString(1,MaGV);
				ps.setString(2, HoGV);
				ps.setString(3, TenGV);
				ps.setString(4, DonVi);
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			con.CloseDb();
	}
	public void EditGV() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Giao Vien Muon Sua: ");
		MaGV = sc.nextLine();
		System.out.print("Nhap Ho Giao Vien Moi: ");
		HoGV=sc.nextLine();
		System.out.print("Nhap Ten Giao Vien Moi: ");
		TenGV = sc.nextLine();
		System.out.print("Nhap Don Vi Cong Tac Moi: ");
		DonVi = sc.nextLine();
		String sqlUpdate = "UPDATE GiaoVien SET HoGV = ?, TenGV = ?, DonVi = ? WHERE MaGV = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlUpdate);
			ps.setString(1,	HoGV);
			ps.setString(2, TenGV);
			ps.setString(3, DonVi);
			ps.setString(4, MaGV);
			ps.executeUpdate();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void OutpGV() {
		try {
			con.ConnectDb();
			String sqlOut ="SELECT * FROM GiaoVien;";
			PreparedStatement ps = con.conn.prepareStatement(sqlOut);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("MaGV: "+rs.getString(1)
									+"\tHoGV: "+rs.getString(2)
									+"\tTenGV: "+rs.getString(3)
									+"\t\tDonVi: "+rs.getString(4));
			}	
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void FindGV() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Giao Vien Muon Tim: ");
		MaGV = sc.nextLine();
		String sqlFind ="SELECT MaGV FROM GiaoVien WHERE IN MaGV = ?;";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlFind);
			ps.setString(1,MaGV);
			String sqlOut ="SELECT * FROM GiaoVien WHERE MaGV = ?;";
			PreparedStatement ps1 = con.conn.prepareStatement(sqlOut);
			ps1.setString(1,MaGV);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				System.out.println("MaGV: "+rs.getString(1)
									+"\tHoGV: "+rs.getString(2)
									+"\tTenGV: "+rs.getString(3)
									+"\t\tDonVi: "+rs.getString(4));
			}
		}catch(Exception e) {}
		con.CloseDb();
	}
}
