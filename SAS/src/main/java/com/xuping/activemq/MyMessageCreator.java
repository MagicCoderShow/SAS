package com.xuping.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class MyMessageCreator implements MessageCreator {
    public String message = "";
    @Override
    public Message createMessage(Session paramSession) throws JMSException {
        return paramSession.createTextMessage(message);
    }
}