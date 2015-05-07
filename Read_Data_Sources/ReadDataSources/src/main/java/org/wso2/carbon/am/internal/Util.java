package org.wso2.carbon.am.internal;

import org.wso2.carbon.ndatasource.core.DataSourceService;

public class Util {
    private static DataSourceService dataSourceAdminService;
    public static DataSourceService getNDataService() {
        return dataSourceAdminService;
    }
    
    public static synchronized void setNDataService(DataSourceService realmSer) {
        dataSourceAdminService= realmSer;
 }

}
