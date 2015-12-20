package wfs.l2t.model;

import org.apache.commons.lang.StringEscapeUtils;

public abstract class Model {

	public String escape(String str)
	{
		return StringEscapeUtils.escapeSql(str);
	}
	
	protected ModelConnection connection = new ModelConnection();

	public Model() {

	}
}
