package passwordchange.core;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.appspot.cloudserviceapi.common.SettingsDBUtils;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import passwordchange.core.UserSecurityQuestion;
import sun.misc.BASE64Encoder;

/**
 * https://parse.com/docs/android_guide#users
 * https://parse.com/docs/android/api/
 * https://parse.com/docs/rest#users
 * 
 */
public class DAO /*implements AbstractDao*/ {
	
	String host = "https://api.parse.com";
//	String host = "http://localhost:4433";
	String PARSE_APPLICATION_ID = SettingsDBUtils.getSettings(Constants.PARSE_APP_ID);
	String PARSE_CLIENT_KEY = SettingsDBUtils.getSettings(Constants.PARSE_MASTER_KEY);
	String objectId;
	String sessionToken;
	
    public static String _jndiUser = "java:/jdbc/caDSR";
    public static String _jndiSystem = "java:/jdbc/caDSRPasswordChange";
//	private Connection conn;
//	private DataSource datasource;
    private static final String  QUESTION_TABLE_NAME = "SBR.USER_SECURITY_QUESTIONS";

    protected static final String SELECT_COLUMNS = "ua_name, question1, answer1, question2, answer2, question3, answer3, date_modified, attempted_count";

    protected static final String PK_CONDITION = "ua_name=?";

    private static final String SQL_INSERT = "INSERT INTO SBREXT.USER_SECURITY_QUESTIONS (ua_name,question1,answer1,question2,answer2,question3,answer3,date_modified, attempted_count) VALUES (?,?,?,?,?,?,?,?,?)";

//    private static String _jndiUser = "java:/jdbc/caDSR";
//    private static String _jndiSystem = "java:/jdbc/caDSRPasswordChange";
    public static String ADMIN_ID = "";
    public static String ADMIN_PASSWORD = "";

    private Logger logger = Logger.getLogger(DAO.class);

//    public DAO(DataSource datasource) {
//    	this.datasource = datasource;
//    }

//    public DAO(Connection conn) {
//    	this.conn = conn;
//    }

    public DAO() {
		// TODO Auto-generated constructor stub
	}

	/**
     * https://developers.google.com/appengine/docs/java/urlfetch/#Secure_Connections_and_HTTPS
     * https://www.parse.com/docs/rest#users-updating
	 * @param username 
	 * @param password 
     * @throws IOException 
     */
    public boolean callParseAPI(String endpoint, String method, String params, UserBean userBean, String username, String password) throws Exception {
    	boolean retVal = false;
    	
    	URL url = new URL(endpoint);
        String query = params;

        //make connection
        HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
        
        urlc.setConnectTimeout(20000);
        urlc.setUseCaches(false);
        urlc.setRequestMethod(method);
        urlc.setRequestProperty("Content-Type", "application/json");   
//        urlc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   
        
        urlc.setAllowUserInteraction(false);
        initParse(urlc, username, password);

        if("POST".equals(method) || "PUT".equals(method)) {
	        //use post mode
	        urlc.setDoOutput(true);	//POST
	        //send query
	        PrintStream ps = new PrintStream(urlc.getOutputStream());
	        ps.print(query);
	        ps.close();
        }

        //get result
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
//        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        
        String l = null;
        while ((l=br.readLine())!=null) {
            System.out.println(l);
        }
        br.close();
        
    	retVal = true;

	    return retVal;
    }

	public boolean checkValidUser(String username) throws Exception {
		boolean retVal = false;

		logger.info ("1 checkValidUser user: " + username);

		if(StringUtils.isEmpty(username)) {
			throw new Exception("username is empty or NULL.");
		}
		
		String params = null;
		params = "username=" + URLEncoder.encode(username);
		retVal = callParseAPI(host + "/1/login", "GET", params, null, ADMIN_ID, ADMIN_PASSWORD);
		
        logger.info("checkValidUser(): " + retVal);
        return retVal;
	}

	public UserBean checkValidUser(String username, String password) throws Exception {
	    UserBean retVal = new UserBean();
	    
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new Exception("username/password is empty or NULL.");
		}
		
