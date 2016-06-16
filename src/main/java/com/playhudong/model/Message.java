package com.playhudong.model;

/**
 * model of message to push
 * @author arlabsurface
 *
 */
import java.sql.Timestamp;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed, Comparable<Delayed> {

	public static final int ORDINARY = 0;
	public static final int ADVANCED = 1;
	
	public static final int STATUS_EDITABLE = 0;
	public static final int STATUS_READYTOPUSH = 1;
	public static final int STATUS_GOINGTOPUSH = 2;
	public static final int STATUS_PUSHED = 3;
	public static final int STATUS_FAILTOPUSH = 4;

	public static final int CHANNEL_WEIXIN = 0;
	public static final int CHANNEL_WEIBO = 1;
	public static final int CHANNEL_SMS = 2;
	public static final int CHANNEL_EMAIL = 3;
	
	private final int id;
	private final Timestamp createTime;
	private final String createUser;

	private String title;
	private String content;
	private int toUsers;
	private int channel;
	private int status;
	private int pushType;
	private Timestamp updateTime;

	private Timestamp pushTime;
	private String cronExpression;
	private int pushedCount;

	public boolean isOrdinary() { return pushType == 0; }
	
	/**
	 * base constructor
	 * 
	 * ordinary message and advanced message constructor
	 * will call this one
	 * 
	 * update time is initialized as create time
	 * cause it is the first time to update the message
	 * 
	 * @param createTime
	 * @param createUser
	 * @param title
	 * @param content
	 * @param toUsers
	 * @param channel
	 * @param status
	 * @param updateTime
	 * @param pushType
	 */
	private Message(Timestamp createTime, String createUser, String title, String content, int toUsers, int channel,
			int status, int pushType) {
		super();
		this.id = -1;
		this.createTime = createTime;
		this.createUser = createUser;
		this.title = title;
		this.content = content;
		this.toUsers = toUsers;
		this.channel = channel;
		this.status = status;
		this.updateTime = createTime;
		this.pushType = pushType;

		pushTime = null;
		cronExpression = null;
		pushedCount = -1;
	}

	/**
	 * used to read message from db
	 * 
	 * @param id
	 * @param createTime
	 * @param createUser
	 * @param title
	 * @param content
	 * @param toUsers
	 * @param channel
	 * @param status
	 * @param pushType
	 * @param updateTime
	 * @param pushTime
	 * @param cronExpression
	 * @param pushedCount
	 */
	public Message(Integer id, String title, String content, Integer toUsers, Integer channel, Timestamp pushTime, Integer status,
			Timestamp createTime, String createUser, Timestamp updateTime, String cronExpression, Integer pushedCount,
			Integer pushType) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.createUser = createUser;
		this.title = title;
		this.content = content;
		this.toUsers = toUsers;
		this.channel = channel;
		this.pushTime = pushTime;
		this.status = status;
		this.pushType = pushType;
		this.updateTime = updateTime;
		this.cronExpression = cronExpression;
		if (pushedCount == null) {
			//if it is a normal message,
			//value in message_table will be null
			//so cannot give a null value to a 'int'
			this.pushedCount = -1;
		}else
			this.pushedCount = pushedCount;
	}

	/**
	 * model for a advanced message
	 * push_time will be null
	 * 
	 * @param createTime
	 * @param createUser
	 * @param title
	 * @param content
	 * @param toUsers
	 * @param channel
	 * @param status
	 * @param updateTime
	 * @param pushType
	 * @param cronExpression
	 */
	public Message(Timestamp createTime, String createUser, String title, String content, int toUsers, int channel,
			int status, int pushType, String cronExpression) {
		//call the base constructor
		this(createTime, createUser, title, content, toUsers, channel, status, pushType);
		// give value to cronExpression
		//make pushedCount 0
		this.cronExpression = cronExpression;
		pushedCount = 0;
	}

	/**
	 * model for an ordinary message
	 * @param createTime
	 * @param createUser
	 * @param title
	 * @param content
	 * @param toUsers
	 * @param channel
	 * @param status
	 * @param updateTime
	 * @param pushType
	 * @param pushTime
	 *            
	 */
	public Message(Timestamp createTime, String createUser, String title, String content, int toUsers, int channel,
			int status, int pushType, Timestamp pushTime) {
		this(createTime, createUser, title, content, toUsers, channel, status, pushType);
		// TODO Auto-generated constructor stub
		// give value to push_time
		this.pushTime = pushTime;

	}

	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long timeLeft = pushTime.getTime() - System.currentTimeMillis();
		if (timeLeft <= 0) {
			return 0;
		}
		long result = unit.convert(timeLeft, TimeUnit.MILLISECONDS);
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channel;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((cronExpression == null) ? 0 : cronExpression.hashCode());
		result = prime * result + id;
		result = prime * result + ((pushTime == null) ? 0 : pushTime.hashCode());
		result = prime * result + pushType;
		result = prime * result + pushedCount;
		result = prime * result + status;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + toUsers;
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (channel != other.channel)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (cronExpression == null) {
			if (other.cronExpression != null)
				return false;
		} else if (!cronExpression.equals(other.cronExpression))
			return false;
		if (id != other.id)
			return false;
		if (pushTime == null) {
			if (other.pushTime != null)
				return false;
		} else if (!pushTime.equals(other.pushTime))
			return false;
		if (pushType != other.pushType)
			return false;
		if (pushedCount != other.pushedCount)
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (toUsers != other.toUsers)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", createTime=" + createTime + ", createUser=" + createUser + ", title=" + title
				+ ", content=" + content + ", toUsers=" + toUsers + ", channel=" + channel + ", status=" + status
				+ ", pushType=" + pushType + ", updateTime=" + updateTime + ", pushTime=" + pushTime
				+ ", cronExpression=" + cronExpression + ", pushedCount=" + pushedCount + "]";
	}


//	public int compareTo(Message o) {
//		// TODO Auto-generated method stub
//		//current push-time is before O's push-time, 
//		//current priority is higher,
//		//and thus return a nagative value
//		if (getPushTime().before(o.getPushTime())) {
//			return -1;
//		} else if (getPushTime().after(o.getPushTime())) {
//			//oterwise, return a positive value
//			return 1;
//		}
//		
//		return 0;
//	}
	
	public static void main(String[] args) {


	}

	public Timestamp getPushTime() {
		return pushTime;
	}

	public void setPushTime(Timestamp pushTime) {
		this.pushTime = pushTime;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getPushedCount() {
		return pushedCount;
	}

	public void setPushedCount(int pushedCount) {
		this.pushedCount = pushedCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getToUsers() {
		return toUsers;
	}

	public void setToUsers(int toUsers) {
		this.toUsers = toUsers;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public int getPushType() {
		return pushType;
	}

	public void setPushType(int pushType) {
		this.pushType = pushType;
	}

	public int getId() {
		return id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}




}
