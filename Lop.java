package testDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Lop {
	private String MaLop;
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	private int NamHoc;
	private int HocKy;
	public int getNamHoc() {
		return NamHoc;
	}
	public void setNamHoc(int namHoc) {
		NamHoc = namHoc;
	}
	public int getHocKy() {
		return HocKy;
	}
	public void setHocKy(int hocKy) {
		HocKy = hocKy;
	}
	GiaoVien gv = new GiaoVien();
	Monhoc mh = new Monhoc();
	ConnectData con = new ConnectData();
	SinhVien sv= new SinhVien();
	public void MenuLop(){
		Scanner sc = new Scanner(System.in);
		int e;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Tao lop moi.");
			System.out.println("2. Sua thong tin lop.");
			System.out.println("3. Bo sung sinh vien vao lop.");
			System.out.println("4. Loai bo sinh vien khoi lop.");
			System.out.println("5. Huy lop");
			System.out.println("6. In danh sach lop.");
			System.out.println("7. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			e = sc.nextInt();
			switch(e) {
			case 1: AddLop();
					break;
			case 2: EditLop();
					break;
			case 3: AddSvLop();
					break;
			case 4: ReSv();
					break;
			case 5: DelLop();
					break;
			case 6: OutpLop();
					break;
			case 7: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 7.");
			}
		}while(flag);
	}
	public void AddLop() {
		Scanner sc = new Scanner(System.in);
		String sqlInsert = "INSERT INTO Lop  "
                + " VALUE(?,?,?,?,?);";
				System.out.print("Nhap Ma Lop: ");
				MaLop=sc.nextLine();
				System.out.print("Nhap Ma Mon Hoc: ");
				mh.setMaMH(sc.nextLine());
				System.out.print("Nhap Nam Hoc: ");
				NamHoc = sc.nextInt();
				System.out.print("Nhap Hoc Ky: ");
				HocKy = sc.nextInt();
				sc.nextLine();
				System.out.print("Nhap Ma Giao Vien: ");
				gv.setMaGV(sc.nextLine());
				try {
					con.ConnectDb();
					PreparedStatement ps = con.conn.prepareStatement(sqlInsert);
					ps.setString(1,MaLop);
					ps.setString(2, mh.getMaMH());
					ps.setInt(3, NamHoc);
					ps.setInt(4, HocKy);
					ps.setString(5, gv.getMaGV());
					ps.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				con.CloseDb();
	}
	public void EditLop() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Lop Muon Sua: ");
		MaLop = sc.nextLine();
		System.out.print("Nhap Nam Hoc Moi: ");
		NamHoc=sc.nextInt();
		System.out.print("Nhap Hoc Ky Moi: ");
		HocKy = sc.nextInt();
		sc.nextLine();
		System.out.print("Nhap Ma Giao Vien Moi: ");
		gv.setMaGV(sc.nextLine());
		String sqlUpdate = "UPDATE Lop SET NamHoc = ?, HocKy = ?, MaGV = ? WHERE MaLop = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlUpdate);
			ps.setInt(1, NamHoc);
			ps.setInt(2, HocKy);
			ps.setString(3, gv.getMaGV());
			ps.setString(4, MaLop);
			ps.executeUpdate();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void AddSvLop() {
		Scanner sc = new Scanner(System.in);
		int n;
		System.out.print("Nhap ma lop: ");
		MaLop = sc.nextLine();
		System.out.print("Muon them bao nhieu sinh vien?\t");
		n = sc.nextInt();
		sc.nextLine();
		String sqlInsert = "INSERT INTO SinhVienLop (MaSV, MaLop) "
                + " VALUE(?,?);";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlInsert);
			for (int i = 0; i<n; i++) {
				System.out.print("Nhap MaSv thu "+(i+1)+": ");
				sv.setMaSV(sc.nextLine());
				ps.setString(1, sv.getMaSV());
				ps.setString(2, MaLop);
				ps.executeUpdate();
			}
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void ReSv() {
		Scanner sc = new Scanner (System.in);
		System.out.print("Nhap Ma Lop: ");
		MaLop = sc.nextLine();
		System.out.print("Nhap Ma Sinh Vien muon xoa: ");
		sv.setMaSV(sc.nextLine());
		String sqlRemove = "DELETE FROM SinhVienLop WHERE MaSV = ? AND MaLop = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlRemove);
			ps.setString(1, sv.getMaSV());
			ps.setString(2, MaLop);
			ps.execute();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void DelLop() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma lop muon xoa: ");
		MaLop = sc.nextLine();
		String sqlDel = "DELETE FROM SinhVienLop WHERE MaLop = ?";
		String sqlDel2 = "DELETE FROM Lop WHERE MaLop = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlDel);
			ps.setString(1, MaLop);
			PreparedStatement ps1 = con.conn.prepareStatement(sqlDel2);
			ps1.setString(1, MaLop);
			ps.execute();
			ps1.execute();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void OutpLop() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma lop: ");
		MaLop = sc.nextLine();
		String sqlOut = "SELECT SinhVienLop.MaLop, SinhVienLop.MaSv, "+
						"SinhVien.HoSV, SinhVien.TenSV FROM SinhVienLop, SinhVien "+
						"WHERE SinhVienLop.MaSV = SinhVien.MaSV AND SinhVienLop.MaLop = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlOut);
			ps.setString(1, MaLop);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Ma Lop: "+rs.getString(1)
									+"\tMaSV: "+rs.getString(2)
									+"\tHo SV: "+rs.getString(3)
									+"\tTen Sv: "+rs.getString(4));
			}	
		} catch (Exception e) {}
		con.CloseDb();
	}
}