		logger.info ("checkValidUser(username, password) user: " + username);

		String params = null;
		params = "username=" + URLEncoder.encode(username);
		callParseAPI(host + "/1/login", "GET", params, retVal, ADMIN_ID, ADMIN_PASSWORD);
	    if (retVal != null) {
	    	// Hooray! The user is logged in.
			retVal.setLoggedIn(true);
		} else {
			// Signup failed. Look at the ParseException to see what happened.
			retVal.setResult(new Result(ResultCode.UNKNOWN_ERROR, retVal.getResult().getMessage()));
	    }
		logger.info("returning isLoggedIn " + retVal.isLoggedIn());        
        return retVal;
	}

	public Result changePassword(String username, String password, String newPassword) {
	    UserBean retVal = new UserBean();

	    logger.info("changePassword  user " + username);
		
		Result result = new Result(ResultCode.UNKNOWN_ERROR);  // (should get replaced)
		boolean isConnectionException = true;  // use to modify returned messages when exceptions are system issues instead of password change issues  
		
		try {
			getConnection(username, password);
			result = resetPassword(username, newPassword);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			if (isConnectionException)
				result = new Result(ResultCode.UNKNOWN_ERROR);  // error not related to user, provide a generic error 
			else
				result = ConnectionUtil.decode(ex);
		}

       logger.info("returning ResultCode " + result.getResultCode().toString());        
       return result;
	}	
		
	public Result resetEmail(String username, String password, String newEmail) {
	    UserBean retVal = new UserBean();

	    logger.info("changeEmail  user " + username);
		
		Result result = new Result(ResultCode.UNKNOWN_ERROR);  // (should get replaced)
		boolean isConnectionException = true;  // use to modify returned messages when exceptions are system issues instead of password change issues  
		
		try {
			getConnection(username, password);
			result = changeEmail(username, newEmail);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			if (isConnectionException)
				result = new Result(ResultCode.UNKNOWN_ERROR);  // error not related to user, provide a generic error 
			else
				result = ConnectionUtil.decode(ex);
		}

       logger.info("returning ResultCode " + result.getResultCode().toString());        
       return result;
	}	
	
//    private ResultSet query( PreparedStatement pstmt, Object[] params) throws SQLException {
//        params( pstmt, params );
//
//        return pstmt.executeQuery();
//    }
//	
//    private void params( PreparedStatement pstmt, Object[] params) throws SQLException {
//        int i = 1;
//        for (Object param : params) {
//            if (param != null) {
//                pstmt.setObject( i++, param );
//            }
//        }
//    }
	
    public void getConnection(String username, String password) throws Exception {
    	URL url = new URL(host + "/1/login?username=" + username + "&password=" + password);
        //make connection
        HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
        
        urlc.setConnectTimeout(20000);
        urlc.setUseCaches(false);
        urlc.setRequestMethod("GET");
        urlc.setRequestProperty("Content-Type", "application/json");   
        
        urlc.setAllowUserInteraction(false);
        initParse(urlc, username, password);

        //get result
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
//        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        
        String l = null; int foundIndex = -1;
        while ((l=br.readLine())!=null) {
            System.out.println(l);
            if((foundIndex = l.indexOf("objectId")) > -1) {
            	objectId = l.substring(foundIndex + "objectId".length() + 3, foundIndex + 21);
            }
            if((foundIndex = l.indexOf("sessionToken")) > -1) {
            	sessionToken = l.substring(foundIndex + "sessionToken".length() + 3, l.length()-2);
            }
        }
        br.close();
        System.out.println("objectId [" + objectId + "] sessionToken [" + sessionToken + "]");
        
        //trying to grab the token:
        /*
			{
			    "username": "j",
			    "createdAt": "2013-05-13T01:07:42.217Z",
			    "updatedAt": "2013-05-13T01:07:42.217Z",
			    "objectId": "kvg6dNFltO",
			    "ACL": {
			        "kvg6dNFltO": {
			            "read": true,
			            "write": true
			        }
			    },
			    "sessionToken": "jttstua92vvnxpspmygdelghi"
			}
         */
    }
    
