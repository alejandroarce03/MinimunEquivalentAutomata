import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Automaton {
	
	public final static String MOORE = "Moore";
	public final static String MEALY = "Mealy";

	private ArrayList<State> states;
	private ArrayList<Relation> relations;
	private String type;
	private HashMap<Integer,String> statesAutomaton;
	

	public Automaton(String type,String pstates, String prelations) {
		typeAutomaton(type, pstates,prelations);
	}

	public void typeAutomaton(String type,String pstates, String prelations) {
		if(type.equalsIgnoreCase("Mealy")) {
			generateMealyAutomaton(pstates,prelations);
			type=MEALY;
		}else {
			generateMooreAutomaton(pstates,prelations);
			type=MOORE;
		}
	}
	
	/*
	public void dfs() {
		
		for (int i=0;i<states.size();i++) {
			states.get(i).setVisited(false);
		}
		
		boolean[] visited = new boolean[states.size()];
		Stack<State> stack = new Stack<State>();
		State firstState = states.get(0);
		stack.push(firstState);

		while (!stack.isEmpty()){
			State curr = stack.pop();
			int ind = index.get(curr);
			visited[ind] = true;
			states.get(ind).setVisited(true);

			for (State s : current.getSuccessorStates()) {
				if (!visited[index.get(s)]) {
					stack.push(s);
				}
			}
			
		}
	}
	*/

	public void generateMealyAutomaton(String pstates, String prelations) {

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

	public void generateMooreAutomaton(String pstates, String prelations) {

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
