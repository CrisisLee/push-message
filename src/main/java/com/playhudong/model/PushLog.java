package com.playhudong.model;

import java.sql.Timestamp;

/**
 * log for message-push,
 * each ordinary message has a log record,
 * each time of an advanced message pushed
 *     has a log record
 * @author arlabsurface
 *
 */
public class PushLog {
	
	public static final int STATUS_SUCCESS = 0;
	public static final int STATUS_FAILED = 1;

	private final int id;//id for a log record.
	//cause an advanced-message will produce more 
	//than one record, so message_id cannot be primary key

	private final int messageId;//id of message
	private Timestamp pushTime;//the actual push-time
	
	private int status;//status of the push, 0 stands for success
	//and 1 stands for failed
	
	
	
	
	/**
	 * use to read a log from db
	 * param is Integer, cause we also use it to read data from db
	 * @param id
	 * @param messageId
	 * @param pushTime
	 * @param status
	 */
	public PushLog(Integer id, Integer messageId, Timestamp pushTime, Integer status) {
		super();
		this.id = id;
		this.pushTime = pushTime;
		this.status = status;
		this.messageId = messageId;
	}

	
	/**
	 * when we create a log, we donot need to give an id,
	 * cause db will produce it automatically
	 * @param messageId
	 * @param pushTime
	 * @param status
	 */
	public PushLog(int messageId, Timestamp pushTime, int status) {
		super();
		this.id = -1;
		this.messageId = messageId;
		this.pushTime = pushTime;
		this.status = status;
	}



	@Override
	public String toString() {
		return "PushLog [id=" + id + ", messageId=" + messageId + ", pushTime=" + pushTime + ", status=" + status + "]";
	}



	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public int getMessageId() {
		return messageId;
	}


	public Timestamp getPushTime() {
		return pushTime;
	}
	
	public void setPushTime(Timestamp pushTime) {
		this.pushTime = pushTime;
	}
	
	
	
}
