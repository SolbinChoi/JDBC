import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestUpdate {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결 얻어오기 
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 연결 문자열
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			// 3. statement 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "update author set name = '안대혁' where no = 5";
			int count = stmt.executeUpdate(sql); // 몇 개가 삽입되었는지 확인하기
			
			System.out.println(count + "개의 row가 수정되었습니다.");
			
			/* conn.commit(); 명시적으로 안해주어도 된다. conn.close()*/
			
		} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패:" + e);	
		} catch (SQLException e) {
			System.out.println("에러:" + e);
		} finally { // 6. 자원 정리
			try { 
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