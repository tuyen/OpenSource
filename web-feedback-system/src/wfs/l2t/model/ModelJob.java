package wfs.l2t.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wfs.l2t.dto.dtoJob;

public class ModelJob extends Model {
	public ModelJob() {
		super();
	}
	
	public dtoJob getJob(String jobId) {
		String sql = "select * from job where JobId = " + jobId;
		dtoJob job = new dtoJob();
		if (connection.connect()) {
			ResultSet rs = connection.read(sql);
			try {
				while (rs.next()) {
					job.jobId = rs.getString("JobId");
					job.categoryId = rs.getString("CategoryId");
					job.accountId = rs.getString("AccountId");
					job.jobName = trimAll(rs.getString("JobName"));
					job.location = trimAll(rs.getString("Location"));
					job.salary = trimAll(rs.getString("Salary").replace(
							"Lương:", ""));
					job.description = trimAll(rs.getString("Description"));
					job.tags = trimAll(rs.getString("Tags"));
					job.requirement = trimAll(rs.getString("Requirement"));
					job.benifit = trimAll(rs.getString("Benifit"));
					job.expired = trimAll(rs.getString("Expired"));
					job.source = trimAll(rs.getString("Source"));
					job.company = trimAll(rs.getString("Company"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return job;
	}

	public List<dtoJob> getJob(int offset, String userId) {
		List<dtoJob> jobList = new ArrayList<dtoJob>();		 
		 
		String sql = "select job.AccountId, job.JobId, JobName, Location, Salary, job.Description, Tags, Requirement, Benifit, Expired, Source, Company, COALESCE(Save, 0) as Save, COALESCE(Rating, 0) as Rating, COALESCE(job_recommended.AccountId, 0) as UserId, category.CategoryId, category.Description as Category from category, job LEFT JOIN job_recommended ON job_recommended.JobId = job.JobId and job_recommended.AccountId = "
				+ userId
				+ " WHERE category.CategoryId = job.CategoryId ORDER BY job.JobId DESC LIMIT "
				+ offset + ",10";
		if (connection.connect()) {
			ResultSet rs = connection.read(sql);
			try {
				while (rs.next()) {
					dtoJob job = new dtoJob();
					job.jobId = rs.getString("JobId");
					job.categoryId = rs.getString("CategoryId");
					job.accountId = rs.getString("AccountId");
					job.jobName = trimAll(rs.getString("JobName"));
					job.location = trimAll(rs.getString("Location"));
					job.salary = trimAll(rs.getString("Salary").replace(
							"Lương:", ""));
					job.description = trimAll(rs.getString("Description"));
					job.tags = trimAll(rs.getString("Tags"));
					job.requirement = trimAll(rs.getString("Requirement"));
					job.benifit = trimAll(rs.getString("Benifit"));
					job.expired = trimAll(rs.getString("Expired"));
					job.source = trimAll(rs.getString("Source"));
					job.company = trimAll(rs.getString("Company"));
					job.save = rs.getString("Save");
					job.rating = rs.getString("Rating");
					job.userId = rs.getString("UserId");
					job.category = rs.getString("Category");
					jobList.add(job);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return jobList;
	}

	public List<dtoJob> getJobByCategory(int offset, String categoryId,
			String userId) {

		List<dtoJob> jobList = new ArrayList<dtoJob>();
		String sql = "select job.AccountId, job.JobId, JobName, category.Description as Category, Job.CategoryId, Location, Salary, job.Description, Tags, Requirement, Benifit, Expired, Source, Company, COALESCE(Save, 0) as Save, COALESCE(Rating, 0) as Rating, COALESCE(job_recommended.AccountId, 0) as UserId FROM category, job LEFT JOIN job_recommended ON job_recommended.JobId = job.JobId AND job_recommended.AccountId = "
				+ userId
				+ " WHERE job.CategoryId = "
				+ categoryId
				+ " AND category.CategoryId = "
				+ categoryId
				+ " order by job.JobId desc limit " + offset + ",10";
		if (connection.connect()) {
			ResultSet rs = connection.read(sql);
			try {
				while (rs.next()) {
					dtoJob job = new dtoJob();
					job.jobId = rs.getString("JobId");
					job.categoryId = rs.getString("CategoryId");
					job.accountId = rs.getString("AccountId");
					job.jobName = trimAll(rs.getString("JobName"));
					job.location = trimAll(rs.getString("Location"));
					job.salary = trimAll(rs.getString("Salary").replace(
							"Lương:", ""));
					job.description = trimAll(rs.getString("Description"));
					job.tags = trimAll(rs.getString("Tags"));
					job.requirement = trimAll(rs.getString("Requirement"));
					job.benifit = trimAll(rs.getString("Benifit"));
					job.expired = trimAll(rs.getString("Expired"));
					job.source = trimAll(rs.getString("Source"));
					job.company = trimAll(rs.getString("Company"));
					job.rating = rs.getString("Rating");
					job.save = rs.getString("Save");
					job.userId = rs.getString("UserId");
					job.category = rs.getString("Category");
					jobList.add(job);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return jobList;
	}

	public String getShortDescription(String jobId) {
		String sql = "select left(Description, 100) as ShortDescription from job where JobId = ?";
		// String sql = "select Benifit from job where JobId = ?";
		String shortDes = "";
		if (connection.connect()) {
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, jobId);
				// stm.setString(1, "2769");
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				if (rs.next()) {
					shortDes = trimAll(rs.getString("ShortDescription"));
					// shortDes = rs.getString("Benifit");
					// shortDes = trimAll(shortDes);
				} else
					return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return shortDes;
	}

	public String trimAll(String txt) {

		while (txt.contains("  ") || txt.contains("\r\n")
				|| txt.contains("\n\n") || txt.contains("\t")) {
			txt = txt.trim();
			txt = txt.replaceAll("(?m)^[ |\t]*\r?\n", "");
			txt = txt.replaceAll("(\\s){2,}", "\n");

			txt = txt.replaceAll("\t", " ");
			txt = txt.replaceAll("\r\n", "\n");
			txt = txt.replaceAll("\n-\n", "-");
			txt = txt.replaceAll(":\n", ":");
		}
		return txt;
	}

	public List<dtoJob> getJobRecommended(String userId) {
		List<dtoJob> jobList = new ArrayList<dtoJob>();
		String sql = "select job.AccountId, job.JobId, JobName, category.CategoryId, category.Description as Category, Location, Salary, job.Description, Tags, Requirement, Benifit, Expired, Source, Company,Save, Rating, job_recommended.AccountId as UserId from category, job join job_recommended on job_recommended.JobId = job.JobId where category.CategoryId = job.CategoryId and Seen = 0 and job_recommended.AccountId = "
				+ userId + " order by job.JobId desc";
		if (connection.connect()) {
			ResultSet rs = connection.read(sql);
			try {
				while (rs.next()) {
					dtoJob job = new dtoJob();
					job.jobId = rs.getString("JobId");
					job.categoryId = rs.getString("CategoryId");
					job.accountId = rs.getString("AccountId");
					job.jobName = trimAll(rs.getString("JobName"));
					job.location = trimAll(rs.getString("Location"));
					job.salary = trimAll(rs.getString("Salary").replace(
							"Lương:", ""));
					job.description = trimAll(rs.getString("Description"));
					job.tags = trimAll(rs.getString("Tags"));
					job.requirement = trimAll(rs.getString("Requirement"));
					job.benifit = trimAll(rs.getString("Benifit"));
					job.expired = trimAll(rs.getString("Expired"));
					job.source = trimAll(rs.getString("Source"));
					job.company = trimAll(rs.getString("Company"));
					job.userId = rs.getString("UserId");
					job.category = rs.getString("Category");
					job.rating = rs.getString("Rating");
					job.save = rs.getString("Save");
					jobList.add(job);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return jobList;
	}

	public List<dtoJob> getJobCared(String userId, int offset) {
		List<dtoJob> jobList = new ArrayList<dtoJob>();
		String sql = "select job.AccountId, job.JobId, JobName, category.CategoryId, Location, Salary,job.Description, category.Description as Category, Tags, Requirement, Benifit, Expired, Source, Company, Save, Rating, job_recommended.AccountId as UserId from category, job join job_recommended on job_recommended.JobId = job.JobId and Save = 1 and job_recommended.AccountId = "
				+ userId
				+ " where category.CategoryId = job.CategoryId order by job.JobId desc limit "
				+ offset + ", 10";
		if (connection.connect()) {
			ResultSet rs = connection.read(sql);
			try {
				while (rs.next()) {
					dtoJob job = new dtoJob();
					job.jobId = rs.getString("JobId");
					job.categoryId = rs.getString("CategoryId");
					job.accountId = rs.getString("AccountId");
					job.jobName = trimAll(rs.getString("JobName"));
					job.location = trimAll(rs.getString("Location"));
					job.salary = trimAll(rs.getString("Salary").replace(
							"Lương:", ""));
					job.description = trimAll(rs.getString("Description"));
					job.tags = trimAll(rs.getString("Tags"));
					job.requirement = trimAll(rs.getString("Requirement"));
					job.benifit = trimAll(rs.getString("Benifit"));
					job.expired = trimAll(rs.getString("Expired"));
					job.source = trimAll(rs.getString("Source"));
					job.company = trimAll(rs.getString("Company"));
					job.save = rs.getString("Save");
					job.rating = rs.getString("Rating");
					job.userId = rs.getString("UserId");
					job.category = rs.getString("Category");
					jobList.add(job);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return jobList;
	}
}
