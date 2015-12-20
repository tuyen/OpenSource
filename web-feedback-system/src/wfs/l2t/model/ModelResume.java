package wfs.l2t.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wfs.l2t.dto.*;

public class ModelResume extends Model {

	public ModelResume() {
	}

	public void updateObjective(String resumeId, String desireSalary,
			String recentSalary, String positionType, String desireCareerLevel,
			String desireWorkLocation, String willingToRelocate,
			String WillingToTravel, String CareerObjective) {

		resumeId = this.escape(resumeId);
		desireSalary = this.escape(desireSalary);
		recentSalary = this.escape(recentSalary);
		positionType = this.escape(positionType);
		desireCareerLevel = this.escape(desireCareerLevel);
		desireWorkLocation = this.escape(desireWorkLocation);
		willingToRelocate = this.escape(willingToRelocate);
		WillingToTravel = this.escape(WillingToTravel);
		CareerObjective = this.escape(CareerObjective);
		
		String sql = "UPDATE `career_objective` " + " SET `DesireSalary`="
				+ desireSalary + ",`RecentSalary`=" + recentSalary
				+ ",`PositionType`='" + positionType
				+ "',`DesireCareerLevel`='" + desireCareerLevel
				+ "',`DesireWorkLocation`='" + desireWorkLocation
				+ "',`WillingToRelocate`=" + willingToRelocate
				+ ",`WillingToTravel`=" + WillingToTravel
				+ ",`CareerObjective`='" + CareerObjective
				+ "' WHERE `ResumeId`=" + resumeId;
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public void addObjective(String resumeId, String desireSalary,
			String recentSalary, String positionType, String desireCareerLevel,
			String desireWorkLocation, String willingToRelocate,
			String WillingToTravel, String CareerObjective) {

		String sql = "INSERT INTO `career_objective`(`ResumeId`, `DesireSalary`, `RecentSalary`, `PositionType`, `DesireCareerLevel`, `DesireWorkLocation`, `WillingToRelocate`, `WillingToTravel`, `CareerObjective`) "
				+ " VALUES ("
				+ resumeId
				+ ",'"
				+ desireSalary
				+ "','"
				+ recentSalary
				+ "','"
				+ positionType
				+ "','"
				+ desireCareerLevel
				+ "','"
				+ desireWorkLocation
				+ "',"
				+ willingToRelocate
				+ ","
				+ WillingToTravel
				+ ",'"
				+ CareerObjective + "')";
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public Boolean hasAObject(String resume) {
		resume = this.escape(resume);
		String sql = "SELECT count(*) as `has_objective` FROM `career_objective` WHERE `ResumeId`="
				+ resume;
		if (this.connection.connect()) {
			ResultSet rs = this.connection.read(sql);
			try {
				rs.next();
				if (rs.getInt("has_objective") > 0) {
					this.connection.close();
					return true;
				} else {
					this.connection.close();
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public Boolean canModify(String user, String resume) {
		user = this.escape(user);
		resume = this.escape(resume);
		String sql = "SELECT count(*) as `valid` FROM `resume` WHERE `ResumeId`="
				+ resume + " and `AccountId`=" + user;
		if (this.connection.connect()) {
			ResultSet rs = this.connection.read(sql);
			try {
				rs.next();
				if (rs.getInt("valid") > 0) {
					this.connection.close();
					return true;

				} else {
					this.connection.close();
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			catch (Exception e) {
				// TODO: handle exception
				return false;
			}

		}
		return false;
	}

	public dtoCareerObjective getObjective(String resumeId) {
		dtoCareerObjective data = new dtoCareerObjective();
		String sql = "SELECT `CareerObjectiveId`, `ResumeId`, `DesireSalary`, `RecentSalary`, `PositionType`, `DesireCareerLevel`,"+
		"  `DesireWorkLocation`, `WillingToRelocate`, `WillingToTravel`, `CareerObjective` FROM `career_objective` WHERE  `ResumeId` = "
				+ resumeId;
		
		resumeId = this.escape(resumeId);
		if(this.connection.connect())
		{
			ResultSet rs = this.connection.read(sql);
			try {
				rs.next();
				data.careerObjectiveId = rs.getString("CareerObjectiveId");
				data.resumeId = rs.getString("ResumeId");
				data.desireSalary = rs.getString("DesireSalary");
				data.recentSalary = rs.getString("RecentSalary");
				data.positionType = rs.getString("PositionType");
				data.desireWorkLocation = rs.getString("DesireWorkLocation");
				data.willingToRelocate = rs.getString("WillingToRelocate");
				data.willingToTravel = rs.getString("WillingToTravel");
				data.careerObjective = rs.getString("CareerObjective");
				data.desireCareerLevel = rs.getString("DesireCareerLevel");
				this.connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.connection.close();
		}
		return data;
	}

	public List<dtoLanguage> getAllLanguage(String resumeId) {
		resumeId = this.escape(resumeId);
		List<dtoLanguage> data = new ArrayList<dtoLanguage>();
		String sql = "SELECT * FROM `language` WHERE  `ResumeId` = " + resumeId;
		if (this.connection.connect()) {
			ResultSet rs = this.connection.read(sql);
			try {
				while (rs.next()) {
					dtoLanguage lang = new dtoLanguage();
					lang.languageId = rs.getString("LanguageId");
					lang.resumeId = rs.getString("ResumeId");
					lang.name = rs.getString("Name");
					lang.level = rs.getString("Level");
					data.add(lang);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.connection.close();
		}
		return data;
	}

	public void addLanguage(String resumeId, String language, String level) {

		resumeId = this.escape(resumeId);
		language = this.escape(language);
		level = this.escape(level);
		String sql = "INSERT INTO `language`(`ResumeId`, `Name`, `Level`) VALUES ("
				+ resumeId + ",'" + language + "','" + level + "')";
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public void deleteLanguage(String resumeId, String languageId) {
		resumeId = this.escape(resumeId);
		languageId = this.escape(languageId);
		String sql = "DELETE FROM `language` WHERE `LanguageId`= " + languageId
				+ " and `ResumeId`=" + resumeId;
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public void deleteExperience(String resumeId, String languageId) {
		
		resumeId = this.escape(resumeId);
		languageId = this.escape(languageId);
		String sql = "DELETE FROM `experience` WHERE `ExperienceId`= "
				+ languageId + " and `ResumeId`=" + resumeId;
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public void deleteSkill(String resumeId, String skillId) {

		resumeId = this.escape(resumeId);
		skillId = this.escape(skillId);
		String sql = "DELETE FROM `skill` WHERE `SkillId`= " + skillId
				+ " and `ResumeId`=" + resumeId;
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public void deleteEducation(String cv, String educationId) {
		cv = this.escape(cv);
		educationId = this.escape(educationId);
		String sql = "DELETE FROM `education` WHERE `ResumeId` =" + cv
				+ " and `EducationId`=" + educationId;
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();

		}
	}

	public void deleteResumeById(String cv, String accountId) {
		cv = this.escape(cv);
		accountId = this.escape(accountId);
		String sql = "DELETE FROM `resume` WHERE `ResumeId` = " + cv
				+ " and `AccountId` = " + accountId;
		if (this.connection.connect()) {
			this.connection.write("DELETE FROM `language` WHERE `ResumeId` = "+cv);
			this.connection.write("DELETE FROM `experience` WHERE `ResumeId` = "+cv);
			this.connection.write("DELETE FROM `education` WHERE `ResumeId` = "+cv);
			this.connection.write("DELETE FROM `career_objective` WHERE `ResumeId` = "+cv);
			this.connection.write("DELETE FROM `skill` WHERE `ResumeId` = "+cv);
			this.connection.write("DELETE FROM `career_objective` WHERE `ResumeId` = "+cv);			
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public List<dtoResume> getUserResumes(String userId) {

		userId = this.escape(userId);
		List<dtoResume> data = new ArrayList<dtoResume>();

		String sql = "SELECT * FROM resume WHERE `AccountId`=" + userId;

		try {
			if (connection.connect()) {
				ResultSet result = connection.read(sql);
				while (result.next()) {
					dtoResume resume = new dtoResume();
					resume.resumeId = result.getString("ResumeId");
					resume.address = result.getString("Address");
					resume.avatar = result.getString("Avatar");
					resume.birthday = result.getDate("Birthday");
					resume.email = result.getString("Email");
					resume.gender = result.getString("Gender");
					resume.hobby = result.getString("Hobby");
					resume.hometown = result.getString("Hometown");
					resume.maritalStatus = result.getBoolean("MaritalStatus");
					resume.name = result.getString("Name");
					resume.nationality = result.getString("Nationality");
					resume.phone = result.getString("Phone");
					resume.resumeTitle = result.getString("Title");
					data.add(resume);
				}
				result.close();
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public dtoResume getResume(String resumeId) {
		resumeId = this.escape(resumeId);
		dtoResume resume = new dtoResume();
		String sql = "SELECT * FROM resume WHERE ResumeId=" + resumeId;
		try {
			if (connection.connect()) {
				ResultSet result = connection.read(sql);
				while (result.next()) {
					resume.resumeId = resumeId;
					resume.address = result.getString("Address");
					resume.avatar = result.getString("Avatar");
					resume.birthday = result.getDate("Birthday");
					resume.email = result.getString("Email");
					resume.gender = result.getString("Gender");
					resume.hobby = result.getString("Hobby");
					resume.hometown = result.getString("Hometown");
					resume.maritalStatus = result.getBoolean("MaritalStatus");
					resume.name = result.getString("Name");
					resume.nationality = result.getString("Nationality");
					resume.phone = result.getString("Phone");
					resume.resumeTitle = result.getString("Title");
				}
				result.close();
				connection.close();
				return resume;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<dtoEducation> getEducation(String resumeId) {
		resumeId = this.escape(resumeId);
		List<dtoEducation> listEdu = new ArrayList<dtoEducation>();
		String sql = "SELECT * FROM education WHERE ResumeId=" + resumeId;
		try {
			connection.connect();
			ResultSet result = connection.read(sql);
			while (result.next()) {
				dtoEducation edu = new dtoEducation();
				edu.educationDescription = result
						.getString("EducationDescription");
				edu.educationLevel = result.getString("EducationLevel");
				edu.educationMajor = result.getString("EducationMajor");
				edu.startDate = result.getDate("StartDate");
				edu.endDate = result.getDate("EndDate");
				edu.schoolName = result.getString("SchoolName");
				edu.educationId = result.getInt("EducationId");
				edu.resumeId = result.getString("ResumeId");
				listEdu.add(edu);
			}
			result.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEdu;

	}

	public List<dtoExperience> getExperience(String resumeId) {
		resumeId = this.escape(resumeId);
		String sql = "select * from experience where ResumeId=" + resumeId;
		List<dtoExperience> listExp = new ArrayList<dtoExperience>();
		try {
			connection.connect();
			ResultSet result = connection.read(sql);
			while (result.next()) {
				dtoExperience exp = new dtoExperience();
				exp.companyName = result.getString("Company_name");
				exp.description = result.getString("Description");
				exp.period = result.getString("Period");
				exp.position = result.getString("Position");
				exp.jobTitle = result.getString("JobTitle");
				exp.experienceId = result.getInt("ExperienceId");
				exp.resumeId = result.getString("ResumeId");

				listExp.add(exp);
			}
			result.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listExp;
	}

	public List<dtoSkill> getSkill(String resumeId) {
		resumeId = this.escape(resumeId);
		List<dtoSkill> listSkill = new ArrayList<dtoSkill>();
		String sql = "select * from skill where ResumeId=" + resumeId;
		try {
			connection.connect();
			ResultSet result = connection.read(sql);
			while (result.next()) {
				dtoSkill skill = new dtoSkill();
				skill.name = result.getString("Name");
				skill.level = result.getString("Level");
				skill.skillId = result.getString("SkillId");
				skill.resumeId = result.getString("ResumeId");
				listSkill.add(skill);

			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSkill;
	}

	public List<dtoReference> getReference(String resumeId) {
		resumeId = this.escape(resumeId);
		connection.connect();
		List<dtoReference> listRef = new ArrayList<dtoReference>();
		String sql = "select * from reference where ResumeId=" + resumeId;
		try {
			ResultSet result = connection.read(sql);
			while (result.next()) {
				dtoReference ref = new dtoReference();
				ref.name = result.getString("Name");
				ref.jobTitle = result.getString("JobTitle");
				ref.phone = result.getString("Phone");
				ref.email = result.getString("Email");
				ref.id = result.getInt("ReferenceId");
				listRef.add(ref);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close();
		return listRef;
	}

	// update avartar

	public void AddEducation(String resumeId, String schoolName,
			String educationLevel, String educationMajor,
			String educationDescription, String startDate, String endDate) {

		resumeId = this.escape(resumeId);
		schoolName = this.escape(schoolName);
		educationLevel = this.escape(educationLevel);
		educationMajor = this.escape(educationMajor);
		educationDescription = this.escape(educationDescription);
		startDate = this.escape(startDate);
		endDate = this.escape(endDate);
		String sql = "INSERT INTO `education`( `ResumeId`, `SchoolName`, `EducationLevel`, `EducationMajor`, `EducationDescription`, `StartDate`, `EndDate`)"
				+ " VALUES ("
				+ resumeId
				+ ",'"
				+ schoolName
				+ "','"
				+ educationLevel
				+ "','"
				+ educationMajor
				+ "','"
				+ educationDescription
				+ "','"
				+ startDate
				+ "','"
				+ endDate
				+ "')";

		if (connection.connect()) {

			this.connection.write(sql);
			connection.close();
		}

	}

	public void addExperience(String ResumeId, String Company_name,
			String JobTitle, String Position, String Description, String Period) {
		String sql = "insert into experience (ResumeId,Company_name, JobTitle, Position, Description, Period) values(?, ?, ?, ?,?,?)";
		connection.connect();
		try {
			PreparedStatement stm = connection.getConnection()
					.prepareStatement(sql);
			stm.setString(1, ResumeId);
			stm.setString(2, Company_name);
			stm.setString(3, JobTitle);
			stm.setString(4, Position);
			stm.setString(5, Description);
			stm.setString(6, Period);
			connection.setPrepareStatement(stm);
			connection.writeSecure();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
	}


	public void addSkill(String name, String level, String resumeId) {
		String sql = "insert into skill (Name, Level, ResumeId) values(?,?,?)";
		connection.connect();
		try {
			PreparedStatement stm = connection.getConnection()
					.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, level);
			stm.setString(3, resumeId);
			connection.setPrepareStatement(stm);
			connection.writeSecure();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
	}


	public void updateResume(String resumeId, String accountId, String title,
			String name, String birthday, String gender, String maritalStatus,
			String nationality, String address, String email, String phone,
			String hobby) {

		resumeId = this.escape(resumeId);
		accountId = this.escape(accountId);
		title = this.escape(title);
		name = this.escape(name);
		birthday = this.escape(birthday);
		gender = this.escape(gender);
		maritalStatus = this.escape(maritalStatus);
		nationality = this.escape(nationality);
		address = this.escape(address);
		email = this.escape(email);
		phone = this.escape(phone);
		hobby = this.escape(hobby);
		
		String sql = "";
		sql = "UPDATE `resume` SET";
		sql += "`Title`='" + title + "',`Name`='" + name + "',`Birthday`='"
				+ birthday + "',`Gender`='" + gender + "',";
		sql += "`MaritalStatus`=" + maritalStatus + ",`Nationality`='"
				+ nationality + "',";
		sql += "`Address`='" + address + "',`Email`='" + email + "',`Phone`='"
				+ phone + "',`Hobby`='" + hobby + "'";
		sql += "WHERE `ResumeId`=" + resumeId + " and `AccountId`=" + accountId;
		if (this.connection.connect()) {
			this.connection.write(sql);
			this.connection.close();
		}
	}

	public void AddResume(String accountId, String title, String name,
			String birthday, String gender, String maritalStatus,
			String nationality, String avatar, String address, String email,
			String phone, String hobbies) {

		
		accountId = this.escape(accountId);
		title = this.escape(title);
		name = this.escape(name);
		birthday = this.escape(birthday);
		gender = this.escape(gender);
		maritalStatus = this.escape(maritalStatus);
		nationality = this.escape(nationality);
		address = this.escape(address);
		email = this.escape(email);
		phone = this.escape(phone);
		hobbies = this.escape(hobbies);
		
		String sql = "INSERT INTO `resume` (`AccountId`, `Title`, `Name`, `Birthday`, `Gender`, `MaritalStatus`, `Nationality`, `Avatar`, `Address`, `Email`, `Phone`, `Hobby`)"
				+ " VALUES("
				+ accountId
				+ ", '"
				+ title
				+ "', '"
				+ name
				+ "','"
				+ birthday
				+ "', '"
				+ gender
				+ "',"
				+ maritalStatus
				+ ",'"
				+ nationality
				+ "', '"
				+ avatar
				+ "', '"
				+ address
				+ "','"
				+ email
				+ "', '"
				+ phone + "','" + hobbies + "')";
		connection.connect();
		connection.write(sql);
		connection.close();
	}

	

	public int getMaxResumeId(String accountId) {
		if (connection.connect()) {
			int id = 0;
			ResultSet re = connection
					.read("SELECT COALESCE(max(ResumeId),-1) as Max FROM `resume` WHERE `AccountId` ="
							+ accountId);
			try {
				re.next();
				id = re.getInt("Max");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			return id;
		}
		return -1;

	}

}
