package sailpoint.plugin.helloworld.policy;


import java.util.ArrayList;
import java.util.List;
import sailpoint.api.SailPointContext;
import sailpoint.object.Identity;
import sailpoint.object.Policy;
import sailpoint.object.PolicyViolation;
import sailpoint.plugin.helloworld.util.HelloWorldUtil;
import sailpoint.policy.BasePluginPolicyExecutor;
import sailpoint.tools.GeneralException;

/**
 * @
 */
public class HelloWorldPolicyExecutor extends BasePluginPolicyExecutor {

    @Override
    public String getPluginName() {
        return HelloWorldUtil.PLUGIN_NAME;
    }

    public List<PolicyViolation> evaluate(SailPointContext context, Policy policy, Identity id) throws GeneralException{
        return new ArrayList<PolicyViolation>();
    }

    /**
     * Clean up any statics, connections etc. or anything that will prevent this class from being unloaded.
     */
    public void terminate(){
        //nothing to do;
    }


}
