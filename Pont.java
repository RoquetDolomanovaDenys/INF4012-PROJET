package fr.denys.bridges.main;

public class Pont extends Predicat{
	
	private int i,j;
	private Type type;
	private Orientation orientation;
	
	
	public Pont(int x, int y , Orientation orien, Type ty){
		super();
		setI(x);
		setJ(y);
		setType(ty);
		setOrientation(orien);
	}
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
}