//    public UserSecurityQuestion findByPrimaryKey( String uaName ) throws Exception {
//        Statement stmt = null;
//        String sql = null;
//        ResultSet rs = null;
//        UserSecurityQuestion q = null;
//        try {
//            sql = "select * from " + QUESTION_TABLE_NAME + " where ua_name = ?";
//
//logger.debug("findByPrimaryKey sql : " + sql);
//			if(conn == null) {
//				conn = datasource.getConnection(ADMIN_ID, ADMIN_PASSWORD);
//			    logger.debug("got DataSource for " + _jndiSystem);
//		    }
//
//            PreparedStatement pstmt = conn.prepareStatement( sql );
//            pstmt.setString(1, uaName);
//			rs = pstmt.executeQuery();
//			int count = 0;
//logger.debug("findByPrimaryKey: " + count);    			
//			if(rs.next()) {
//				q = new UserSecurityQuestion();
//				q.setUaName(rs.getString("ua_name"));
//				q.setQuestion1(rs.getString("question1"));
//				q.setAnswer1(rs.getString("answer1"));
//				q.setQuestion2(rs.getString("question2"));
//				q.setAnswer2(rs.getString("answer2"));
//				q.setQuestion3(rs.getString("question3"));
//				q.setAnswer3(rs.getString("answer3"));
//				//q.setDateModified(new Timestamp());
//			}
//logger.debug("findByPrimaryKey: " + count + " q " + q); 			
//        }
//        catch (SQLException e) {
//            throw new Exception( e );
//        }
//        finally {
//            if (rs != null) try { rs.close(); } catch (SQLException e) { logger.error(e.getMessage()); }
//            if (stmt != null) try { stmt.close(); } catch (SQLException e) { logger.error(e.getMessage()); }
//        	if (conn != null) try { conn.close(); } catch (SQLException e) { logger.error(e.getMessage()); }
//        }
//        return q;
//    }

//    public UserSecurityQuestion findByUaName( String uaName ) throws Exception {
//    	return findByPrimaryKey( uaName );
//    }

//    public UserSecurityQuestion[] findAll( ) throws Exception {
//        Statement stmt = null;
//        ResultSet rs = null;
//        String sql = null;
//        ArrayList<UserSecurityQuestion> qList = new ArrayList<UserSecurityQuestion>();
//        try {
//            sql = "select * from " + QUESTION_TABLE_NAME;
//
//	        conn = datasource.getConnection(ADMIN_ID, ADMIN_PASSWORD);
//            PreparedStatement pstmt = conn.prepareStatement( sql );
//			rs = pstmt.executeQuery();
//			UserSecurityQuestion q = null;
//			while(rs.next()) {
//				q = new UserSecurityQuestion();
//				q.setUaName(rs.getString("ua_name"));
//				q.setQuestion1(rs.getString("question1"));
//				q.setAnswer1(rs.getString("answer1"));
//				q.setQuestion2(rs.getString("question2"));
//				q.setAnswer2(rs.getString("answer2"));
//				q.setQuestion3(rs.getString("question3"));
//				q.setAnswer3(rs.getString("answer3"));
//				//q.setDateModified(new Timestamp());
//				qList.add(q);
//			}
//        }
//        catch (SQLException e) {
//            throw new Exception( e );
//        }
//        finally {
//            if (rs != null) try { rs.close(); } catch (SQLException e) {}
//            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
//        }
//        return toArray(qList);
//    }

    public boolean deleteByPrimaryKey( String uaName ) throws Exception {
    	//no requirement to delete anything

    	return false;
    }

    private void checkMaxLength( String name, String value, int maxLength)
            throws Exception {

        if ( value != null && value.length() > maxLength ) {
            throw new Exception("Value of column '" + name + "' cannot have more than " + maxLength + " chars");
        }
    }
    
