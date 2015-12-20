package wfs.l2t.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wfs.l2t.dto.dtoAccount;
import wfs.l2t.utility.Md5Utility;

public class ModelAccount extends Model {

	private Md5Utility md5;

	public ModelAccount() {
		super();
		md5 = new Md5Utility();
	}

	public dtoAccount getAccount(String email) {
		dtoAccount account = new dtoAccount();
		String sql = "select * from `account` where `Email` = ?";
		if (connection.connect()) {
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, email.toLowerCase());
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				try {
					if (rs.next() != false) {
						account.accountId = rs.getString("AccountId");
						account.userName = rs.getString("Username");
						account.email = rs.getString("Email").toLowerCase();
						account.password = rs.getString("Password");
						account.accountType = rs.getString("AccountType");
						account.timeReceiveEmail = rs
								.getString("TimeReceiveEmail");
						account.numberReceiveEmail = rs
								.getString("NumberReceiveEmail");
						account.isActive = rs.getInt("IsActive");
						account.confirmCode = rs.getString("ConfirmCode");
						account.avatar = rs.getString("Avatar");
					} else
						return null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			connection.close();
		}
		return account;
	}

	public boolean checkConfirmCode(int accountId, String code) {
		String sql = "select * from `account` where `ConfirmCode` = ? and `AccountId` = ?";
		if (connection.connect()) {
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, code);
				stm.setInt(2, accountId);
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				try {
					if (rs.next() == false)
						return false;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			connection.close();
		}
		return true;
	}

	public void updateActivation(int accountId, int status) {
		String sql = "update `account` set `IsActive` = ? where `AccountId` = ?";
		if (connection.connect()) {
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setInt(1, status);
				stm.setInt(2, accountId);
				connection.setPrepareStatement(stm);
				connection.writeSecure();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			connection.close();
		}
	}

	public void addAccount(dtoAccount account) {
		String sql = "insert into `account` (`UserName`, `Email`, `Password`, `AccountType`, `TimeReceiveEmail`, `NumberReceiveEmail`, `IsActive`,`ConfirmCode`,`Avatar`,`Token`) values (?,?,?,?,?,?,?,?,?,?)";
		if (connection.connect()) {
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, account.userName);
				stm.setString(2, account.email);
				stm.setString(3, md5.md5(account.password));
				stm.setString(4, account.accountType);
				stm.setString(5, account.timeReceiveEmail);
				stm.setString(6, account.numberReceiveEmail);
				stm.setInt(7, account.isActive);
				stm.setString(8, account.confirmCode);
				stm.setString(9, account.avatar);
				stm.setString(10, account.token);
				connection.setPrepareStatement(stm);
				connection.writeSecure();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getAccountId(String email) {
		String accountId = "";
		String sql = "select `AccountId` from `account` where `Email` = ?";
		if (this.connection.connect()) {
			PreparedStatement stm;
			try {
				stm = connection.getConnection().prepareStatement(sql);
				stm.setString(1, email);
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				rs.next();
				accountId = rs.getString("AccountId");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
			this.connection.close();
		}
		return accountId;
	}

	public String getLoginToken(String userId) {
		dtoAccount ac = this.getAccountById(userId);
		return ac.token;

	}

	public dtoAccount getAccountById(String id) {
		dtoAccount account = new dtoAccount();
		String sql = "select * from `account` where `AccountId` = " + id;
		if (this.connection.connect()) {
			ResultSet rs = this.connection.read(sql);
			try {
				rs.next();
				account.accountId = rs.getString("AccountId");
				account.userName = rs.getString("Username");
				account.email = rs.getString("Email");
				account.password = rs.getString("Password");
				account.accountType = rs.getString("AccountType");
				account.timeReceiveEmail = rs.getString("TimeReceiveEmail");
				account.numberReceiveEmail = rs.getString("NumberReceiveEmail");
				account.confirmCode = rs.getString("ConfirmCode");
				account.avatar = rs.getString("Avatar");
				account.token = rs.getString("Token");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.connection.close();
		}
		return account;
	}

	public Boolean setReceiveEmailTime(String userId, String time) {
		if (this.connection.connect()) {
			String sql = "UPDATE `account` SET `TimeReceiveEmail`='" + time
					+ "' WHERE `AccountId`=" + userId;
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	public Boolean setToken(String id, String token) {

		if (this.connection.connect()) {
			String sql = "UPDATE `account` SET `Token`='" + token
					+ "' WHERE `AccountId`=" + id;
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	public Boolean setReceiveEmailNumber(String userId, String number) {
		if (this.connection.connect()) {
			String sql = "UPDATE `account` SET `NumberReceiveEmail`='" + number
					+ "' WHERE `AccountId`=" + userId;
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	public Boolean updatePassword(String userId, String password) {
		if (this.connection.connect()) {
			String sql = "UPDATE `account` SET `Password`='"
					+ md5.md5(password) + "' WHERE `AccountId`=" + userId;
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	public Boolean changeAvatar(String id, String avatar) {
		String sql = "UPDATE `account` SET `Avatar`='" + avatar
				+ "' WHERE `AccountId`=" + id;
		if (this.connection.connect()) {
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	public Boolean hasSavedJob(String userId)
	{
		Boolean ret = false;
		if(connection.connect())
		{
			String sql = "SELECT count(*) as `total` FROM `job_recommended` WHERE `Save` = 1 and `AccountId` = " + userId;
			ResultSet rs = connection.read(sql);
			try {
				rs.next();
				ret = rs.getInt("total") > 0;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			connection.close();
		}
		return ret;
	}
	
	public Boolean changeUserName(String id, String name) {
		String sql = "UPDATE `account` SET `UserName`='" + name
				+ "' WHERE `AccountId`=" + id;
		if (this.connection.connect()) {
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	
}
