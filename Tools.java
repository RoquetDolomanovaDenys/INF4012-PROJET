package fr.denys.bridges.main;

import java.util.ArrayList;

public class Tools {
	public static boolean isEqualPont(Pont pont1, Pont pont2){
		return (pont1.getI() == pont2.getI() && pont1.getJ() == pont2.getJ()
				&& pont1.getOrientation() == pont2.getOrientation()
				&& pont1.getType() == pont2.getType()
				&& pont1.getState() == pont2.getState());
	}
	
	public static Proposition copyProp(Proposition prop){
		Proposition copy = new Proposition(new ArrayList<>(), new ArrayList<>());
		
		for(Object obj : prop.getPropositions()){
			if(obj instanceof Pont){
				Pont pont = (Pont)obj;
				Pont copyP = new Pont(pont.getI(), pont.getJ(), pont.getOrientation(), pont.getType());
				copy.addPredicat(copyP);
			}else if(obj instanceof Ile){
				Ile ile = (Ile)obj;
				Ile copyI = new Ile(ile.getI(),ile.getJ(),ile.getN());
				copy.addPredicat(copyI);
			}else if(obj instanceof Proposition){
				Proposition propo = (Proposition)obj;
				Proposition copyPr = copyProp(propo);
				copy.addProposition(copyPr);
			}
		}
		
		for(int i = 0 ; i < prop.getConnectors().size(); i++){
			Connectors connect = prop.getConnectors().get(i);
			Connectors copyC = null;
			switch(connect){
			case CONJONCTIVE : copyC = Connectors.CONJONCTIVE;
			case DISJONCTIVE : copyC = Connectors.DISJONCTIVE;
			}
			copy.addConnector(copyC);
		}
		return copy;
	}
	
	
	public static void printProposition(Proposition prop){
		int i = 0;
		int size = prop.getPropositions().size();
		for(Object obj : prop.getPropositions()){
			if(obj instanceof Pont){
				Pont pont = (Pont)obj;
				if(pont.isNegative()) System.out.print("-");
				System.out.print("P("+pont.getI()+","+pont.getJ()+","+pont.getOrientation().toString()+","+pont.getType().toString()+")");
			}else if(obj instanceof Ile){
				Ile ile = (Ile)obj;
				if(ile.isNegative()) System.out.print("-");
				System.out.print("I("+ile.getI()+","+ile.getJ()+","+ile.getN()+")");
			}else if(obj instanceof Proposition){
				System.out.print("(");
				printProposition((Proposition)obj);
				System.out.print(")");
			}
			if(i != size-1){
				if(prop.getConnectors().get(i)==Connectors.CONJONCTIVE){
					System.out.print("^"); System.out.println();
				}
				else
					System.out.print("v");
			}
			i++;
		}
	}
	
	/*public static boolean isEqualProp(Proposition prop1, Proposition prop2){
		
		for(Connectors co1 : prop1.getConnectors())
			for(Connectors co2 : prop2.getConnectors())
				if(co1!=co2)
					return false;
		
		if(prop1.getPropositions().size() != prop2.getPropositions().size())
			return false;
		
		for(Object obj1 : prop1.getPropositions()){
			for(Object obj2 : prop2.getPropositions()){
				if(obj1 instanceof Pont){
					
				}else if(obj1 instanceof Ile){
					
				}else{
					
				}
			}
		}
	}*/
	
	
}
