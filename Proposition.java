package fr.denys.bridges.main;

import java.util.ArrayList;

public class Proposition implements CanProp{
	
	private ArrayList<CanProp> propositions;
	private ArrayList<Connectors> connectors;
	
	public Proposition(ArrayList<CanProp> props, ArrayList<Connectors> connect){
		super();
		setPropositions(props);
		setConnectors(connect);
	}
	
	
	public void applicateNegation(){
		
		for(int i = 0; i < getPropositions().size()-1; i++){
			Object obj = getPropositions().get(i);
			Connectors connect = getConnectors().get(i);
			if(obj instanceof Predicat){
				Predicat pred = (Predicat)obj;
				pred.setNegative();
				if(connect == Connectors.CONJONCTIVE){
					connect = Connectors.DISJONCTIVE;
				}else{
					connect = Connectors.CONJONCTIVE;
				}
				remplPredicat(pred,i);
				remplConnector(connect, i);
			}else{
				Proposition prop = (Proposition)obj;
				prop.applicateNegation();
				remplProposition(prop, i);
			}
		}
		int i = getPropositions().size()-1;
		Object obj = getPropositions().get(i);
		
		if(obj instanceof Predicat){
			Predicat pred = (Predicat)obj;
			pred.setNegative();
			remplPredicat(pred, i);
		}else{
			Proposition prop = (Proposition)obj;
			prop.applicateNegation();
			remplProposition(prop, i);
		}
	}
	
	public void remplPredicat(Predicat pred, int i){
		this.propositions.set(i, pred);
	}
	

	public void remplProposition(Proposition prop, int i){
		this.propositions.set(i, prop);
	}
	
	public void remplConnector(Connectors connect, int i){
		this.connectors.set(i, connect);
	}
	
	public void addProposition(Proposition prop){
		this.propositions.add(prop);
	}
	
	public void addPredicat(Predicat pred){
		this.propositions.add(pred);
	}
	
	public void removeProposition(Proposition prop){
		this.propositions.remove(prop);
	}
	
	public void removePredicat(Predicat pred){
		this.propositions.remove(pred);
	}
	
	public void addConnector(Connectors connect){
		this.connectors.add(connect);
	}
	
	public void removeConnector(Connectors connect){
		this.connectors.remove(connect);
	}
	
	public ArrayList<CanProp> getPropositions() {
		return propositions;
	}
	
	public void setPropositions(ArrayList<CanProp> propositions) {
		this.propositions = propositions;
	}
	
	public ArrayList<Connectors> getConnectors() {
		return connectors;
	}
	
	public void setConnectors(ArrayList<Connectors> connectors) {
		this.connectors = connectors;
	}
	
	
	
	
}
