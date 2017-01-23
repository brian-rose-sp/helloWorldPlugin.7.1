package sailpoint.plugin.helloworld.task;

import sailpoint.api.SailPointContext;
import sailpoint.object.Attributes;
import sailpoint.object.TaskResult;
import sailpoint.object.TaskSchedule;
import sailpoint.plugin.helloworld.util.HelloWorldUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sailpoint.task.BasePluginTaskExecutor;

public class HelloWorldTaskExecutor extends BasePluginTaskExecutor {
    private static final Log log = LogFactory.getLog(HelloWorldTaskExecutor.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPluginName() {
        return HelloWorldUtil.PLUGIN_NAME;
    }

    @Override
    public void execute(SailPointContext context, TaskSchedule taskSchedule, TaskResult taskResult, Attributes<String, Object> stringObjectAttributes) throws Exception {
        log.info("Task executing");
        taskResult.put("total", 10);
    }

    /**
     * Clean up any statics, connections etc. or anything that will prevent this class from being unloaded.
     */
    @Override
    public boolean terminate() {
        return true;
    }
}
