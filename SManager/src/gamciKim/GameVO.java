package gamciKim;

public class GameVO {
	private String game_id; // 아이디
	private String friend_id; // 아이디
	private String game_pwd; // 패스워드
	private String nickname; // 닉네임
	private int game_level; // 레벨
	private String game_jobs; // 직업
	private String log_status; // 현재 상태
	private String crt_location; // 현재 위치
	private String game_server; // 서버이름
	private int n_mail; // 새로운 쪽지 수
	private int mail_num;// 쪽지 번호
	private String to_id; // (쪽) 보낸이
	private String from_id; // (쪽) 받는이
	private String mail_title; // (쪽) 제목
	private String mail_view; // (쪽) 내용
	private String sys_date; // (쪽) 보낸날짜
	private int notice_num; // 공지사항 번호
	private String notice_title; // (공) 제목
	private String notice_view; // (공) 내용
	private String up_date; // (공) 업로드 날짜
	private String read_notice;// (공) 읽음 여부

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String frien_id) {
		this.friend_id = frien_id;
	}

	public String getGame_pwd() {
		return game_pwd;
	}

	public void setGame_pwd(String game_pwd) {
		this.game_pwd = game_pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGame_level() {
		return game_level;
	}

	public void setGame_level(int game_level) {
		this.game_level = game_level;
	}

	public String getGame_jobs() {
		return game_jobs;
	}

	public void setGame_jobs(String game_jobs) {
		this.game_jobs = game_jobs;
	}

	public String getLog_status() {
		return log_status;
	}

	public void setLog_status(String log_status) {
		this.log_status = log_status;
	}

	public String getCrt_location() {
		return crt_location;
	}

	public void setCrt_location(String crt_location) {
		this.crt_location = crt_location;
	}

	public String getGame_server() {
		return game_server;
	}

	public void setGame_server(String game_server) {
		this.game_server = game_server;
	}

	public int getN_mail() {
		return n_mail;
	}

	public void setN_mail(int n_mail) {
		this.n_mail = n_mail;
	}

	public int getMail_num() {
		return mail_num;
	}

	public void setMail_num(int mail_num) {
		this.mail_num = mail_num;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getMail_view() {
		return mail_view;
	}

	public void setMail_view(String mail_view) {
		this.mail_view = mail_view;
	}

	public String getSys_date() {
		return sys_date;
	}

	public void setSys_date(String sys_date) {
		this.sys_date = sys_date;
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_view() {
		return notice_view;
	}

	public void setNotice_view(String notice_view) {
		this.notice_view = notice_view;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

	public String getRead_notice() {
		return read_notice;
	}

	public void setRead_notice(String read_notice) {
		this.read_notice = read_notice;
	}

	public String briefShow() {
		return String.format("%-10s %-15s %-15s %-20s", game_server, nickname, log_status, crt_location);
	}

	public String briefShow1() {
		return String.format("%-10s %-15s %-25s %-20s", notice_num, notice_title, up_date, notice_view, read_notice);
	}
} // 클래스 종료
