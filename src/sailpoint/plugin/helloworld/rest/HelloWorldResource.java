package sailpoint.plugin.helloworld.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import sailpoint.plugin.helloworld.service.HelloWorld;
import sailpoint.plugin.helloworld.service.HelloWorldService;
import sailpoint.plugin.helloworld.util.HelloWorldUtil;
import sailpoint.rest.plugin.BasePluginResource;
import sailpoint.rest.plugin.AllowAll;
import sailpoint.rest.plugin.Deferred;
import sailpoint.rest.plugin.RequiredRight;
import sailpoint.rest.plugin.SystemAdmin;
import sailpoint.tools.GeneralException;

/**
 * @author brian.rose
 */
@Path("helloworld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredRight("HelloWorldPluginRestServiceAllow")
public class HelloWorldResource extends BasePluginResource {

	private static final String SETTING_DEFAULT_MESSAGE = "defaultMessage";

	public HelloWorldResource() {
	}

	@Override
	public String getPluginName() {
		return HelloWorldUtil.PLUGIN_NAME;
	}

	/**
	 * Returns the default hello world message.  This is also an example if @AllowAll.  Anyone can use this.
	 */
	@GET
	@Path("hello")
	@AllowAll
	public String getDefaultHello() throws Exception {
		return getSettingString(SETTING_DEFAULT_MESSAGE);
	}

	/**
	 * Returns a dummy string.  This is just here to show the @RightsRequired annotation.
	 */
	@GET
	@Path("requiredRight")
	@RequiredRight("HelloWorldPluginRestServiceTestMessageAllow")
	public String getRightRequiredMessage() throws Exception {
		return "Hello World - You have the required right!";
	}

	/**
	 * Returns a dummy string.  This is just here to show the @Deferred annotation.
	 */
	@GET
	@Path("deferred")
	@Deferred
	public HelloWorld getDeferredMessage(int userId) throws Exception {

		HelloWorld helloWorld = new HelloWorld(userId, "Hello World - You have the @Deferred right!");
		authorize(new HelloWorldAuthorizer(helloWorld));

		return helloWorld;
	}

	/**
	 * Deletes all users from the helloWorld database table
	 *
	 * @throws GeneralException
	 */
	@DELETE
	@Path("admin/{userId}")
	@SystemAdmin
	public void deleteHelloWorldForUser(@PathParam("userId") String userId) throws GeneralException {
		getHelloWorldService().deleteHelloWorldForUser(userId);
	}

	/**
	 * Adds a user's message to the helloWorld database table
	 *
	 * @throws GeneralException
	 */
	@GET
	@Path("admin/{userId}/{message}")
	@SystemAdmin
	public void addHelloWorldForUser(@PathParam("userId") int userId,
									 @PathParam("message")String message) throws GeneralException {
		getHelloWorldService().addHelloWorldForUser(new HelloWorld(userId, message));
	}

	private HelloWorldService getHelloWorldService() {
		return new HelloWorldService(this);
	}
}
