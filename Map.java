package fr.denys.bridges.main;

import java.util.ArrayList;

public class Map {
	private int L;
	private ArrayList<Ile> iles = new ArrayList<>();

	private static Map map;
	
	public Map(int taille){
		setL(taille);
		map = this;
	}
	
	public void addIle(Ile ile){
		this.iles.add(ile);
	}
	
	public boolean isExist(Ile ile){
		for(Ile ile_tmp : this.iles){
			if((ile_tmp.getI() == ile.getI()) && (ile_tmp.getJ() == ile.getJ()) && (ile_tmp.getN() == ile.getN()))
				return true;
		}
		return false;
	}
	
	public boolean hasIle(int i, int j){
		for(Ile ile : getIles()){
			if(ile.getI() == i && ile.getJ() == j)
				return true;
		}
		return false;
	}
	
	public boolean isInside(Pont pont){
		return (pont.getI() >= 1 && pont.getI() <= getL() && pont.getJ() >= 1 && pont.getJ() <= getL());
	}
	
	public int getL() {
		return L;
	}
	public void setL(int l) {
		L = l;
	}
	public ArrayList<Ile> getIles() {
		return iles;
	}
	public void setIles(ArrayList<Ile> iles) {
		this.iles = iles;
	}
	
	public Map getInstance(){
		return map;
	}
}
