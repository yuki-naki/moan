package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ThreadBean {

	private String threadId;
	private String title;
	private String creator;
	private String createdDate;
	private String lastUpdate;
	private String lastUser;
	private int replyNb;


	public ThreadBean(String threadId, String title, String creator, String createdDate, String lastUpdate, String lastUser, int replyNb){
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

	public static Comparator<ThreadBean> titleComparator = new Comparator<ThreadBean>() {

		public int compare(ThreadBean t1, ThreadBean t2) {
		   String title1 = t1.getTitle();
		   String title2 = t2.getTitle();

		   //ascending order
		   return title1.compareTo(title2);
	    }
	};

	public static Comparator<ThreadBean> creatorComparator = new Comparator<ThreadBean>() {

		public int compare(ThreadBean t1, ThreadBean t2) {
		   String creator1 = t1.getCreator();
		   String creator2 = t2.getCreator();

		   //ascending order
		   return creator1.compareTo(creator2);
	    }
	};

	public static Comparator<ThreadBean> replyComparator = new Comparator<ThreadBean>() {

		public int compare(ThreadBean t1, ThreadBean t2) {
		   int reply1 = t1.getReplyNb();
		   int reply2 = t2.getReplyNb();

		   //ascending order
		   return reply1 - reply2;
	    }
	};

	public static Comparator<ThreadBean> createdDateComparator = new Comparator<ThreadBean>() {

		public int compare(ThreadBean t1, ThreadBean t2) {
		   String createdDate1 = t1.getCreatedDate();
		   String createdDate2 = t2.getCreatedDate();

		   SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		   try {
			   Date date1 = dateFormat1.parse(createdDate1);
			   Date date2 = dateFormat2.parse(createdDate2);
			   return date1.compareTo(date2);
		   }
		   catch (ParseException e) {
			e.printStackTrace();
		   }

		   //ascending order
		   return createdDate1.compareTo(createdDate2);
	    }
	};

	public static Comparator<ThreadBean> lastUserComparator = new Comparator<ThreadBean>() {

		public int compare(ThreadBean t1, ThreadBean t2) {
		   String lastUser1 = t1.getLastUser();
		   String lastUser2 = t2.getLastUser();

		   //ascending order
		   return lastUser1.compareTo(lastUser2);
	    }
	};

	public static Comparator<ThreadBean> lastUpdateComparator = new Comparator<ThreadBean>() {

		public int compare(ThreadBean t1, ThreadBean t2) {
		   String lastUpdate1 = t1.getLastUpdate();
		   String lastUpdate2 = t2.getLastUpdate();

		   SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		   try {
			   Date date1 = dateFormat1.parse(lastUpdate1);
			   Date date2 = dateFormat2.parse(lastUpdate2);
			   return date1.compareTo(date2);
		   }
		   catch (ParseException e) {
			e.printStackTrace();
		   }

		   //ascending order
		   return lastUpdate1.compareTo(lastUpdate2);
	    }
	};
}
