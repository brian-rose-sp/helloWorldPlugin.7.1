
package sailpoint.plugin.helloworld.rest;

import sailpoint.authorization.Authorizer;
import sailpoint.authorization.UnauthorizedAccessException;
import sailpoint.plugin.helloworld.service.HelloWorld;
import sailpoint.tools.GeneralException;
import sailpoint.web.UserContext;

/**
 * Authorizer which checks to see if the currently logged in user
 * has access to the specified helloWorld resource.
 *
 * @author brian.rose (from Dustin Dobervich's TODO plugin)
 *
 */
class HelloWorldAuthorizer implements Authorizer {

	/**
	 * The todo to check.
	 */
	private HelloWorld helloWorld;

	/**
	 * Constructor.
	 *
	 * @param helloWorld - the hello world object
	 */
	public HelloWorldAuthorizer(HelloWorld helloWorld) {
		this.helloWorld = helloWorld;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void authorize(UserContext userContext) throws GeneralException {
		if (!userContext.getLoggedInUser().getId().equals(helloWorld.getUserId())) {
			throw new UnauthorizedAccessException("User does not have access to the todo");
		}
	}

}

