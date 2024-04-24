package Model;

public class UserDTO {
	
	// 1. Field (ID, PW, NICK)
	private String id;
	private String pw;
	private String pw2;
	private String nick;
	
	// 2. Constructor
	public UserDTO(String id, String pw, String nick) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
	}
	
	public UserDTO(String id, String pw, String pw2, String nick) {
		super();
		this.id = id;
		this.pw = pw;
		this.pw2 = pw2;
		this.nick = nick;
	}
	
	public UserDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	// 3. Getter & Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPw2() {
		return pw2;
	}
	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

}
