import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestCount {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결 얻어오기 
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 연결 문자열
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			// 3. statement 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select count(*) from employees "; // row 하나
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){ // row 하나임으로 if문
				int count = rs.getInt(1); // 컬럼 인덱스 1번부터 시작 (employee_id 값)
				// 얻어온 것 출력
				System.out.println("전체" + count + "개의 row가 있습니다.");
			}

		} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패:" + e);	
		} catch (SQLException e) {
			System.out.println("에러:" + e);
		} finally { // 6. 자원 정리
			try { 
				if(rs!=null){ // 역순으로 닫아주기
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
				conn.close(); // 성공 후 연결 끊기
			}
			}catch (SQLException e) {
				e.printStackTrace();
			} 
			}

	}

}
