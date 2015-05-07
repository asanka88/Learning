package org.wso2.carbon.am.sample;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.wso2.carbon.am.internal.Util;
import org.wso2.carbon.ndatasource.common.DataSourceException;
import org.wso2.carbon.ndatasource.core.CarbonDataSource;

import java.util.List;

/**
 * This mediator routes the message to a dynamic endpoint depending on the
 * current location of the user
 */
public class DataSourceInfoMediator extends AbstractMediator {

    public boolean mediate(MessageContext messageContext) {
        try {
            List<CarbonDataSource> allDataSources = Util.getNDataService().getAllDataSources();


            for(CarbonDataSource ds:allDataSources){
                String name=ds.getDSMInfo().getName();
                DataSource dsObject = (DataSource) ds.getDSObject();
                String url = dsObject.getUrl();
                System.out.println(url);
            }

        } catch (DataSourceException e) {
        }
        return false;
    }
}