//    public void insert( UserSecurityQuestion dto ) throws Exception {
//        PreparedStatement stmt = null;
//
//        try {
//	        conn = datasource.getConnection(ADMIN_ID, ADMIN_PASSWORD);
//            stmt = conn.prepareStatement( SQL_INSERT );
//
//            if ( dto.getUaName() == null ) {
//                throw new Exception("Value of column 'ua_name' cannot be null");
//            }
//            checkMaxLength( "ua_name", dto.getUaName(), 30 );
//            stmt.setString( 1, dto.getUaName() );
//
//            if ( dto.getQuestion1() == null ) {
//                throw new Exception("Value of column 'question1' cannot be null");
//            }
//            checkMaxLength( "question1", dto.getQuestion1(), 500 );
//            stmt.setString( 2, dto.getQuestion1() );
//
//            if ( dto.getAnswer1() == null ) {
//                throw new Exception("Value of column 'answer1' cannot be null");
//            }
//            checkMaxLength( "answer1", dto.getAnswer1(), 500 );
//            stmt.setString( 3, dto.getAnswer1() );
//
//            if ( dto.getQuestion2() == null ) {
//                throw new Exception("Value of column 'question2' cannot be null");
//            }
//            checkMaxLength( "question2", dto.getQuestion2(), 500 );
//            stmt.setString( 4, dto.getQuestion2() );
//
//            if ( dto.getAnswer2() == null ) {
//                throw new Exception("Value of column 'answer2' cannot be null");
//            }
//            checkMaxLength( "answer2", dto.getAnswer2(), 500 );
//            stmt.setString( 5, dto.getAnswer2() );
//
//            if ( dto.getQuestion3() == null ) {
//                throw new Exception("Value of column 'question3' cannot be null");
//            }
//            checkMaxLength( "question3", dto.getQuestion3(), 500 );
//            stmt.setString( 6, dto.getQuestion3() );
//
//            if ( dto.getAnswer3() == null ) {
//                throw new Exception("Value of column 'answer3' cannot be null");
//            }
//            checkMaxLength( "answer3", dto.getAnswer3(), 500 );
//            stmt.setString( 7, dto.getAnswer3() );
//
//            if ( dto.getDateModified() == null ) {
//                dto.setDateModified( new Date( System.currentTimeMillis()));
//            }
//            stmt.setDate( 8, dto.getDateModified() );
//
//            int n = stmt.executeUpdate();
//        }
//        catch (SQLException e) {
//            throw new Exception( e );
//        }
//        finally {
//            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
//        }
//    }

//    public boolean update( String uaName, UserSecurityQuestion dto ) throws Exception {
//        StringBuffer sb = new StringBuffer();
//        ArrayList<Object> params = new ArrayList<Object>();
//
//        if ( dto.getUaName() != null ) {
//            checkMaxLength( "ua_name", dto.getUaName(), 30 );
//            sb.append( "ua_name=?" );
//            params.add( dto.getUaName());
//        }
//
//        if ( dto.getQuestion1() != null ) {
//            if (sb.length() > 0) {
//                sb.append( ", " );
//            }
//
//            checkMaxLength( "question1", dto.getQuestion1(), 500 );
//            sb.append( "question1=?" );
//            params.add( dto.getQuestion1());
//        }
//
//        if ( dto.getAnswer1() != null ) {
//            if (sb.length() > 0) {
//                sb.append( ", " );
//            }
//
//            checkMaxLength( "answer1", dto.getAnswer1(), 500 );
//            sb.append( "answer1=?" );
//            params.add( dto.getAnswer1());
//        }
//
//        if ( dto.getQuestion2() != null ) {
//            if (sb.length() > 0) {
//                sb.append( ", " );
//            }
//
//            checkMaxLength( "question2", dto.getQuestion2(), 500 );
//            sb.append( "question2=?" );
//            params.add( dto.getQuestion2());
//        }
//
//        if ( dto.getAnswer2() != null ) {
//            if (sb.length() > 0) {
//                sb.append( ", " );
//            }
//
//            checkMaxLength( "answer2", dto.getAnswer2(), 500 );
//            sb.append( "answer2=?" );
//            params.add( dto.getAnswer2());
//        }
//
//        if ( dto.getQuestion3() != null ) {
//            if (sb.length() > 0) {
//                sb.append( ", " );
//            }
//
//            checkMaxLength( "question3", dto.getQuestion3(), 500 );
//            sb.append( "question3=?" );
//            params.add( dto.getQuestion3());
//        }
//
//        if ( dto.getAnswer3() != null ) {
//            if (sb.length() > 0) {
//                sb.append( ", " );
//            }
//
//            checkMaxLength( "answer3", dto.getAnswer3(), 500 );
//            sb.append( "answer3=?" );
//            params.add( dto.getAnswer3());
//        }
//
//        if (sb.length() > 0) {
//            sb.append( ", " );
//        }
//        
//        if ( dto.getAttemptedCount() == null ) {
//            sb.append( "attempted_count=NULL" );
//        }
//        else {
//            sb.append( "attempted_count=?" );
//            params.add( dto.getAttemptedCount());
//        }
//        
//        if (sb.length() == 0) {
//            return false;
//        }
//
//        params.add( uaName );
//
//        Object[] oparams = new Object[ params.size() ];
//
//        return updateOne( sb.toString(), PK_CONDITION, params.toArray( oparams ));
//    }

