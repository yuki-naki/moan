public class CommentBean {
	
	private String commentId;
	private String threadId;
	private String commenter;
	private String content;
	private String createdDate;
	
	public CommentBean(String commentId, String threadId, String commenter, String content, String createdDate) {
			this.commentId = commentId;
			this.threadId = threadId;
			this.commenter = commenter;
			this.content = content;
			this.createdDate = createdDate;
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
	public void setContent(String Content){
		this.content = content;
	}
	
	public String getCreatedDate(){
		return createdDate;
	}
	public void setCreatedDate(String createdId){
		this.createdDate= createdDate;
	}

	public String getCommenter(){
		return commenter;
	}
	public void setCommenter(String commenter){
		this.commenter = commenter;
	}
	
}