import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.xd.jms.IMClient;

/**
 * @author wuliwei
 * 
 */
public class StartUp {

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