//    private boolean updateOne( String setstring, String cond, Object... params) throws Exception {
//        int ret = executeUpdate( getUpdateSql( setstring, cond ), params );
//
//        if (ret > 1) {
//            throw new Exception("More than one record updated");
//        }
//
//        return ret == 1;
//    }
    
//    private int executeUpdate( String sql, Object... params) throws Exception {
//        Statement stmt = null;
//
//        try {
//            if (params != null && params.length > 0) {
//    	        if(conn == null) {
//    	        conn = datasource.getConnection(ADMIN_ID, ADMIN_PASSWORD);
//    	        }
//                PreparedStatement pstmt = conn.prepareStatement( sql );
//                stmt = pstmt;
//
//                params( pstmt, params);
//
//                return pstmt.executeUpdate();
//            }
//            else {
//                stmt = conn.createStatement();
//
//                return stmt.executeUpdate( sql );
//            }
//        }
//        catch (SQLException e) {
//            throw new Exception( e );
//        }
//        finally {
//            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
//        }
//    }
    
    private String getUpdateSql(String setstring, String cond) {
        return getUpdateSql(setstring) + getSqlCondition( cond );
    }

    /**
     * Returns the condition starting with " WHERE " or an empty string.
     */
    private String getSqlCondition(String cond) {
        return cond != null && cond.length() > 0 ? (" WHERE " + cond) : "";
    }

    private String getUpdateSql(String setstring) {
        return "UPDATE " + QUESTION_TABLE_NAME + " SET " + setstring;
    }
    
    
