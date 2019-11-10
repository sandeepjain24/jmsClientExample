package com.javacodegeeks.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class JmsProducerQueueClient {
    public static void main(String[] args) throws Exception {
    String inputReponse;
    boolean flag=true;
    JmsProducerQueueClient client= new JmsProducerQueueClient();
    do{
        System.out.println("Please select the method \n" +
                "1. Start \n" +
                "2. Cancel\n" +
                "3. Exit\n" );
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        switch(number){
            case 1:
                System.out.println("Started Evaluation");
                client.startEvaluation();
                break;
            case 2:
                System.out.println("Cancel Evaluation");
                client.cancelEvaluation();
                break;
            case 3:
                flag=false;
                break;
        }


    }
    while(flag);

    }

    public static String jsonRequest() {
        return "{\n" +
                "\"evalId\" : \"1\",\n" +
                "                             \"alternatives\": [{ \n" +
                "                                                          \"altId\": \"6aea3fd3-6ac1-4f6b-a42f-e417c6596dd7\", \n" +
                "                                                          \"responsePlans\": [{ \n" +
                "                                                                        \"responsePlanId\": \"1\", \n" +
                "                                                                        \"activationTime\": 1560843441374\n" +
                "                                                          }]\n" +
                "                                           },\n" +
                "\t\t\t\t\t\t\t\t\t\t   { \n" +
                "                                                          \"altId\": \"cce1cfd1-5214-4bbf-8147-5f5529e477c8\", \n" +
                "                                                          \"responsePlans\": [{ \n" +
                "                                                                        \"responsePlanId\": \"1\", \n" +
                "                                                                        \"activationTime\": 1560843441374\n" +
                "                                                          }]\n" +
                "                                           }]\n" +
                "                             \n" +
                "}";
    }
    public void startEvaluation() throws Exception{
        Connection connection = null;
        try {
            // Producer
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://localhost:61616"); //192.168.9.161
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("EVALUATION_REQUEST_QUEUE");
            MessageProducer producer = session.createProducer(queue);
            String jsonContent = jsonRequest();
            Message message = session.createTextMessage(jsonContent);
            message.setStringProperty("_type", "startEvaluationRequest");
            System.out.println("Sending text '" + jsonContent + "'");
            producer.send(message);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void cancelEvaluation() throws Exception{
        Connection connection = null;
        try {
            // Producer
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://localhost:61616"); //192.168.9.161
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("EVALUATION_REQUEST_QUEUE");
            MessageProducer producer = session.createProducer(queue);
            String jsonContent = jsonRequest();
            Message message = session.createTextMessage(jsonContent);
            message.setStringProperty("_type", "cancelEvaluationRequest");
            System.out.println("Sending text '" + jsonContent + "'");
            producer.send(message);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public String cancelJsonRequest(){
        return "\n" +
                "{\n" +
                " \"evalId\": \"1\"\n" +
                "}";
    }
}
