package gov.usgs.cida.wqp.parameter;

public class ResultIdentifier {

	private String dataSource;
	private String resultId;
	private String single;

	public ResultIdentifier(String single) {
		this.single = single;
		if (null != single) {
			String[] parts = single.split("-");
			if (2 == parts.length) {
				this.dataSource = parts[0];
				this.resultId = parts[1];
			}
		}
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

}
