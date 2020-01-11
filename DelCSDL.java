package testDb;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DelCSDL {
	ConnectData con = new ConnectData();
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
}
