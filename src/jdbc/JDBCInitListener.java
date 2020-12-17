package jdbc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class JDBCInitListener
 *
 */
@WebListener
public class JDBCInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public JDBCInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	//1. 클래스 로딩
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    	} catch (ClassNotFoundException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	
    	//2. Connection을 하는 클래스에 정보를 넘겨줌
    	ServletContext application = sce.getServletContext();
    	
    	String url = application.getInitParameter("jdbcUrl");
    	String user = application.getInitParameter("jdbcUser");
    	String password = application.getInitParameter("jdbcPassword");
    	
    	ConnectionProvider.setUrl(url);
    	ConnectionProvider.setUser(user);
    	ConnectionProvider.setPassword(password);
    	
    	//3. ContextPath경로 편의 저장
    	String contextPath = application.getContextPath();
    	application.setAttribute("root", contextPath);

    }
	
}
