package bean;

public class CommentBean {
	
	private String commentId;
	private String threadId;
	private String createdDate;
	private String creator;
	private String content;
	
	
	public CommentBean(String commentId, String threadId, 
		String createdDate, String creator, String content) {
			this.commentId = commentId;
			this.threadId = threadId;
			this.createdId = createdId;
			this.createdDate = createdDate;
			this.content = content;
		}
	
	public String getCommentId(){
		return commentId;
	}
	public setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getThreadId(){
		return threadId;
	}
	public setThreadId(String threadId){
		this.threadId = threadId;
	}
	
	public String getCreatedDate(){
		return createdId;
	}
	public setCreatedId(String createdId){
		this.createdId = createdId;
	}
	
	public String getCreatedDate(){
		return createdDate;
	}
	public setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getContent(){
		return content;
	}
	public setContent(String Content){
		this.content = content;
	}
}