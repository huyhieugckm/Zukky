package testDb;

import java.util.Scanner;

public class Indanhsach {
	ConnectData con = new ConnectData();
	Lop l = new Lop();
	public void Prin() {
		try {
			Scanner sc = new Scanner(System.in);
			con.ConnectDb();
			System.out.print("Nhap nam hoc: ");
			l.setNamHoc(sc.nextInt());
			System.out.print("Nhap hoc ky: ");
			l.setHocKy(sc.nextInt());
		}
		catch(Exception e) {
			
		}
	}
}
