package com.xuping.activemq;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
/**
 * 发送消息方
 * User: liuwentao
 * Time: 12-6-14 上午11:29
 */
public class MessageSender extends Thread {
    public static void main(String args[]) throws Exception {
    	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616?wireFormat.maxInactivityDuration=0");
        JmsTemplate jmsTemplate = new JmsTemplate(factory);
        Destination destination = new ActiveMQQueue("MessageQueue");
        for (int i = 1; i <=10 ; i++) {
            System.out.println("发送 i=" + i);
            //消息产生者
            MyMessageCreator myMessageCreator = new MyMessageCreator();
            myMessageCreator.message = (i<10?i+"":"end");
            jmsTemplate.send(destination, myMessageCreator);
            sleep(2000);//2秒后发送下一条消息
        }
    }
}