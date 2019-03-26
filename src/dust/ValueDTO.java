package dust;

public class ValueDTO {
	private String pm10Value[] = new String[5];
	private String pm25Value[] = new String[5];
	
	public String getPm10Value(int i) {
		return pm10Value[i];
	}
	public void setPm10Value(int i, String val) {
		this.pm10Value[i] = val;
	}
	public String getPm25Value(int i) {
		return pm25Value[i];
	}
	public void setPm25Value(int i, String val) {
		this.pm25Value[i] = val;
	}
	public int getSize() {
		return pm10Value.length;
	}
}
