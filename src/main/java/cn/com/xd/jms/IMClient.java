package cn.com.xd.jms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author wuliwei
 * 
 */
public class IMClient implements Runnable {
	private static final String exitCmd = "exit";
	private JmsTemplate jmsTemplate;
	private Destination sendTo;

	/**
	 * @param pJmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate pJmsTemplate) {
		jmsTemplate = pJmsTemplate;
	}

	/**
	 * @param pSendTo
	 */
	public void setSendTo(Destination pSendTo) {
		sendTo = pSendTo;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		send();
	}

	/**
	 * @param msg
	 */
	public void onMessage(Object msg) {
		try {
			System.out.println("received : " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean send() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String msg = null;
			while (true) {
				try {
					msg = br.readLine();
					if (null != msg && !msg.isEmpty()) {
						if (exitCmd.equals(msg.toLowerCase())) {
							break;
						}
						jmsTemplate.convertAndSend(sendTo, msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] path = new String[] { "jms.spring.xml" };
		ApplicationContext ac = new ClassPathXmlApplicationContext(path);
		IMClient client = (IMClient) ac.getBean("client");
		new Thread(client).start();
	}

}
