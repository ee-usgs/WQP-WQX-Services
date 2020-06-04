package gov.usgs.wma.wqp.parameter;

public class BoundingBox {

	private String single;
	private String north;
	private String south;
	private String east;
	private String west;

	public BoundingBox(String single) {
		this.single = single;
		if (null != single) {
			String[] parts = single.split(",");
			if (4 == parts.length) {
				this.west = parts[0];
				this.south = parts[1];
				this.east = parts[2];
				this.north = parts[3];
			}
		}
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

	@Override
	public String toString() {
		return this.single;
	}

}
