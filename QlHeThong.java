package testDb;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class QlHeThong {
	ConnectData con = new ConnectData();
	public void MenuQL() {
		Scanner sc = new Scanner(System.in);
		int f;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Delete CSDL.");
			System.out.println("2. Backup.");
			System.out.println("3. Restore.");
			System.out.println("4. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			f = sc.nextInt();
			switch(f) {
			case 1: DelData();
					break;
			case 2: BackupDB();
					break;
			case 3: ResDB();
					break;
			case 4: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 4.");
			}
		}while(flag);
	}
	public void DelData() {
		con.ConnectDb();
		try {
			PreparedStatement ps = con.conn.prepareStatement("DELETE FROM SinhVienLop;");
			PreparedStatement ps1 = con.conn.prepareStatement("DELETE FROM Lop;");
			PreparedStatement ps2 = con.conn.prepareStatement("DELETE FROM SinhVien;");
			PreparedStatement ps3 = con.conn.prepareStatement("DELETE FROM GiaoVien;");
			PreparedStatement ps4 = con.conn.prepareStatement("DELETE FROM MonHoc;");
			ps.execute();
			ps1.execute();
			ps2.execute();
			ps3.execute();
			ps4.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void BackupDB() {
		String executeCmd;
		executeCmd = "mysqldump -u "+con.getUserName()+" -p"+con.getPassword()+" "+con.getDbName()
					+" -r backup.sql";
		try {
			Process	runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if(processComplete == 0) {
				System.out.println("Backup taken successfully.");
			}
			else {
				System.out.println("Could not take mysql backup");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ResDB() {
		String[] executeCmd;
		executeCmd = new String[]{"/bin/sh", "-c", "mysql -u "+con.getUserName()+" -p"+con.getPassword()+" "+con.getDbName()
					+" < backup.sql"};
		try {
			Process	runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if(processComplete == 0) {
				System.out.println("success.");
			}
			else {
				System.out.println("Restore failure");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
