package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.BookVo;

public class BookDao {
	
	public int insert(BookVo vo){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결 얻어오기 
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 연결 문자열
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			// 3. statement 준비 (미리 쿼리를 준비시켜줌, 동적인 쿼리)
			String sql = "insert into book values(seq_author.nextval, ? ,?, ?)"; // 값이 바인딩 되는 것임으로 물음표
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩 작업
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getRate());
			pstmt.setLong(3, vo.getAuthorNo());
			
			//5. 쿼리 실행
			count = pstmt.executeUpdate(); // 몇 개가 삽입되었는지 확인하기
			/* conn.commit(); 명시적으로 안해주어도 된다. conn.close()*/
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);	
		} catch (SQLException e) {
			System.out.println("에러:" + e);
		} finally { // 6. 자원 정리
			try { 
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
				conn.close(); // 성공 후 연결 끊기
			}
			}catch (SQLException e) {
				System.out.println("에러:"+ e);
				} 
			}
		return count;
	}
	
	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();
		// JDBC 코드
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결 얻어오기 
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 연결 문자열
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			// 3. statement 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행 (정적인 쿼리)
			String sql = "select no," +
						 "	title, " +
						 " rate ," +
						 " author_No ," +
						 " from book ";
			rs = stmt.executeQuery(sql); // 준비 해놓은 게 없으니까 바로 sql을 넣어주기
			
			while(rs.next()){ // 현재 로우를 가리키고 있는 동안
				Long no = rs.getLong(1); // 컬럼 인덱스 1번부터 시작 (employee_id 값)
				String title = rs.getString(2);
				int rate = rs.getInt(3);
				Long authorNo = rs.getLong(4);
				
				BookVo vo = new BookVo();
				// 얻어온 것을 vo 객체에 담기
				vo.setNo(no); 
				vo.setTitle(title);
				vo.setRate(rate);
				vo.setAuthorNo(authorNo);
				
				// 저장
				list.add(vo); 
				
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
		
		
		return list;	
	}
}
