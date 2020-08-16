package contents;

public class Forex {
	private String base;
	private String target;
	private String multiplier;
	public Forex(String base, String target, String multiplier) {
		super();
		this.base = base;
		this.target = target;
		this.multiplier = multiplier;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}
	
}
