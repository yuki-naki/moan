package bean;

public class CommentBean {

	private String commentId;
	private String threadId;
	private String commenter;
	private String content;
	private String createdDate;
	private boolean isDeleted;

	public CommentBean(String commentId, String threadId, String commenter, String content, String createdDate, boolean isDeleted) {
			this.commentId = commentId;
			this.threadId = threadId;
			this.commenter = commenter;
			this.content = content;
			this.createdDate = createdDate;
			this.isDeleted = isDeleted;
		}

	public String getCommentId(){
		return commentId;
	}
	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getThreadId(){
		return threadId;
	}
	public void setThreadId(String threadId){
		this.threadId = threadId;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}

	public String getCreatedDate(){
		return createdDate;
	}
	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCommenter(){
		return commenter;
	}
	public void setCommenter(String commenter){
		this.commenter = commenter;
	}

	public boolean getIsDeleted(){
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted){
		this.isDeleted = isDeleted;
	}
}
