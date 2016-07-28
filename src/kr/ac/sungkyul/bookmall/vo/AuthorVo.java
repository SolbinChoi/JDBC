package kr.ac.sungkyul.bookmall.vo;

public class AuthorVo {
	private Long no;
	private String name;
	private String description;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
		@Override
	public String toString() { // 자기 값을 제대로 출력하기 위해서
		return "AuthorVo [no=" + no + ", name=" + name + ", description=" + description + "]";
	}
}
