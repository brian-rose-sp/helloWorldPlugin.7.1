package sailpoint.plugin.helloworld.service;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class HelloWorld {

	/**
	 * The id.
	 */
	private String id;

	/**
	 * The id.
	 */
	private String userId;

	/**
	 * The message
	 */
	private String message = new String();


	/**
	 * Gets the id.
	 *
	 * @return The id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id The id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the user id.
	 *
	 * @return The user id.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the id.
	 *
	 * @param userId The user id.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * Gets the message
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public HelloWorld(int userId, String message) {
		this.message = message;
	}
}
