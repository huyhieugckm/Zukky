package testDb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.PreparedStatement;

public class DocFileDB2 {
	Monhoc mh = new Monhoc();
	GiaoVien gv = new GiaoVien();
	Lop l = new Lop();
	SinhVien sv= new SinhVien();	
	ConnectData con = new ConnectData();
	QlDiem qld = new QlDiem();
	public void Docfile2() throws FileNotFoundException {
		String csvFilePath = "./DB2/MonHoc.csv";
		String csvFilePath2 = "./DB2/SinhVien.csv";
		String csvFilePath3 = "./DB2/Lop.csv";
		String csvFilePath4 = "./DB2/SinhVienLop.csv";
		String sqlInsert = "INSERT INTO MonHoc "
                + " VALUE(?,?,?);";
		String sqlUpdate = "UPDATE SinhVien SET HoSV = ?, TenSV = ?, NgaySinh = ?, NoiSinh = ? WHERE MaSV = ?";
		String sqlInsert3 = "INSERT INTO Lop "
                + " VALUE(?,?,?,?,?);";
		String sqlInsert4 = "INSERT INTO SinhVienLop "
                + " VALUE(?,?,?);";
		try {//doc file mon hoc
			con.ConnectDb();
        PreparedStatement ps = con.conn.prepareStatement(sqlInsert);
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
        String lineText = null;

        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            mh.setMaMH(data[0]);
            mh.setTenMH(data[1]);
            mh.setSoTC(Integer.parseInt(data[2]));
            ps.setString(1, mh.getMaMH());
            ps.setString(2, mh.getTenMH());
            ps.setInt(3, mh.getSoTC());
            ps.execute();
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {// doc file sinh vien
		con.ConnectDb();
        PreparedStatement ps2 = con.conn.prepareStatement(sqlUpdate);
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath2));
        String lineText = null;

        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");           
            sv.setMaSV(data[0]);
            sv.setHoSV(data[1]);
            sv.setTenSV(data[2]);
            sv.setNgaySinh(data[3]);
            sv.setNoiSinh(data[4]);
            ps2.setString(1, sv.getHoSV());
            ps2.setString(2, sv.getTenSV());
            ps2.setString(3, sv.getNgaySinh());
            ps2.setString(4, sv.getNoiSinh()); 
            ps2.setString(5, sv.getMaSV());
            ps2.execute();
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {// doc file lop
		con.ConnectDb();
        PreparedStatement ps3 = con.conn.prepareStatement(sqlInsert3);
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath3));
        String lineText = null;

        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");           
            l.setMaLop(data[0]);
            mh.setMaMH(data[1]);
            l.setNamHoc(Integer.parseInt(data[2]));
            l.setHocKy(Integer.parseInt(data[3]));
            gv.setMaGV(data[4]);
            ps3.setString(1, l.getMaLop());
            ps3.setString(2, mh.getMaMH());
            ps3.setInt(3, l.getNamHoc());
            ps3.setInt(4, l.getHocKy());
            ps3.setString(5, gv.getMaGV());
            ps3.execute();
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {// doc file sinhvienlop
		con.ConnectDb();
        PreparedStatement ps4 = con.conn.prepareStatement(sqlInsert4);
        
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath4));
        String lineText = null;
        lineReader.readLine(); 
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");           
            sv.setMaSV(data[0]);
            l.setMaLop(data[1]);
            qld.setDiem(Double.parseDouble(data[2]));
            ps4.setString(1, sv.getMaSV());
            ps4.setString(2, l.getMaLop());
            ps4.setDouble(3, qld.getDiem());
            ps4.execute();
        }
        String sqlUPDATE1 = "UPDATE SinhVienLop SET Diem = ? WHERE MaSV = SV003 AND MaLop = L001;";
        PreparedStatement ps5 = con.conn.prepareStatement(sqlUPDATE1);
        ps5.setDouble(1, 9);
        ps5.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
   }
}
