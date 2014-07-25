package org.asanka.bam;

import org.asanka.bam.beans.Student;
import org.asanka.bam.publisher.Publisher;
import org.wso2.carbon.databridge.agent.thrift.exception.AgentException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Publisher publisher=new Publisher();

    public static void main( String[] args )
    {
        System.setProperty("javax.net.ssl.trustStore", "//home//asanka//personal//Learning//BAM//wso2bam-2.4.1//repository//resources//security//client-truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
        publisher.addStreamDefinition();
        List<Student> studentList= new ArrayList<Student>();

        try {
            BufferedReader file= new BufferedReader(new FileReader("//home//asanka//personal//Learning//Sources//BAM//publisher/data.csv"));
            String line;
            while((line=file.readLine())!=null){
                studentList.add(new Student(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO  not found");
        }
      //  printStudents(studentList);
        publishData(studentList);
        System.out.println( "Hello World!" );
    }

    public static void printStudents(List<Student> list){
        Iterator itr=list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
    public static void publishData(List<Student> list){
        Iterator itr=list.iterator();
        Student next=null;
        while(itr.hasNext()){
            try {
                next = (Student) itr.next();
                publisher.publishEvent(next);

            } catch (AgentException e) {
                System.out.println("[Failed]--> "+next);
                e.printStackTrace();
            }
        }
        publisher.stop();
    }
}
