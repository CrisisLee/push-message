package com.playhudong.model;

/**
 * model of message to push
 * @author arlabsurface
 *
 */
import java.sql.Timestamp;

public class Message implements Comparable<Message> {

	public static final int ORDINARY = 0;
	public static final int ADVANCED = 1;
	
	public static final int STATUS_EDITABLE = 0;
	public static final int STATUS_READYTOPUSH = 1;
	public static final int STATUS_GOINGTOPUSH = 2;
	public static final int STATUS_PUSHED = 3;
	public static final int STATUS_FAILTOPUSH = 4;

	
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
	private Message(int id, Timestamp createTime, String createUser, String title, String content, int toUsers, int channel,
			int status, int pushType) {
		super();
		
		this.id = id;
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
	public Message(int id, Timestamp createTime, String createUser, String title, String content, int toUsers, int channel,
			int status, int pushType, String cronExpression) {
		//call the base constructor
		this(id, createTime, createUser, title, content, toUsers, channel, status, pushType);
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
	public Message(int id, Timestamp createTime, String createUser, String title, String content, int toUsers, int channel,
			int status, int pushType, Timestamp pushTime) {
		this(id, createTime, createUser, title, content, toUsers, channel, status, pushType);
		// TODO Auto-generated constructor stub
		// give value to push_time
		this.pushTime = pushTime;

	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", createTime=" + createTime + ", createUser=" + createUser + ", title=" + title
				+ ", content=" + content + ", toUsers=" + toUsers + ", channel=" + channel + ", status=" + status
				+ ", pushType=" + pushType + ", updateTime=" + updateTime + ", pushTime=" + pushTime
				+ ", cronExpression=" + cronExpression + ", pushedCount=" + pushedCount + "]";
	}


	public int compareTo(Message o) {
		// TODO Auto-generated method stub
		//current push-time is before O's push-time, 
		//current priority is higher,
		//and thus return a nagative value
		if (getPushTime().before(o.getPushTime())) {
			return -1;
		} else if (getPushTime().after(o.getPushTime())) {
			//oterwise, return a positive value
			return 1;
		}
		
		return 0;
	}
	
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


}
