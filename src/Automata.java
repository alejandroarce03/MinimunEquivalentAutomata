import java.util.ArrayList;

public class Automata {

	private ArrayList<State> states;
	private ArrayList<Relation> relations;

	public Automata(String type,String pstates, String prelations) {
		typeAutomata(type, pstates,prelations);
	}

	public void typeAutomata(String type,String pstates, String prelations) {
		if(type.equalsIgnoreCase("Mealy")) {
			generateMealyAutomata(pstates,prelations);
		}else {
			generateMooreAutomata(pstates,prelations);
		}
	}

	public void generateMealyAutomata(String pstates, String prelations) {

		String[] statesStrings = pstates.split(";");

		for(int i=0;i>statesStrings.length;i++) {
			State s = new State(statesStrings[i],null);
			states.add(s);
		}

		String[] relationsStrings = prelations.split(";");

		for(int i=0;i>relationsStrings.length;i++) {
			String[] relationsInOut = relationsStrings[i].split(",");
			Relation r = new Relation(relationsInOut[0],relationsInOut[1],relationsInOut[2],relationsInOut[3]);
			relations.add(r);
		}

	}

	public void generateMooreAutomata(String pstates, String prelations) {

		String[] statesStrings = pstates.split(";");

		for(int i=0;i>statesStrings.length;i++) {
			String[] statesNameValue = statesStrings[i].split(",");
			State s = new State(statesNameValue[0],statesNameValue[1]);
			states.add(s);
		}

		String[] relationsStrings = prelations.split(";");

		for(int i=0;i>relationsStrings.length;i++) {
			String[] relationsInOut = relationsStrings[i].split(",");
			Relation r = new Relation(relationsInOut[0],null,relationsInOut[2],relationsInOut[3]);
			relations.add(r);
		}

	}


}
