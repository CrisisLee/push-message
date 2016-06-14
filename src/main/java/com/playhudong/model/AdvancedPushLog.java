package com.playhudong.model;

import java.sql.Timestamp;
/**
 * log for an advanced message, containing pushed-count and 
 * last-push-time
 * @author arlabsurface
 *
 */

public class AdvancedPushLog {

	private final int messageId;//id of the advanced message
	private int pushedCount;
	private Timestamp lastPushTime;
	
	/**
	 * used to create an advanced message log
	 * each message corresponds to a log
	 * @param messageId
	 */
	public AdvancedPushLog(int messageId, Timestamp currentTime) {
		super();
		this.messageId = messageId;
		this.pushedCount = 1;//once we create an advanced-log, it
		//means that the message has been pushed once, so it should be 1
		this.lastPushTime = currentTime;
	}

	
	/**
	 * used to read data from db
	 * @param messageId
	 * @param pushedCount
	 * @param lastPushTime
	 */

	public AdvancedPushLog(Integer messageId, Integer pushedCount, Timestamp lastPushTime) {
		super();
		this.messageId = messageId;
		this.pushedCount = pushedCount;
		this.lastPushTime = lastPushTime;
	}

	


	@Override
	public String toString() {
		return "AdvancedPushLog [messageId=" + messageId + ", pushedCount=" + pushedCount + ", lastPushTime="
				+ lastPushTime + "]";
	}


	public int getPushedCount() {
		return pushedCount;
	}

	public void setPushedCount(int pushedCount) {
		this.pushedCount = pushedCount;
	}

	public Timestamp getLastPushTime() {
		return lastPushTime;
	}

	public void setLastPushTime(Timestamp lastPushTime) {
		this.lastPushTime = lastPushTime;
	}

	public int getMessageId() {
		return messageId;
	}
	
	
	
}
