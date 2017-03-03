package fr.denys.bridges.main;

public class Ile extends Predicat{
	private int i,j,n;
	
	
	public Ile(int x, int y, int num){
		super();
		setI(x);
		setJ(y);
		setN(num);
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

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
}
