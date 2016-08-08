package it.sieradzki.goeuro_test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// This class should be immutable, but we can't do that with Jackson
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	@JsonProperty("_id")
	private long id;

	private String name;

	private String type;

	@JsonProperty("geo_position")
	private GeoPosition geoPosition;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Location location = (Location) o;

		if (id != location.id) return false;
		if (!name.equals(location.name)) return false;
		if (!type.equals(location.type)) return false;
		return geoPosition.equals(location.geoPosition);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + name.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + geoPosition.hashCode();
		return result;
	}
}
