package org.wso2.carbon.am.internal;

 
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.ndatasource.core.DataSourceService;

/**
 * @scr.component name="org.wso2.carbon.am" immediate="true"
 * @scr.reference name="org.wso2.carbon.ndatasource"
 * interface="org.wso2.carbon.ndatasource.core.DataSourceService"
 * cardinality="0..1" policy="dynamic" bind="setNDataService"
 * unbind="unsetNDataService"
 **/
public class DataSourceComponent {

    protected void activate(ComponentContext context) {
        System.out.println("Bundle activated !!!");
        BundleContext bundleContext = context.getBundleContext();       
      //bundleContext.registerService(ApplicationManagementService.class.getName(), mgtService, null);
    }

    protected void deactivate(ComponentContext context) {
         
    }

    protected void setNDataService(DataSourceService dataSourceAdminService) {
        System.out.println("setting damn thing !!!!!");
        Util.setNDataService(dataSourceAdminService);
    }

    protected void unsetNDataService(DataSourceService dataSourceAdminService) {
        Util.setNDataService(null);
    }

}
