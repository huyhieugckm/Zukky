package testDb;

import java.io.IOException;
import java.util.Scanner;

public class QlHeThong {
	ConnectData con = new ConnectData();
	public void MenuQL() {
		Scanner sc = new Scanner(System.in);
		int f;
		boolean flag = true;
		do {
			System.out.println("=========================");
			System.out.println("1. Backup.");
			System.out.println("2. Restore.");
			System.out.println("3. Thoat");
			System.out.println("=========================");
			System.out.print("Hay nhap lua chon: ");
			f = sc.nextInt();
			switch(f) {
			case 1: BackupDB();
					break;
			case 2: ResDB();
					break;
			case 3: flag = false;
					break;
			default: System.out.println("Chi duoc chon tu 1 den 3.");
			}
		}while(flag);
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
