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
		states = new ArrayList<>();
		relations = new ArrayList<>();
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
	
	
	public void dfs() {
		
		for (int i=0;i<states.size();i++) {
			states.get(i).setVisited(false);
		}
		
		boolean[] visited = new boolean[states.size()];
		Stack<State> stack = new Stack<State>();
		State firstState = states.get(0);
		stack.push(firstState);
		int i = 0;
		while (!stack.isEmpty()){
			State curr = stack.pop();
			visited[i] = true;
			states.get(i).setVisited(true);
			i++;
			for (State s : getSuccessors(curr)) {
				if (!s.getVisited()) {
					stack.push(s);
				}
			}
			
		}
	}
	

	public void generateMealyAutomaton(String pstates, String prelations) {

		String[] statesStrings = pstates.split(";");

		for(int i=0;i>statesStrings.length;i++) {
			State s = new State(statesStrings[i]);
			states.add(s);
		}

		String[] relationsStrings = prelations.split(";");

		for(int i=0;i>relationsStrings.length;i++) {
			String[] relationsInOut = relationsStrings[i].split(",");
			Relation r = new Relation(relationsInOut[0],relationsInOut[1],searchState(relationsInOut[2]),searchState(relationsInOut[3]));
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
			Relation r = new Relation(relationsInOut[0],searchState(relationsInOut[1]),searchState(relationsInOut[2]));
			relations.add(r);
		}
		connectStatesRelations();

	}
	
	public State searchState(String name) {
		State found = null;
		for(State s : states) {
			if(s.getName().equalsIgnoreCase(name)) {
				found = s;
			}
		}
		return found;
	}
	
	public void connectStatesRelations(){
		for(int i=0;i<states.size();i++) {
			for(int j=0;j<relations.size();j++) {
				State state = states.get(i);
					if(state == relations.get(j).getSourceState()) {
						states.get(i).getOutputs().add(relations.get(j));
					}
					if(state == relations.get(j).getDestinationState()) {
						states.get(i).getInputs().add(relations.get(j));
					}
				
			}
		}
	}
	
	public void removeInaccessibleStates() {
		dfs();
		for(int i=0;i<states.size();i++) {
			if(states.get(i).getInputs() == null   [[]]) {
				states.remove(i);
			}
			if()
		}
	}
	
	public ArrayList<State> getSuccessors(State s){
		ArrayList<State> successors = new ArrayList<>();
		for(int i=0;i<s.getOutputs().size();i++) {
			Relation r = s.getOutputs().get(i);
			successors.add(r.getDestinationState());
		}
		
		return successors;
	}


}
