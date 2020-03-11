package testDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Monhoc {
	private String MaMH;
	private String TenMH;
	private int SoTC;
	ConnectData con = new ConnectData();
	public String getTenMH() {
		return TenMH;
	}
	public void setTenMH(String tenMH) {
		TenMH = tenMH;
	}
	public int getSoTC() {
		return SoTC;
	}
	public void setSoTC(int soTC) {
		SoTC = soTC;
	}
	public String getMaMH() {
		return MaMH;
	}
	public void setMaMH(String maMH) {
		MaMH = maMH;
	}
	public void MenuMonhoc() {
		Scanner sc = new Scanner(System.in);
		int b;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Them mon hoc moi.");
			System.out.println("2. Sua thong tin mon hoc.");
			System.out.println("3. In danh sach mon hoc.");
			System.out.println("4. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			b = sc.nextInt();
			switch(b) {
			case 1: AddMH();
					break;
			case 2: EditMH();
					break;
			case 3: OutpMH();
					break;
			case 4: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 4.");
			}
		}while(flag);
	}
	public void AddMH () {
		Scanner sc = new Scanner(System.in);
		String sqlInsert = "INSERT INTO MonHoc "
                + " VALUE(?,?,?);";
				System.out.print("Nhap Ma Mon Hoc: ");
				MaMH=sc.nextLine();
				System.out.print("Nhap Ten Mon Hoc: ");
				TenMH=sc.nextLine();
				do {
					System.out.print("Nhap So TC: ");
					SoTC=sc.nextInt();
					if(SoTC<0) {
						System.out.println("So TC phai lon hon 0");
					}
				}while(SoTC<0);
			try {
				con.ConnectDb();
				PreparedStatement ps = con.conn.prepareStatement(sqlInsert);
				ps.setString(1,MaMH);
				ps.setString(2, TenMH);
				ps.setInt(3, SoTC);
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			con.CloseDb();
	}
	public void EditMH() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Mon Hoc Muon Sua: ");
		MaMH = sc.nextLine();
		System.out.print("Nhap Ten Mon Hoc Moi: ");
		TenMH = sc.nextLine();
		do {
			System.out.print("Nhap So TC Moi: ");
			SoTC = sc.nextInt();
			if(SoTC<0) {
				System.out.println("So TC phai lon hon 0");
			}
		}while(SoTC<0);
		String sqlUpdate = "UPDATE MonHoc SET TenMH = ?, SoTC = ? WHERE MaMH = ?;";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlUpdate);
			ps.setString(1, TenMH);
			ps.setInt(2, SoTC);
			ps.setString(3, MaMH);
			ps.executeUpdate();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void OutpMH() {
		try {
			con.ConnectDb();
			String sqlOut ="SELECT * FROM MonHoc;";
			PreparedStatement ps = con.conn.prepareStatement(sqlOut);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("MaMH: "+rs.getString(1)+"\tTenMH: "
									+rs.getString(2)+"\tSoTC: "
									+rs.getInt(3));
			}	
		}catch(Exception e) {}
		con.CloseDb();
	}
}
