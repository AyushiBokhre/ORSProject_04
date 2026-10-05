package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel extends BaseModel<UserBean> {

	private static Logger log = Logger.getLogger(UserModel.class);

	@Override
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("UserModel add() method started");

		Connection conn = null;
		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			log.warn("User already exists with login: " + bean.getLogin());
			throw new DuplicateRecordException("Login Id already exists");
		}

		try {

			log.debug("Getting database connection for adding user");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnSuccessfulLogin());
			pstmt.setString(10, bean.getGender());
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getLock());
			pstmt.setString(13, bean.getRegisteredIP());
			pstmt.setString(14, bean.getLastLoginIP());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setString(19, bean.getPhoto());

			pstmt.executeUpdate();
			conn.commit();

			log.info("User added successfully with login: " + bean.getLogin());

		} catch (SQLException e) {
			log.error("Exception while adding user: " + bean.getLogin(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after add()");
		}

		log.info("UserModel add() method ended");

		return bean.getId();
	}

	@Override
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("UserModel update() method started for user ID: " + bean.getId());

		Connection conn = null;
		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			log.warn("Duplicate login found while updating user: " + bean.getLogin());
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {

			log.debug("Getting database connection for updating user");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setInt(8, bean.getUnSuccessfulLogin());
			pstmt.setString(9, bean.getGender());
			pstmt.setTimestamp(10, bean.getLastLogin());
			pstmt.setString(11, bean.getLock());
			pstmt.setString(12, bean.getRegisteredIP());
			pstmt.setString(13, bean.getLastLoginIP());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " record updated");

			conn.commit(); // End transaction

			log.info("User updated successfully with ID: " + bean.getId());

			pstmt.close();

		} catch (SQLException e) {
			log.error("Exception while updating user ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after update()");
		}
	}

	public UserBean findByLogin(String login) throws ApplicationException {

		log.debug("Searching user by login: " + login);

		UserBean bean = findByUniqueColumn("login", login);

		if (bean != null) {
			log.info("User found with login: " + login);
		} else {
			log.warn("No user found with login: " + login);
		}

		return bean;
	}

	public UserBean authenticate(String login, String password) throws ApplicationException {

		log.info("Authentication started for login: " + login);

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(password)) {

			log.info("User authentication successful for login: " + login);

			return bean;

		} else {

			log.warn("User authentication failed for login: " + login);

			return null;
		}
	}

	@Override
	public String getWhereClause(UserBean bean) {

		log.debug("UserModel getWhereClause() method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}

			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}

			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}

			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append(" and dob like '" + new java.sql.Date(bean.getDob().getTime()) + "%'");
			}

		}

		log.debug("Generated UserModel where clause: " + sql.toString());

		return sql.toString();
	}

	public void updatePhoto(long id, String photo) throws ApplicationException {

		log.info("UserModel updatePhoto() started for user ID: " + id);

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_USER SET PHOTO = ? WHERE ID = ?");

			pstmt.setString(1, photo);
			pstmt.setLong(2, id);

			pstmt.executeUpdate();

			conn.commit(); // End transaction

			log.info("User photo updated successfully for user ID: " + id);

			pstmt.close();

		} catch (Exception e) {

			log.error("Exception while updating photo for user ID: " + id, e);

			try {
				conn.rollback();
			} catch (Exception ex) {

				log.error("Exception during updatePhoto rollback for user ID: " + id, ex);

				throw new ApplicationException("Exception : updatePhoto rollback exception " + ex.getMessage());
			}

			throw new ApplicationException("Exception in updating User Photo");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after updatePhoto()");
		}
	}

	@Override
	public String getTable() {

		log.debug("UserModel getTable() called");

		return "st_user";
	}

	@Override
	public UserBean getBean() {

		log.debug("UserModel getBean() called");

		return new UserBean();
	}

	public UserBean changePassword(String newPassword, String oldPassword, String login) {

		log.info("Change password process started for login: " + login);

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(oldPassword)) {

			log.info("Old password verified for login: " + login);

			bean.setPassword(newPassword);
			update(bean);

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Password changed successfully and email sent to: " + login);

			return bean;

		}

		log.warn("Password change failed for login: " + login);

		return null;
	}

	public UserBean forgetPassword(String login) {

		log.info("Forget password process started for login: " + login);

		UserBean bean = findByLogin(login);

		if (bean != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Forget password email sent successfully to: " + login);

			return bean;
		}

		log.warn("No user found for forget password request: " + login);

		return null;
	}

	public long register(UserBean bean) {

		log.info("User registration started for login: " + bean.getLogin());

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		// map.put("firstName", bean.getFirstName());
		// map.put("lastName", bean.getLastName());

		msg.setTo(map.get("login"));
		msg.setSubject("User Registration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		System.out.println("mail send successfully.");

		log.info("User registration completed successfully for login: " + bean.getLogin());

		return pk;
	}

}

