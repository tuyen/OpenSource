package wfs.l2t.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wfs.l2t.dto.dtoCategory;

public class ModelCategory extends Model {

	public ModelCategory() {
		super();

	}

	public List<dtoCategory> getAllCategoryByUser(String userId) {
		String sql = "SELECT `category`.`CategoryId` , `Description` , COALESCE( `Care`.`CategoryId` , 0 ) AS `Checked` FROM `category` LEFT JOIN `care` ON `category`.`CategoryId` = `Care`.`CategoryId` AND `care`.`AccountId` = "
				+ userId;
		List<dtoCategory> data = new ArrayList<dtoCategory>();
		if (this.connection.connect()) {
			ResultSet rs = this.connection.read(sql);
			if (rs != null) {
				try {
					while (rs.next()) {
						dtoCategory item = new dtoCategory();
						item.categoryId = rs.getString("CategoryId");
						item.categoryName = rs.getString("Description");
						item.checked = rs.getInt("Checked") == 0 ? ""
								: "checked";
						data.add(item);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.connection.close();
		}
		return data;
	}

	public List<dtoCategory> getAllCategory() {
		List<dtoCategory> data = new ArrayList<dtoCategory>();
		if (this.connection.connect()) {
			ResultSet rs = this.connection
					.read("SELECT DISTINCT category.Description, category.CategoryId FROM job, category WHERE job.CategoryId = category.CategoryId ORDER BY category.Description ASC");
			if (rs != null) {
				try {
					while (rs.next()) {
						dtoCategory item = new dtoCategory();
						item.categoryId = rs.getString("CategoryId");
						item.categoryName = rs.getString("Description");
						data.add(item);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.connection.close();
		}
		return data;
	}

	public String getCategoryName(String id) {
		String sql = "select Description from category where CategoryId = ?";
		String ret = "";
		if (connection.connect()) {
			try {
				PreparedStatement stm = connection.getConnection()
						.prepareStatement(sql);
				stm.setString(1, id);
				connection.setPrepareStatement(stm);
				ResultSet rs = connection.readSecure();
				if (rs.next()) {
					ret = rs.getString("Description");
				} else
					return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.close();
		}
		return ret;
	}
}
