package org.asanka.bam.publisher;

import org.asanka.bam.beans.Student;
import org.wso2.carbon.databridge.agent.thrift.AsyncDataPublisher;
import org.wso2.carbon.databridge.agent.thrift.exception.AgentException;
import org.wso2.carbon.databridge.commons.Event;

import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by asanka on 7/25/14.
 */
public class Publisher {

    private  final String streamName="student_marksee";
    private  final String version="1.0.0";
    private AsyncDataPublisher asyncDataPublisher=new AsyncDataPublisher("tcp://localhost:7611", "admin", "admin");
    String streamDefinition = "{" +
            " 'name':'" + streamName + "'," +
            " 'version':'" + version + "'," +
            " 'nickName': 'student_data'," +
            " 'description': 'marks of student'," +
            " 'metaData':[" +
            " {'name':'publisherIP','type':'STRING'}" +
            " ]," +
            " 'payloadData':[" +
            " {'name':'studentName','type':'STRING'}," +
            " {'name':'subject','type':'STRING'}," +
            " {'name':'marks','type':'INT'}" +
            " ]" +
            "}";

    public void addStreamDefinition(){
        asyncDataPublisher.addStreamDefinition(streamDefinition,streamName,version);

    }

    public void publishEvent(Student student) throws AgentException {
        Object [] payload=new Object[]{student.getName(),student.getSubject(),student.getMarks()};
        Object [] metadata=new Object[]{"10.100.0.182"};
        Event event= createEvent(null,metadata,payload,streamName);

        asyncDataPublisher.publish(streamName,version,event);
        System.out.println("[Published:publisher]--> "+ student);
    }

    private Event createEvent(Object[] correlationData, Object[] metaData,
                              Object[] payLoadData,String streamId){
        Date date= new Date();
        Event event=new Event();//(streamId,date.getTime(),metaData,correlationData,payLoadData);
        event.setCorrelationData(correlationData);
        event.setMetaData(metaData);
        event.setPayloadData(payLoadData);
        return event;
    }

    public void stop(){
        asyncDataPublisher.stop();
    }






}
