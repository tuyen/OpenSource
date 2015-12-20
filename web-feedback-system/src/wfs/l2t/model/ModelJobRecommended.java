package wfs.l2t.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wfs.l2t.dto.dtoJobRecommended;

public class ModelJobRecommended extends Model {
	public ModelJobRecommended() {
		super();
	}

	/**
	 * count the number of job was rated
	 * @param userId
	 * @return
	 */
	public String countJobRated(String userId) {
		String sql = "SELECT COUNT(*) as NumRate FROM `job_recommended` WHERE job_recommended.AccountId = ? AND Rating > 0";
		if(connection.connect()){
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, userId);				
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				if(rs.next())
					return rs.getString("NumRate");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			connection.close();
		}
		return "0";
	}

	/**
	 * insert a job into job_recommended table
	 * 
	 * @param jobRec
	 */
	public void add(dtoJobRecommended jobRec) {
		if (connection.connect()) {
			String sql = "insert into `job_recommended` value(?,?,?,?,?,?)";
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, jobRec.accountId);
				stm.setString(2, jobRec.jobId);
				stm.setString(3, jobRec.save);
				stm.setString(4, jobRec.rating);
				stm.setString(5, jobRec.seen);
				stm.setTimestamp(6, jobRec.time);
				connection.setPrepareStatement(stm);
				connection.writeSecure();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			connection.close();
		}
	}

	/**
	 * update a job in job_recommended table
	 * 
	 * @param jobRec
	 *            dtoJobRecommended
	 */
	public void update(dtoJobRecommended jobRec) {
		if (connection.connect()) {
			String sql = "update `job_recommended` set Save = ?, Time = ?, Rating = ?, Seen = ? where `AccountId` = ? and `JobId` = ?";
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, jobRec.save);
				stm.setTimestamp(2, jobRec.time);
				stm.setString(3, jobRec.rating);
				stm.setString(4, jobRec.seen);
				stm.setString(5, jobRec.accountId);
				stm.setString(6, jobRec.jobId);

				connection.setPrepareStatement(stm);
				connection.writeSecure();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			connection.close();
		}
	}

	/**
	 * check whether a user have a job recommended or not
	 * 
	 * @param jobId
	 *            String
	 * @param accountId
	 *            String
	 * @return boolean
	 */
	public boolean checkIfExist(String jobId, String accountId) {
		if (connection.connect()) {
			String sql = "select * from `job_recommended` where `AccountId` = ? and `JobId` = ?";
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, accountId);
				stm.setString(2, jobId);
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				if (rs.next())
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			connection.close();
		}
		return false;
	}

}