//    private UserSecurityQuestion fetch( ResultSet rs ) throws SQLException {
//        UserSecurityQuestion dto = new UserSecurityQuestion();
//        dto.setUaName( rs.getString( 1 ));
//        dto.setQuestion1( rs.getString( 2 ));
//        dto.setAnswer1( rs.getString( 3 ));
//        dto.setQuestion2( rs.getString( 4 ));
//        dto.setAnswer2( rs.getString( 5 ));
//        dto.setQuestion3( rs.getString( 6 ));
//        dto.setAnswer3( rs.getString( 7 ));
//        dto.setDateModified( rs.getDate( 8 ));
//
//        return dto;
//    }

    private UserSecurityQuestion[] toArray(ArrayList<UserSecurityQuestion> list ) {
        UserSecurityQuestion[] ret = new UserSecurityQuestion[ list.size() ];
        return list.toArray( ret );
    }

    private void initParse(HttpURLConnection urlc, String username, String password) throws Exception {
		if(PARSE_APPLICATION_ID == null || PARSE_APPLICATION_ID.indexOf("${") > -1 ||
				PARSE_CLIENT_KEY == null || PARSE_CLIENT_KEY.indexOf("${") > -1) {
			throw new Exception("PARSE_APPLICATION_ID and/or PARSE_CLIENT_KEY is not initialized properly!");
		}
		System.out.println("PARSE_APPLICATION_ID [" + PARSE_APPLICATION_ID + "] PARSE_CLIENT_KEY [" + PARSE_CLIENT_KEY + "]");
    	//=== begin
		urlc.setRequestProperty("X-Parse-Application-Id", PARSE_APPLICATION_ID);
		urlc.setRequestProperty("X-Parse-REST-API-Key", PARSE_CLIENT_KEY);
		//=== end
		if(sessionToken != null) {
			 urlc.setRequestProperty("X-Parse-Session-Token", sessionToken);
			 System.out.println("X-Parse-Session-Token set to [" + sessionToken + "]");
		}

		//CommonUtil.setBasicAuth(urlc, username, password);
    }
    
	public Result resetPassword(String username, String newPassword) throws Exception {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(newPassword)) {
			throw new Exception("Username/password is NULL or empty.");
		}
		
	    UserBean retVal = new UserBean();

		logger.info("resetPassword  user " + username);
		
		Result result = new Result(ResultCode.UNKNOWN_ERROR);  // (should get replaced)
		boolean isConnectionException = true;  // use to modify returned messages when exceptions are system issues instead of password change issues  
		
		try {
			//this is assuming that a proper admin level account has been logged in with Parse.initialize() call
			logger.debug("connected");
			String params = null;
			params = "{\"password\":\"" + newPassword + "\"}";
//			params = "{\"phone\":\"415-369-6201\"}";
			String target = host + "/1/users/" + objectId;
			System.out.println("resetPassword parse url [" + target + "]");
			callParseAPI(target, "PUT", params, retVal, ADMIN_ID, ADMIN_PASSWORD);
		    if (retVal != null) {
		        isConnectionException = false;
				logger.debug("connected");
				logger.debug("attempted to alter user " + username);
				result = new Result(ResultCode.PASSWORD_CHANGED);
		    }
//	        isConnectionException = false;
//		
//			// can't use parameters with PreparedStatement and "alter user", create a single string
//	        // (must quote password to retain capitalization for verification function)
//	        
//	        result = new Result(ResultCode.PASSWORD_CHANGED);
			System.out.println("$$$ resetPassword username [" + username + "] password changed to [" + newPassword + "] $$$");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(ex.getMessage());
				if (isConnectionException)
					result = new Result(ResultCode.UNKNOWN_ERROR);  // error not related to user, provide a generic error 
				else
					result = ConnectionUtil.decode(ex);
		}

       return result;
	}	
	
	private Result changeEmail(String username, String newEmail) throws Exception {
		Result result = new Result(ResultCode.UNKNOWN_ERROR);  // (should get replaced)
		boolean isConnectionException = true;  // use to modify returned messages when exceptions are system issues instead of password change issues  

		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(newEmail)) {
			throw new Exception("Username/email is NULL or empty.");
		}
		
	    UserBean retVal = new UserBean();

		logger.info("changeEmail  user " + username);
				
		try {
			//this is assuming that a proper admin level account has been logged in with Parse.initialize() call
			logger.debug("connected");
			String params = null;
			params = "{\"email\":\"" + newEmail + "\"}";
//			params = "{\"phone\":\"415-369-6201\"}";
			String target = host + "/1/users/" + objectId;
			System.out.println("changeEmail parse url [" + target + "]");
			callParseAPI(target, "PUT", params, retVal, ADMIN_ID, ADMIN_PASSWORD);
		    if (retVal != null) {
		        isConnectionException = false;
				logger.debug("connected");
				logger.debug("attempted to alter user " + username);
				result = new Result(ResultCode.EMAIL_CHANGED);
		    }
			System.out.println("$$$ changeEmail username [" + username + "] email changed to [" + newEmail + "] $$$");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(ex.getMessage());
				if (isConnectionException)
					result = new Result(ResultCode.UNKNOWN_ERROR);  // error not related to user, provide a generic error 
				else
					result = ConnectionUtil.decode(ex);
		}

       return result;
	}	
}	
