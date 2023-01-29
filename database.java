import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class database {

	private String sql;
	static Connection conn;
	static Statement stmt = null;
	static ResultSet rs = null;

	public String logincheck(int getstate, String getid, String getpw) throws SQLException {
		int state;
		String id;
		String pw;
		String result;
		String type;
//-------------------
		result = "";
		state = getstate;
		id = getid;
		pw = getpw;
		type = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			if (state == 0) {
				sql = "Select * from user where userid = '" + id + "' and password = '" + pw + "'";
				System.out.println(sql);
			} else if (state == 1) {
				sql = "Select * from student where studentID = '" + id + "' and password = '" + pw + "'";
				System.out.println(sql);
			}
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				System.out.println("있음");
				type = rs.getString("type");
				if (type.equals("java") || type.equals("excel") || type.equals("word") || type.equals("ppt")) {
					result = type;
				} else {
					type = rs.getString("id");
					result = type;
				}
			} else {
				System.out.println("없음");
				result = "no";
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			result = "db";
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
			result = "db";
		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(result);
		return result;
	}

	public void SelectMystudent(String gettype, Student_select getss) throws SQLException {
		Student_select ss = getss;
		String type = gettype;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "select * from student,study where student.id = study.studentid and study." + type + " = 1;";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("studentID");
				String name = rs.getString("name");
				String stype = rs.getString("type");
				String sin = rs.getString("sin");
				String[] data = { id, name, stype, sin };
				ss.model.addRow(data);
				System.out.println(name);
			}
			ss.tb.setModel(ss.model);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void SelectMystudentID(String gettype, JComboBox<String> getcm_id) throws SQLException {
		JComboBox<String> cmid = getcm_id;
		String type = gettype;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "select student.studentID from student,study where student.id = study.studentid and study." + type + " = 1;";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("studentID");
				cmid.addItem(id);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void AllSelectMystudent(All_Student_select getass) throws SQLException {
		All_Student_select ass = getass;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "select * from student;";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("studentID");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String stype = rs.getString("type");
				String sin = rs.getString("sin");
				String[] data = { id, name, sex, stype, sin };
				ass.model.addRow(data);
			}
			ass.tb.setModel(ass.model);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean UpdatePoint(String getpoint, String getid, String gettype) throws SQLException {

		String point = getpoint;
		String id = getid;
		String type = gettype;

		Connection conn = null;
		Statement stmt = null;
		Boolean rs, result = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "update result set " + type + " = " + point
					+ " where studentID = (select ID from student where studentID = '" + id + "');";
			rs = stmt.execute(sql);

			if (!rs) {
				result = true;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	public boolean selectsign(String getid) throws SQLException {

		boolean result = false;
		String id = getid;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "select * from study where studentid = " + id + ";";
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	public boolean signup(String getid, boolean getch_1, boolean getch_2, boolean getch_3, boolean getch_4,
			String getword, String getjava, String getexcel, String getppt) throws SQLException {

		String id = getid;
		boolean ch_1 = getch_1, ch_2 = getch_2, ch_3 = getch_3, ch_4 = getch_4;
		String word = getword, java = getjava, excel = getexcel, ppt = getppt;
		int a[] = { 1, 1, 1, 1 };
		Connection conn = null;
		Statement stmt = null;
		boolean rs, result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();
			if (word.equals("신청안함")) {
				a[0] = 0;
			}
			if (java.equals("신청안함")) {
				a[1] = 0;
			}
			if (excel.equals("신청안함")) {
				a[2] = 0;
			}
			if (ppt.equals("신청안함")) {
				a[3] = 0;
			}

			for (int i=0; i<4;i++) {
				System.out.println(a[i]+", ");
			}
			sql = "insert into study (word, java, excel, ppt, studentID) values(" + a[0] + "," + a[1] + "," + a[2] + ","
					+ a[3] + ",'" + id + "')";
			rs = stmt.execute(sql);
			if (!rs) {
				sql = "insert into slimt (word, java, excel, ppt, studentID) values('" + word + "','" + java + "','"
						+ excel + "','" + ppt + "','" + id + "')";
				rs = stmt.execute(sql);
				if (!rs) {
					sql = "insert into result (word, java, excel, ppt, studentID) values(0,0,0,0,'" + id + "')";
					rs = stmt.execute(sql);
					if (!rs) {
						result = true;
						System.out.println("등록 성공");
					} else {
						result = false;
						System.out.println("결과 실패");
					}
				} else {
					result = false;
					System.out.println("리스트 실패");
				}
			} else {
				result = false;
				System.out.println("study 실패");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public boolean deletesign(String getid) throws SQLException {

		String id = getid;
		int a[] = { 1, 1, 1, 1 };
		Connection conn = null;
		Statement stmt = null;
		boolean rs, result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "delete from result where studentID = '" + id + "'";
			rs = stmt.execute(sql);
			if (!rs) {
				sql = "delete from slimt where studentID = '" + id + "'";
				rs = stmt.execute(sql);
				if (!rs) {
					sql = "delete from study where studentID = '" + id + "'";
					rs = stmt.execute(sql);
					if (!rs) {
						result = true;
					} else {
						result = false;
					}
				} else {
					result = false;
				}
			} else {
				result = false;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void select_point(String getid, Student_point getsp) throws SQLException {
		Student_point sp = getsp;
		String id = getid;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null, rs1 = null;
		int a[] = { 0, 0, 0, 0 }, a1[] = { 0, 0, 0, 0 };
		String b[] = { "word", "java", "excel", "ppt" }, b1[] = { "이한아", "한유월", "한세건", "문소리" };

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/pj_1";

			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("연결성공");

			stmt = conn.createStatement();

			sql = "select student.name, study.* from student, study where student.id= " + id
					+ " and study.studentid = student.id";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("1");
				sp.a = rs.getString("name");
				a[0] = rs.getInt("word");
				a[1] = rs.getInt("java");
				a[2] = rs.getInt("excel");
				a[3] = rs.getInt("ppt");
				System.out.println("2");
			}
			rs.close();
			sql = "select * from result where studentid='" + id + "'";
			rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				System.out.println("3");
				a1[0] = rs1.getInt("word");
				a1[1] = rs1.getInt("java");
				a1[2] = rs1.getInt("excel");
				a1[3] = rs1.getInt("ppt");
				System.out.println("4");
			}
			for (int i = 0; i < 4; i++) {
				System.out.println("5");
				if (a[i] == 1) {
					String[] data = { b[i], a1[i] + "", b1[i] };
					sp.model.addRow(data);
				}
			}
			System.out.println("6");

			sp.tb.setModel(sp.model);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			rs.close();
			rs1.close();
			if (conn != null && !conn.isClosed()) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
