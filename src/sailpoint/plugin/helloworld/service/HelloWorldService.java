package sailpoint.plugin.helloworld.service;

import sailpoint.plugin.PluginBaseHelper;
import sailpoint.plugin.PluginContext;
import sailpoint.tools.GeneralException;
import sailpoint.tools.IOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian.rose on 1/23/2017.
 */
public class HelloWorldService {

	private static final String GET_USER_QUERY = "SELECT * FROM helloworld_table WHERE user_id = ? ORDER BY user_id ASC";
	private static final String DELETE_USER_QUERY = "DELETE FROM helloworld_table WHERE user_id = ?";
	private static final String ADD_USER_QUERY = "INSERT INTO helloworld_table (user_id, message) VALUES (?,?)";

	/**
	 * The plugin context.
	 */
	private PluginContext pluginContext;

	/**
	 * Constructor.
	 *
	 * @param pluginContext The plugin context.
	 */
	public HelloWorldService(PluginContext pluginContext) {
		this.pluginContext = pluginContext;
	}

	/**
	 *  Gets a user's custom message
	 *
	 * @param userId The user id.
	 * @return The HelloWorld info for the user.
	 * @throws GeneralException
	 */
	public HelloWorld getHelloWorldForUser(String userId) throws GeneralException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = pluginContext.getConnection();

			statement = PluginBaseHelper.prepareStatement(connection, GET_USER_QUERY, userId);
			ResultSet resultSet = statement.executeQuery();

			HelloWorld helloWorld = null;

			// should only ever be one, but will get the last one
			while (resultSet.next()) {
				helloWorld = new HelloWorld(resultSet.getInt("userId"),
						resultSet.getString("message"));
			}

			return helloWorld;
		} catch (SQLException e) {
			throw new GeneralException(e);
		} finally {
			IOUtil.closeQuietly(statement);
			IOUtil.closeQuietly(connection);
		}
	}

	/**
	 *  Adds a user's custom message
	 *
	 * @param helloWorld The hello world information to add for a user.
	 * @throws GeneralException
	 */
	public void addHelloWorldForUser(HelloWorld helloWorld) throws GeneralException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = pluginContext.getConnection();

			statement = PluginBaseHelper.prepareStatement(connection, ADD_USER_QUERY,
					helloWorld.getUserId(), helloWorld.getMessage());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new GeneralException(e);
		} finally {
			IOUtil.closeQuietly(statement);
			IOUtil.closeQuietly(connection);
		}
	}

	/**
	 *  Deletes a user's custom message record from the db
	 *
	 * @param userId The user id.
	 * @throws GeneralException
	 */
	public void deleteHelloWorldForUser(String userId) throws GeneralException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = pluginContext.getConnection();

			statement = PluginBaseHelper.prepareStatement(connection, DELETE_USER_QUERY, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new GeneralException(e);
		} finally {
			IOUtil.closeQuietly(statement);
			IOUtil.closeQuietly(connection);
		}
	}
}
