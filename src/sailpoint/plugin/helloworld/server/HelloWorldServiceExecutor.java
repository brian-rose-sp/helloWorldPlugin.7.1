package sailpoint.plugin.helloworld.server;

import sailpoint.plugin.helloworld.util.HelloWorldUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sailpoint.server.BasePluginService;
import sailpoint.tools.GeneralException;
import sailpoint.api.SailPointContext;

/**
 * @author brian.rose
 */
public class HelloWorldServiceExecutor extends BasePluginService {
    private static final Log log = LogFactory.getLog(HelloWorldServiceExecutor.class);

    @Override
    public String getPluginName() {
        return HelloWorldUtil.PLUGIN_NAME;
    }

    @Override
    public void execute(SailPointContext context) throws GeneralException {
        log.warn("Hello World Service Executor Interval Message");
    }
}
