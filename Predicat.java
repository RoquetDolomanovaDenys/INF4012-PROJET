package fr.denys.bridges.main;

public class Predicat implements CanProp {

	private PredicatState state;
	
	public Predicat(){
		state = PredicatState.NORMAL;
	}
	
	
	
	public PredicatState getState() {
		return state;
	}



	public void setState(PredicatState state) {
		this.state = state;
	}



	public void setNormal(){
		this.state = PredicatState.NORMAL;
	}
	
	public void setNegative(){
		this.state = PredicatState.NEGATION;
	}
	
	public boolean isNegative(){
		return this.state == PredicatState.NEGATION;
	}
	
}
