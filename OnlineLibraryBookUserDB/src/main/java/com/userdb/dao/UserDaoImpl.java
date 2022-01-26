package com.userdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userdb.model.UserAuthority;
import com.userdb.model.UserDimention;

@Repository
public class UserDaoImpl implements UserDao {

	private final NamedParameterJdbcOperations jdbcTemplate;

	@Autowired
	public UserDaoImpl(@Qualifier("namedJDBCTemplate") NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = Objects.requireNonNull(jdbcTemplate, "jdbcTemplate");
	}

	private static final String SQL_GET_USER_COUNT = "SELECT COUNT(*) AS CNT FROM user_login WHERE rec_lgcl_del_ind = 'N'";

	private static final String SQL_GET_USER_BY_USERNAME = "SELECT UL.firstName, UL.lastName, UL.username, UL.password, UL.enabled, "
			+ " UL.expired, UA.authority " 
			+ " FROM user_login UL JOIN user_auth UA " 
			+ " ON UL.ID = UA.ID "
			+ " WHERE UL.rec_lgcl_del_ind = 'N' AND UL.username = :userName";
	
	private static final String SQL_GET_USER_INFORMATION_BY_USER_NAME = " SELECT UI.fav_genres, UI.avg_reading_hrs_daily "
			+ " FROM user_login UL LEFT JOIN user_info UI "
			+ " ON UL.id = UI.id "
			+ " AND UL.rec_lgcl_del_ind = 'N' "
			+ " WHERE UL.username = :userName";

	private static final String SQL_UPDATE_PASSWORD_BY_USERNAME = " MERGE INTO user_login dest "
			+ " USING( SELECT :userName userName " + " :password password, " + " FROM DUAL) src "
			+ " ON(src.userName = dest.username" + " AND dest.rec_lgcl_del_ind = 'N')"
			+ " WHEN MATCHED THEN UPDATE dest.password = src.password ";

	private static final String SQL_REGISTER_NEW_USER = "INSERT INTO user_login (firstName, lastName, username, password, enabled, expired, rec_lgcl_del_ind)"
			+ " VALUES(:firstName, :lastName, :username, :password, :enabled, :expired, 'N')";

	private static final String SQL_REGISTER_NEW_USER_AUTHORITIES = "INSERT INTO user_auth VALUES(:id, :authority)";

	private static final String SQL_REGISTER_NEW_USER_INFORMATION = "INSERT INTO user_info VALUES(:id, :favGenres, :readHrs)";

	private static final String SQL_DELETE_USER_BY_USERNAME = " UPDATE user_login SET rec_lgcl_del_ind ='Y' WHERE username = :userName";

	private static final String SQL_GET_USER_ID_BY_USER_NAME = "SELECT id FROM user_login WHERE username = :userName AND rec_lgcl_del_ind = 'N'";

	@Override
	public int getUserCount() {
		List<Integer> counts = jdbcTemplate.query(SQL_GET_USER_COUNT, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("CNT");
			}
		});

		return counts.get(0);
	}

	@Override
	public int registerUser(UserDimention userInfo) {
		long userId = persistUserLogin(userInfo);
		int infoCount = persistsUserInformation(userInfo, userId);
		int authoriesCount = persistUserAuthorities(userInfo, userId);

		return infoCount + authoriesCount + 1;
	}

	@Override
	public UserDimention getUserInformation(String userName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", userName);
		List<Map<String, Object>> result = jdbcTemplate.queryForList(SQL_GET_USER_BY_USERNAME, parameters);
		UserDimention user = null;
		if (result.size() > 0) {
			user = extractUSerDimention(result);
			Map<String, Object> extraInformation = getUserExtraInformation(userName);
			user.setFavoriteGenres(String.valueOf(extraInformation.get("fav_genres")));
			user.setAvgReadingHrsDaily(Integer.valueOf(String.valueOf(extraInformation.get("avg_reading_hrs_daily"))));
		}
		return user;
	}

	@Override
	public int deleteUser(String userId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", userId);
		int updateCount = jdbcTemplate.update(SQL_DELETE_USER_BY_USERNAME, parameters);
		return updateCount;
	}

	@Override
	public int updateUser(UserDimention user) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", user.getUsername());
		parameters.put("password", user.getPassword());
		int updateCount = jdbcTemplate.update(SQL_UPDATE_PASSWORD_BY_USERNAME, parameters);
		return updateCount;
	}

	private UserDimention extractUSerDimention(List<Map<String, Object>> result) {
		UserDimention userDimention = new UserDimention();
		userDimention.setFirstName(String.valueOf(result.get(0).get("firstName")));
		userDimention.setLastName(String.valueOf(result.get(0).get("lastName")));
		userDimention.setUsername(String.valueOf(result.get(0).get("username")));
		userDimention.setPassword(String.valueOf(result.get(0).get("password")));
		userDimention.setExpired(Integer.valueOf(String.valueOf(result.get(0).get("expired")))==1?true:false);
		userDimention.setEnabled(Integer.valueOf(String.valueOf(result.get(0).get("enabled")))==1?true:false);
		
		List<UserAuthority> authorities =new ArrayList<>();
		for(Map<String, Object> row: result) {
			UserAuthority authority = new UserAuthority();
			authority.setAuthority(String.valueOf(row.get("authority")));
		}
		userDimention.setAuthorities(authorities);
		return userDimention;
	}
	
	private Map<String, Object> getUserExtraInformation(String userName){
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", userName);
		Map<String, Object> result = jdbcTemplate.queryForMap(SQL_GET_USER_INFORMATION_BY_USER_NAME, parameters);
		
		return result;
	}

	private int persistUserAuthorities(UserDimention userInformation, long userId) {
		Map<String, Object>[] parameters = prepareBatches(userInformation, userId);
		int[] updateCount = jdbcTemplate.batchUpdate(SQL_REGISTER_NEW_USER_AUTHORITIES, parameters);
		return updateCount.length;
	}

	private Map<String, Object>[] prepareBatches(UserDimention userInformation, long userId) {
		Map<String, Object>[] parameters = new Map[userInformation.getAuthorities().size()];
		int counter =0;
		for (UserAuthority auth : userInformation.getAuthorities()) {
			Map<String, Object> param = new HashMap<>();
			param.put("id", userId);
			param.put("authority", auth.getAuthority());
			parameters[counter++] = param;
		}
		return parameters;
	}

	private int persistsUserInformation(UserDimention userInformation, long userId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", userId);
		parameters.put("favGenres", userInformation.getFavoriteGenres());
		parameters.put("readHrs", userInformation.getAvgReadingHrsDaily());
		int updateCount = jdbcTemplate.update(SQL_REGISTER_NEW_USER_INFORMATION, parameters);
		return updateCount;
	}

	private long persistUserLogin(UserDimention userInformation) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("firstName", userInformation.getFirstName());
		parameters.put("lastName", userInformation.getLastName());
		parameters.put("username", userInformation.getUsername());
		parameters.put("password", userInformation.getPassword());
		parameters.put("enabled", 1);
		parameters.put("expired", 0);

		int insertCount = jdbcTemplate.update(SQL_REGISTER_NEW_USER, parameters);
		Long userId = 0l;
		if (insertCount > 0) {
			parameters.clear();
			parameters.put("userName", userInformation.getUsername());

			userId = jdbcTemplate.queryForObject(SQL_GET_USER_ID_BY_USER_NAME, parameters, new RowMapper<Long>() {

				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return Long.decode(rs.getString("id"));
				}
			});
		}
		return userId;
	}

}
