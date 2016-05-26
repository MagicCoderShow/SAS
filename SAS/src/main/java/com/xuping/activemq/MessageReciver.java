package com.xuping.activemq;
import javax.jms.Destination;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
/**
 * 消息接收方
 * User: liuwentao
 * Time: 12-6-14 上午11:32
 */
public class MessageReciver{
    public static void main(String args[]) throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616?wireFormat.maxInactivityDuration=0");
        JmsTemplate jmsTemplate = new JmsTemplate(factory);
        Destination destination = new ActiveMQQueue("MessageQueue");

        TextMessage msg = null;
        //是否继续接收消息
        boolean isContinue = true;
        while (isContinue) {
            msg = (TextMessage) jmsTemplate.receive(destination);
            System.out.println("收到消息 :" + msg.getText());
            if (msg.getText().equals("end")) {
                isContinue = false;
                System.out.println("收到退出消息，程序要退出！");
            }
        }
        System.out.println("程序退出了！");
    }
}