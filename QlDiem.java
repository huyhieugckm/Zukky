package testDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QlDiem {
	ConnectData con = new ConnectData();
	private double Diem;
	public double getDiem() {
		return Diem;
	}
	public void setDiem(double diem) {
		Diem = diem;
	}
	Lop l = new Lop();
	SinhVien sv = new SinhVien();
	public void MenuDiem() {
		Scanner sc = new Scanner(System.in);
		int f;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Them diem.");
			System.out.println("2. Sua diem.");
			System.out.println("3. In bang diem cho lop.");
			System.out.println("4. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			f = sc.nextInt();
			switch(f) {
			case 1: AddDiem();
					break;
			case 2: EditDiem();
					break;
			case 3: OutDiem1();
					break;
			case 4: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 4.");
			}
		}while(flag);
	}
	public void AddDiem() {
		Scanner sc = new Scanner(System.in);
		try {
			con.ConnectDb();
			System.out.print("Nhap ma lop: ");
			l.setMaLop(sc.nextLine());
			PreparedStatement ps = con.conn.prepareStatement("SELECT DIEM FROM SinhVienLop WHERE "
					+ "MaLop = ?;");
			String sqlUPDATE = "UPDATE SinhVienLop SET Diem = ? WHERE MaLop = ? AND MaSV = ?";
			ps.setString(1, l.getMaLop());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getDouble(1)== 0) {
					System.out.print("Nhap MaSV: ");
					sv.setMaSV(sc.nextLine());
					System.out.print("Nhap diem: ");
					Diem = sc.nextDouble();
					sc.nextLine();
					PreparedStatement ps1 = con.conn.prepareStatement(sqlUPDATE);
					ps1.setDouble(1, Diem);
					ps1.setString(2, l.getMaLop());
					ps1.setString(3, sv.getMaSV());
					ps1.execute();
				}
			}	
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void EditDiem() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap Ma Lop: ");
		l.setMaLop(sc.nextLine());
		System.out.print("Nhap Ma SV: ");
		sv.setMaSV(sc.nextLine());
		System.out.print("Nhap Diem Moi: ");
		Diem = sc.nextDouble();
		sc.nextLine();
		String sqlUPDATE = "UPDATE SinhVienLop SET Diem = ? WHERE MaLop = ? AND MaSV = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlUPDATE);
			ps.setDouble(1, Diem);
			ps.setString(2, l.getMaLop());
			ps.setString(3, sv.getMaSV());
			ps.executeUpdate();
		}catch(Exception e) {}
		con.CloseDb();
	}
	public void OutDiem1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma lop: ");
		l.setMaLop(sc.nextLine());
		String sqlOut = "SELECT SinhVienLop.MaLop, SinhVienLop.MaSv, "+
						"SinhVien.HoSV, SinhVien.TenSV, SinhVienLop.Diem FROM SinhVienLop, SinhVien "+
						"WHERE SinhVienLop.MaSV = SinhVien.MaSV AND SinhVienLop.MaLop = ?";
		try {
			con.ConnectDb();
			PreparedStatement ps = con.conn.prepareStatement(sqlOut);
			ps.setString(1, l.getMaLop());
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Ma Lop: "+rs.getString(1)
									+"\tMaSV: "+rs.getString(2)
									+"\tHo SV: "+rs.getString(3)
									+"\tTen Sv: "+rs.getString(4)
									+"\tDiem: "+rs.getString(5));
			}	
		} catch (Exception e) {}
		con.CloseDb();
	}
}
