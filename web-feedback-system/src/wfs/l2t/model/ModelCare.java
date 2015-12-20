package wfs.l2t.model;

public class ModelCare extends Model {
	public ModelCare() {
		super();
	}

	public Boolean uncareAllCategory(String userId) {
		if (this.connection.connect()) {
			String sql = "DELETE FROM `care` WHERE `AccountId` = " + userId;
			Boolean rs = this.connection.write(sql);
			this.connection.close();
			return rs;
		}
		return false;
	}

	public void careCategory(String userId, String[] catId) {
		if (this.connection.connect()) {
			for (int i = 0; i < catId.length; i++) {
				String sql = "INSERT INTO `care`(`CategoryId`, `AccountId`) VALUES ("
						+ catId[i] + "," + userId + ")";
				this.connection.write(sql);
			}
			this.connection.close();
		}
	}

	public void careCategory(String userId, String catId) {
		if (this.connection.connect()) {
			String sql = "INSERT INTO `care`(`CategoryId`, `AccountId`) VALUES ("
					+ catId + ", "+userId+")";
			this.connection.write(sql);

			this.connection.close();
		}
	}
}
