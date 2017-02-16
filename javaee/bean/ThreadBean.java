public class ThreadBean {

	private String threadId;
	private String title;
	private String creator;
	private String createdDate;
	private String lastUpdate;
	private String lastUser;
	private int replyNb;


	public ThreadBean(String threadId, String title, String creator,
		String createdDate, String lastUpdate, String lastUser, int replyNb){
			
		this.threadId = threadId;
		this.title = title;
		this.creator = creator;
		this.createdDate = createdDate;
		this.lastUpdate = lastUpdate;
		this.lastUser = lastUser;
		this.replyNb = replyNb;

	}

	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
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

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public String getLastUser() {
		return lastUser;
	}
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public int getReplyNb() {
		return replyNb;
	}
	public void setReplyNb(int replyNb) {
		this.replyNb = replyNb;
	}
	

}
