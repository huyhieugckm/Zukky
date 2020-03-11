package testDb;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static void menu () {
		System.out.println("=================================");
		System.out.println("1. Quan ly mon hoc.");
		System.out.println("2. Quan ly giao vien.");
		System.out.println("3. Quan ly sinh vien.");
		System.out.println("4. Quan ly lop.");
		System.out.println("5. Quan ly diem.");
		System.out.println("6. Quan tri he thong.");
		System.out.println("7. Them CSDL");
		System.out.println("8. Them CSDL(DB2)");
		System.out.println("9. In danh sach 3 sinh vien");
		System.out.println("10. Thoat.");
		System.out.println("==================================");
		System.out.print("Hay nhap lua chon: ");
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int a;
		do {
			menu();
			a=sc.nextInt();
			switch(a) {
			case 1: Monhoc m = new Monhoc();
					m.MenuMonhoc();
					break;
			case 2: GiaoVien g = new GiaoVien();	
					g.MenuGiaoVien();
					break;
			case 3: SinhVien s = new SinhVien();
					s.MenuSinhVien();
					break;
			case 4: Lop l = new Lop();
					l.MenuLop();
					break;
			case 5: QlDiem d = new QlDiem();
					d.MenuDiem();
					break;
			case 6: QlHeThong ql = new QlHeThong();
					ql.MenuQL();
					break;
			case 7: RFile rf = new RFile();
				try {
					rf.Docfile();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
			case 8: DocFileDB2 df = new DocFileDB2();
				try {
					df.Docfile2();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
			case 9: 
					break;
			case 10: flag = false;	
					break;
			default: System.out.println("Chi duoc chon tu 1 den 10");
			}
		}while (flag);
	}
}
