import java.util.ArrayList;

public class Automata {
	
	public final static String MOORE = "Moore";
	public final static String MEALY = "Mealy";

	private ArrayList<State> states;
	private ArrayList<Relation> relations;
	private String type;
	
	

	public Automata(String type,String pstates, String prelations) {
		typeAutomata(type, pstates,prelations);
	}

	public void typeAutomata(String type,String pstates, String prelations) {
		if(type.equalsIgnoreCase("Mealy")) {
			generateMealyAutomata(pstates,prelations);
			type=MEALY;
		}else {
			generateMooreAutomata(pstates,prelations);
			type=MOORE;
		}
	}

	public void generateMealyAutomata(String pstates, String prelations) {

		String[] statesStrings = pstates.split(";");

		for(int i=0;i>statesStrings.length;i++) {
			State s = new State(statesStrings[i]);
			states.add(s);
		}

		String[] relationsStrings = prelations.split(";");

		for(int i=0;i>relationsStrings.length;i++) {
			String[] relationsInOut = relationsStrings[i].split(",");
			Relation r = new Relation(relationsInOut[0],relationsInOut[1],relationsInOut[2],relationsInOut[3]);
			relations.add(r);
		}
		connectStatesRelations();

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
			Relation r = new Relation(relationsInOut[0],relationsInOut[2],relationsInOut[3]);
			relations.add(r);
		}
		connectStatesRelations();

	}
	
	public void connectStatesRelations(){
		for(int i=0;i<states.size();i++) {
			for(int j=0;j<relations.size();j++) {
				String state = states.get(i).getName();
					if(state.equalsIgnoreCase(relations.get(j).getSourceState())) {
						states.get(i).getOutputs().add(relations.get(j));
					}
					if(state.equalsIgnoreCase(relations.get(j).getDestinationState())) {
						states.get(i).getInputs().add(relations.get(j));
					}
				
			}
		}
	}


}
