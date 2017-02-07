package bean;

public class ThreadBean {

	private String threadId;
	private String userId;
	private String title;
	private String creator;
	private String date;
	private String lastUsername;
	private int replyNb;

	public ThreadBean(String threadId, String userId, String title, String creator, String date, String lastUsername, int replyNb){
		this.threadId = threadId;
		this.userId = userId;
		this.title = title;
		this.creator = creator;
		this.date = date;
		this.lastUsername = lastUsername;
		this.replyNb = replyNb;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLastUsername() {
		return lastUsername;
	}

	public void setLastUsername(String lastUsername) {
		this.lastUsername = lastUsername;
	}

	public int getReplyNb() {
		return replyNb;
	}

	public void setReplyNb(int replyNb) {
		this.replyNb = replyNb;
	}
}
