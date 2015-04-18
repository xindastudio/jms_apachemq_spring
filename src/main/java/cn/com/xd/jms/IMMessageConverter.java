package cn.com.xd.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * @author wuliwei
 * 
 */
public class IMMessageConverter implements MessageConverter {

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jms.support.converter.MessageConverter#fromMessage(javax.jms.Message)
	 */
	@Override
	public Object fromMessage(Message pArg0) throws JMSException,
			MessageConversionException {
		return ((TextMessage) pArg0).getText();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jms.support.converter.MessageConverter#toMessage(java.lang.Object,
	 *      javax.jms.Session)
	 */
	@Override
	public Message toMessage(Object pArg0, Session pArg1) throws JMSException,
			MessageConversionException {
		return pArg1.createTextMessage((String) pArg0);
	}

}
