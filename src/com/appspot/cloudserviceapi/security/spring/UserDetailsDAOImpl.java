package com.appspot.cloudserviceapi.security.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.appengine.api.datastore.Key;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gdata.data.ExtensionDescription.Default;

/**
 * https://www.javacodegeeks.com/2012/10/spring-security-prevent-brute-force.
 * html
 */
public class UserDetailsDAOImpl implements UserDetailsDAO {

//	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USERS SET accountNonLocked = ? WHERE username = ?";
//	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USERS WHERE username = ?";
//
//	private static final String SQL_USER_ATTEMPTS_GET = "SELECT * FROM USER_ATTEMPTS WHERE username = ?";
//	private static final String SQL_USER_ATTEMPTS_INSERT = "INSERT INTO USER_ATTEMPTS (USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?)";
//	private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = attempts + 1, lastmodified = ? WHERE username = ?";
//	private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = 0, lastmodified = null WHERE username = ?";

	public static final int MAX_ATTEMPTS = 5;

	private GaeCache attempts;
	private int allowedNumberOfAttempts;

	// @Autowired
	// private DataSource dataSource;
	//
	// @PostConstruct
	UserDetailsDAOImpl() {
		// setDataSource(dataSource);
		int time = 5; // 'account block configured for $time minutes
		// TODO use
		// http://www.ehcache.org/documentation/2.8/integrations/googleappengine.html
		// !!!
//		attempts = (LoadingCache) CacheBuilder.newBuilder().maximumSize(3).expireAfterAccess(time, TimeUnit.SECONDS).build();
		attempts = new GaeCache();
	}

	@Override
	public void updateFailAttempts(String username) {

//		UserAttempts user = null;
//		user = getUserAttempts(username);
//		if (user == null) {
//			if (isUserExists(username)) {
				// if no record, insert a new
				// getJdbcTemplate().update(SQL_USER_ATTEMPTS_INSERT, new
				// Object[] { username, 1, new Date() });

				try {
					attempts.put(username, attempts.get(username) + 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
//		} else {
//
//			if (isUserExists(username)) {
//				// update attempts count, +1
//				// getJdbcTemplate().update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS,
//				// new Object[] { new Date(), username });
//				try {
//					attempts.put(username, attempts.get(username) + 1);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}

			if (attempts.get(username) + 1 >= MAX_ATTEMPTS) {
				// locked user
				// getJdbcTemplate().update(SQL_USERS_UPDATE_LOCKED, new
				// Object[] { false, username });
				// throw exception
				GaeUserDetails user = loadUserByUsername(username);
				int attempted = attempts.get(username);
				if(user.isAccountNonLocked() && attempted < MAX_ATTEMPTS) {
					user.setAccountNonLocked(false);  //lock the account! THIS IS NOT OPTIMIZED - IT IS INVOKED EVEN IF IT IS ALREADY LOCKED!!!
					saveUser(user);
				}
				throw new LockedException("User account [" + username + "] is locked!");
			}

//		}

	}

	@Override
//	public UserAttempts getUserAttempts(String username) {
	public GaeCache getUserAttempts() {

		try {

//			UserAttempts userAttempts = null; // getJdbcTemplate().queryForObject(SQL_USER_ATTEMPTS_GET,
			// new Object[] { username }, new RowMapper<UserAttempts>() {
			// public UserAttempts mapRow(ResultSet rs, int rowNum) throws
			// SQLException {
			//
			// UserAttempts user = new UserAttempts();
			// user.setId(rs.getInt("id"));
			// user.setUsername(rs.getString("username"));
			// user.setAttempts(rs.getInt("attempts"));
			// user.setLastModified(rs.getDate("lastModified"));
			//
			// return user;
			// }
			//
			// });

//			return userAttempts;
			return attempts;

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void resetFailAttempts(String username) {
		// getJdbcTemplate().update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new
		// Object[] { username });

		attempts.invalidate(username);
	}

	public GaeUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		GaeUserDetails d = (new UserSecurityDAO()).findUserDetailsByLoginId(username);
		if (d == null) {
			throw new UsernameNotFoundException("Invalid user credentials");
		}
		return d;
	}

	private void saveUser(GaeUserDetails user)
			throws UsernameNotFoundException {
		(new UserSecurityDAO()).save(user);
	}

	private boolean isUserExists(String username) {
		boolean result = false;
		int count = -1;
		GaeUserDetails t;
		try {
			t = (new UserSecurityDAO()).findUserDetailsByLoginId(username);
			if (t != null)
				result = true;
		} catch (Exception e) {
			result = false;
		}
		// int count = getJdbcTemplate().queryForObject(SQL_USERS_COUNT, new
		// Object[] { username }, Integer.class);
		// if (count > 0) {
		// result = true;
		// }

		return result;
	}

}