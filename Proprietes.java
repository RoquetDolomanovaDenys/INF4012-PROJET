package fr.denys.bridges.main;

import java.util.ArrayList;

public class Proprietes {
	
	public static int NMAX = 8;
	
	public static Proposition getAdjs(Map map, Ile ile){
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		
		Pont pont = new Pont(ile.getI()+1, ile.getJ(), Orientation.HORIZONTAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI()-1, ile.getJ(), Orientation.HORIZONTAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI(), ile.getJ()+1, Orientation.VERTICAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI(), ile.getJ()-1, Orientation.VERTICAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		if(prop.getPropositions().size() == prop.getConnectors().size() && prop.getConnectors().size() != 0)
			prop.removeConnector(prop.getConnectors().get(prop.getConnectors().size()-1));
		
		return prop;
		
		
	}
	
	public static Proposition getAdj(Map map, Ile ile){
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		
		Pont pont = new Pont(ile.getI()+1, ile.getJ(), Orientation.HORIZONTAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI()+1, ile.getJ(), Orientation.HORIZONTAL, Type.DOUBLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		
		
		pont = new Pont(ile.getI()-1, ile.getJ(), Orientation.HORIZONTAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI()-1, ile.getJ(), Orientation.HORIZONTAL, Type.DOUBLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI(), ile.getJ()+1, Orientation.VERTICAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI(), ile.getJ()+1, Orientation.VERTICAL, Type.DOUBLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI(), ile.getJ()-1, Orientation.VERTICAL, Type.SIMPLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		pont = new Pont(ile.getI(), ile.getJ()-1, Orientation.VERTICAL, Type.DOUBLE);
		if(map.isInside(pont)){
			prop.addPredicat(pont);
			prop.addConnector(Connectors.DISJONCTIVE);
		}
		
		if(prop.getPropositions().size() == prop.getConnectors().size() && prop.getConnectors().size() != 0)
			prop.removeConnector(prop.getConnectors().get(prop.getConnectors().size()-1));
		
		return prop;
		
		
	}
	
	public static Proposition getPontsAsso(Map map){
		
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		for(Ile ile : map.getIles()){
			Proposition adj = getAdj(map, ile);
			prop.addProposition(adj);
			prop.addConnector(Connectors.CONJONCTIVE);
			for(int i = 0 ; i< adj.getPropositions().size(); i++){
				
				Object obj = adj.getPropositions().get(i);
				Pont pont =(Pont)obj;
				
				if(pont.getType() == Type.SIMPLE){
					Proposition h = Tools.copyProp(adj);
					h.removePredicat((Predicat)h.getPropositions().get(i));
					
					if(h.getPropositions().size() == h.getConnectors().size())
						h.removeConnector(h.getConnectors().get(i));
					
					prop.addProposition(h);
					prop.addConnector(Connectors.CONJONCTIVE);
					
				}
				
			}
			
		}
		if(prop.getPropositions().size() == prop.getConnectors().size() && prop.getConnectors().size() != 0)
			prop.removeConnector(prop.getConnectors().get(prop.getConnectors().size()-1));
		
		return prop;
	}
	
	
	public static Proposition getPropBetIles(Map map){
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		for(int i = 0 ; i<map.getIles().size();i++){
			Ile ile1 = map.getIles().get(i);
			for(int j = i+1 ; j < map.getIles().size();j++){
				Ile ile2 = map.getIles().get(j);
				if(ile1 != ile2){
					if(ile1.getI() == ile2.getI()){
						if(ile1.getJ() > ile2.getJ()){
							Ile tmp = ile1;
							ile1 = ile2;
							ile2 = tmp;
						}
						boolean AlreadyIle = false;

						for(int k = ile1.getJ()+1 ; k < ile2.getJ(); k ++)
							if(map.hasIle(ile1.getI(),k)){AlreadyIle = true; break; }
						for(int k = ile1.getJ()+1 ; k < ile2.getJ() && !AlreadyIle; k ++){
							Proposition dist = new Proposition(new ArrayList<>(), new ArrayList<>());
							Pont pont = new Pont(ile1.getI(),k, Orientation.VERTICAL, Type.SIMPLE);
							dist.addPredicat(pont);
							dist.addConnector(Connectors.DISJONCTIVE);
							pont = new Pont(ile1.getI(),k,Orientation.VERTICAL, Type.DOUBLE);
							dist.addPredicat(pont);
							prop.addProposition(dist);
							prop.addConnector(Connectors.CONJONCTIVE);
						}
					}else if(ile1.getJ() == ile2.getJ()){
						if(ile1.getI() > ile2.getI()){
							Ile tmp = ile1;
							ile1 = ile2;
							ile2 = tmp;
						}
						boolean AlreadyIle = false;

						for(int k = ile1.getI()+1 ; k < ile2.getI(); k ++)
							if(map.hasIle(k,ile1.getJ())){AlreadyIle = true; break; }
						
						for(int k = ile1.getI()+1 ; k < ile2.getI() && !AlreadyIle ; k ++){
							Proposition dist = new Proposition(new ArrayList<>(), new ArrayList<>());
							Pont pont = new Pont(k,ile1.getJ(), Orientation.HORIZONTAL, Type.SIMPLE);
							dist.addPredicat(pont);
							dist.addConnector(Connectors.DISJONCTIVE);
							pont = new Pont(k,ile1.getJ(),Orientation.HORIZONTAL, Type.DOUBLE);
							dist.addPredicat(pont);
							prop.addProposition(dist);
							prop.addConnector(Connectors.CONJONCTIVE);
						}
					}
				}
			}
			
		}
		if(prop.getPropositions().size() == prop.getConnectors().size() && prop.getConnectors().size() != 0)
			prop.removeConnector(prop.getConnectors().get(prop.getConnectors().size()-1));
		return prop;
	}
	
	
	public static Proposition getPropImpo(Map map){
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		ArrayList<Integer> alReadyI = new ArrayList<>();
		ArrayList<Integer> alReadyJ = new ArrayList<>();
		for(Ile ile : map.getIles()){
			int i = ile.getI()-1;
			int j = ile.getJ();
			
			alReadyI.add(i);
			alReadyJ.add(j);
			if(i>=1 && i <= map.getL() && j >= 1 && j <= map.getL()){
				for(int n = 1 ; n <= NMAX; n++){
					Pont pont;
					Ile ilet;
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					
				}
				
			}
			
			i = ile.getI()+1;
			j = ile.getJ();
			
			alReadyI.add(i);
			alReadyJ.add(j);
			if(i>=1 && i <= map.getL() && j >= 1 && j <= map.getL()){
				for(int n = 1 ; n <= NMAX; n++){
					Pont pont;
					Ile ilet;
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
				}
				
			}
			
			i = ile.getI();
			j = ile.getJ()+1;
			
			alReadyI.add(i);
			alReadyJ.add(j);
			if(i>=1 && i <= map.getL() && j >= 1 && j <= map.getL()){
				for(int n = 1 ; n <= NMAX; n++){
					Pont pont;
					Ile ilet;
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
				}
				
			}
			
			i = ile.getI();
			j = ile.getJ()-1;
			
			alReadyI.add(i);
			alReadyJ.add(j);
			if(i>=1 && i <= map.getL() && j >= 1 && j <= map.getL()){
				for(int n = 1 ; n <= NMAX; n++){
					Pont pont;
					Ile ilet;
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.DOUBLE);
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.VERTICAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.SIMPLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
					
					pont = new Pont(i,j,Orientation.HORIZONTAL, Type.DOUBLE);
					pont.setNegative();
					prop.addPredicat(pont);
					prop.addConnector(Connectors.CONJONCTIVE);
				
					ilet = new Ile(i, j, n);
					ilet.setNegative();
					prop.addPredicat(ilet);
					prop.addConnector(Connectors.CONJONCTIVE);
				}
				
			}
		}
		
		
		for(int i = 0 ; i<map.getIles().size();i++){
			Ile ile1 = map.getIles().get(i);
			for(int j = i+1 ; j < map.getIles().size();j++){
				Ile ile2 = map.getIles().get(j);
				if(ile1 != ile2){
					if(ile1.getI() == ile2.getI()){
						if(ile1.getJ() > ile2.getJ()){
							Ile tmp = ile1;
							ile1 = ile2;
							ile2 = tmp;
						}
						
						boolean AlreadyIle = false;
					
						for(int k = ile1.getJ()+1 ; k < ile2.getJ(); k ++)
							if(map.hasIle(ile1.getI(),k)){AlreadyIle = true; break; }
						for(int k = ile1.getJ()+1 ; k < ile2.getJ() && !AlreadyIle; k ++){
							if(!(alReadyJ.contains(k) && alReadyI.contains(ile2.getI()))){
								for(int n = 1; n<=NMAX;n++){
									Pont pont;
									Ile ilet;
									pont = new Pont(ile1.getI(),k,Orientation.VERTICAL, Type.SIMPLE);
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(ile1.getI(),k,Orientation.HORIZONTAL, Type.SIMPLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									pont = new Pont(ile1.getI(),k,Orientation.HORIZONTAL, Type.DOUBLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(ile1.getI(),k,Orientation.VERTICAL, Type.DOUBLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									ilet = new Ile(ile1.getI(),k, n);
									ilet.setNegative();
									prop.addPredicat(ilet);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									
									pont = new Pont(ile1.getI(),k,Orientation.VERTICAL, Type.DOUBLE);
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(ile1.getI(),k,Orientation.VERTICAL, Type.SIMPLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									pont = new Pont(ile1.getI(),k,Orientation.HORIZONTAL, Type.SIMPLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(ile1.getI(),k,Orientation.HORIZONTAL, Type.DOUBLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									ilet = new Ile(ile1.getI(),k, n);
									ilet.setNegative();
									prop.addPredicat(ilet);
									prop.addConnector(Connectors.CONJONCTIVE);
								}
							}
						}
					}else if(ile1.getJ() == ile2.getJ()){
						if(ile1.getI() > ile2.getI()){
							Ile tmp = ile1;
							ile1 = ile2;
							ile2 = tmp;
						}
						boolean AlreadyIle = false;

						for(int k = ile1.getI()+1 ; k < ile2.getI(); k ++)
							if(map.hasIle(k,ile1.getJ())){AlreadyIle = true; break; }
						
						for(int k = ile1.getI()+1 ; k < ile2.getI() && !AlreadyIle ; k ++){
							if(!alReadyI.contains(k) && !alReadyJ.contains(ile1.getJ())){
								for(int n=1;n<=NMAX;n++){
									Pont pont;
									Ile ilet;
									pont = new Pont(k,ile1.getJ(),Orientation.HORIZONTAL, Type.SIMPLE);
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(k,ile1.getJ(),Orientation.HORIZONTAL, Type.DOUBLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									pont = new Pont(k,ile1.getJ(),Orientation.VERTICAL, Type.SIMPLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(k,ile1.getJ(),Orientation.VERTICAL, Type.DOUBLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									ilet = new Ile(k,ile1.getJ(), n);
									ilet.setNegative();
									prop.addPredicat(ilet);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									
									pont = new Pont(k,ile1.getJ(),Orientation.HORIZONTAL, Type.DOUBLE);
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(k,ile1.getJ(),Orientation.HORIZONTAL, Type.SIMPLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									pont = new Pont(k,ile1.getJ(),Orientation.VERTICAL, Type.SIMPLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
									
									pont = new Pont(k,ile1.getJ(),Orientation.VERTICAL, Type.DOUBLE);
									pont.setNegative();
									prop.addPredicat(pont);
									prop.addConnector(Connectors.CONJONCTIVE);
								
									ilet = new Ile(k,ile1.getJ(), n);
									ilet.setNegative();
									prop.addPredicat(ilet);
									prop.addConnector(Connectors.CONJONCTIVE);
								}
							}
						}
					}
				}
			}
			
		}
		if(prop.getPropositions().size() == prop.getConnectors().size() && prop.getConnectors().size() != 0)
			prop.removeConnector(prop.getConnectors().get(prop.getConnectors().size()-1));
		
		return prop;
	}
	
	
	
	public static Proposition getLinePont(Map map){
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		
		return prop;
	}
	
	
	public static void main(String[] args){
		Map map = new Map(5);
		map.addIle(new Ile(2,1,2));
		map.addIle(new Ile(2,5,2));
		Proposition prop = new Proposition(new ArrayList<>(), new ArrayList<>());
		prop.addProposition(getPontsAsso(map));
		prop.addConnector(Connectors.CONJONCTIVE);
		prop.addProposition(getPropBetIles(map));
		prop.addConnector(Connectors.CONJONCTIVE);
		prop.addProposition(getPropImpo(map));
		Tools.printProposition(prop);
	}

}
