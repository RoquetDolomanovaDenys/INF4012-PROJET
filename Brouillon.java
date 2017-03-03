package fr.denys.bridges.main;

public class Brouillon {
	/*
	// FONCTION A OPTIMISER !!!!
	public static Proposition getRemplProp(Proposition prop, Pont pont1, Pont pont2){
		
		Proposition res = prop;
		
		if(res.getPropositions().size() == 1){
			Object obj = res.getPropositions().get(0);
			if(obj instanceof Pont){
				Pont pontp = (Pont)obj;
				if(Tools.isEqualPont(pontp, pont1)){
					res.remplPredicat(pont2, 0);
				}else if(pontp.isNegative()){
					Pont pontp2 = pontp;
					pontp2.setNormal();
					if(Tools.isEqualPont(pontp2, pont1)){
						res.remplPredicat(pont2, 0);
						res.applicateNegation();
					}
				}
			}else if(obj instanceof Proposition){
				res = getRemplProp((Proposition)obj, pont1, pont2);
			}
		}else{
			for(int i = 0 ; i < res.getPropositions().size();i++){
				Object obj = res.getPropositions().get(i);
				if(obj instanceof Proposition){
					Proposition tmp =getRemplProp((Proposition)obj, pont1, pont2);
					if(tmp == null){
						// A FAIRE MDR, LOL IMPOSSIBBLE
					}else{
						res.remplProposition(getRemplProp(tmp, pont1, pont2), i);
					}
				}else{
					Pont pontp = (Pont)obj;
					if(Tools.isEqualPont(pontp, pont1)){
						res.remplPredicat(pont2, 0);
					}else if(pontp.isNegative()){
						Pont pontp2 = pontp;
						pontp2.setNormal();
						if(Tools.isEqualPont(pontp2, pont1)){
							res.remplPredicat(pont2, 0);
							res.applicateNegation();
						}
					}
				}
			}
		}
		
		if(res == prop) res = null;
		
		return res;
	}
	
	public static Proposition getPontassoIle(Map map){
		for(Ile ile : map.getIles()){
			Proposition h1 = getAdjs(map, ile);
			for(int i = 2 ; i <= ile.getN(); i++){
		
			}
		}
	}*/
	
	
}
