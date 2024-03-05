package Tp1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//rules base
		ArrayList<Rule>BDR=new ArrayList<Rule>();
		
		
		 BDR.add(new Rule(1, new ArrayList<>(Arrays.asList("A")), new ArrayList<>(Arrays.asList("E"))));
		 BDR.add(new Rule(2, new ArrayList<>(Arrays.asList("B")), new ArrayList<>(Arrays.asList("D"))));
		 BDR.add(new Rule(3, new ArrayList<>(Arrays.asList("H")), new ArrayList<>(Arrays.asList("A", "F"))));
		 BDR.add(new Rule(4, new ArrayList<>(Arrays.asList("E", "G")), new ArrayList<>(Arrays.asList("C"))));
		 BDR.add(new Rule(5, new ArrayList<>(Arrays.asList("E", "K")), new ArrayList<>(Arrays.asList("B"))));
		 BDR.add(new Rule(6, new ArrayList<>(Arrays.asList("D", "E", "K")), new ArrayList<>(Arrays.asList("C"))));
		 BDR.add(new Rule(7, new ArrayList<>(Arrays.asList("G", "K", "F")), new ArrayList<>(Arrays.asList("A"))));

		//fact base
		ArrayList<String> BDF=new ArrayList<>(Arrays.asList("H","K"));
		
		//facts to prove
		ArrayList<String>FP=new ArrayList<>(Arrays.asList("C"));
		ArrayList<String>FP2=new ArrayList<>(Arrays.asList("G"));
	
		Boolean test=BackChain(BDR,BDF,FP);
		if(test) System.out.println("Goal "+FP+" satisfied by backward chaining");
		else System.out.println("Goal"+FP+" unsatisfied by backward chaining");
		
		Boolean test2=ForwardChain(BDR,BDF,FP);
		if(test2) System.out.println("Goal "+FP+" satisfied by forward chaining");
		else System.out.println("Goal"+FP+" unsatisfied by forward chaining");
		
	
		Boolean test3=BackChain(BDR,BDF,FP2);
		if(test3) System.out.println("Goal "+FP2+" satisfied by backward chaining");
		else System.out.println("Goal "+FP2+" unsatisfied by backward chaining");
		
		Boolean test4=ForwardChain(BDR,BDF,FP2);
		if(test4) System.out.println("Goal "+FP2+" satisfied by forward chaining");
		else System.out.println("Goal "+FP2+" unsatisfied by forward chaining");
		
	}
	private static Boolean ForwardChain(ArrayList<Rule> BDR, ArrayList<String> BDF, ArrayList<String> FP) {
		// TODO Auto-generated method stub
		boolean res=false;
		//si le but est deja dans la BDF alors succès
		if(BDF.containsAll(FP))return true;
		else {
			//regles non activées
			ArrayList<Rule>nonTriggeredRules=new ArrayList<Rule>(BDR);
			//regles activables
			ArrayList<Rule>rulesToConsider=new ArrayList<Rule>(BDR);
			
			while(!rulesToConsider.isEmpty()&& !res) {
				//choisir une regle
				Rule r=rulesToConsider.get(0);
				rulesToConsider.remove(0);
				//si la bdf satisfait les condition de cette regle ==> ajouter le resultat de la regle a la BDF
				if(BDF.containsAll(r.p)) {
					BDF.addAll(r.c);
					//desactiver la regle
					nonTriggeredRules.remove(r);
					rulesToConsider=new ArrayList<>(nonTriggeredRules);
					
					//si le but est atteint on sort
					if(r.c.containsAll(FP))res=true;
				}
					
			}
		}
		return res;
	}
	public static Boolean BackChain(ArrayList<Rule>BDR,ArrayList<String>BDF,ArrayList<String>FP) {
		Boolean res;
		if(FP.isEmpty()) return true;
		else {
			if(TakeGoal(BDR,BDF,FP.get(0))) {
				BDF.add(FP.get(0));
				res=BackChain(BDR,BDF,rest(FP));
			}
			else res=false;
		}
		return res;
	}
	private static ArrayList<String> rest(ArrayList<String> FP) {
	        return new ArrayList<>(FP.subList(1, FP.size()));
	    }
	private static boolean TakeGoal(ArrayList<Rule> BDR, ArrayList<String> BDF, String FP) {
		// TODO Auto-generated method stub
		boolean res=false;
		if(BDF.contains(FP))return true;
		else {
			ArrayList<Rule>RA=new ArrayList<Rule>();
			for(Rule rule:BDR) if(rule.c.contains(FP))RA.add(rule);
			while(!RA.isEmpty()&& res!=true) {
				Rule r=RA.get(0);
				RA.remove(0);
				res=BackChain(BDR,BDF,r.p);
				
			}
		}
		return res;
	}

}
