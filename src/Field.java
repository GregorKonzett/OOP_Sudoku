
public class Field {
	private int value;
	public final boolean initial;
	
	public Field() {
		value=0;
		initial=false;
	}
	
	public Field(int value,boolean initial) {
		this.value = value;
		this.initial = initial;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isInitial() {
		return initial;
	}
	
	public String toString() {
		return value+"";//+(initial?"I ":" ");
	}

}
