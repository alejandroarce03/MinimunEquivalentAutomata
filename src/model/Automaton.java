package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Automaton {

	public final static String MOORE = "Moore";
	public final static String MEALY = "Mealy";

	private ArrayList<State> states;
	private ArrayList<Relation> relations;
	private String type;
	private HashMap<State, Integer> statesAutomaton;
	private boolean[] visited;




	public Automaton(String type,String pstates, String prelations) {
		states = new ArrayList<>();
		relations = new ArrayList<>();
		typeAutomaton(type, pstates,prelations);	
		statesAutomaton = new HashMap<>();

	} 
	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	public ArrayList<Relation> getRelations() {
		return relations;
	}

	public void setRelations(ArrayList<Relation> relations) {
		this.relations = relations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<State,Integer> getStatesAutomaton() {
		return statesAutomaton;
	}

	public void setStatesAutomaton(HashMap<State, Integer> statesAutomaton) {
		this.statesAutomaton = statesAutomaton;
	}
	public void generateStatesAutomaton(){
		for (int i = 0; i < states.size(); i++) {
			statesAutomaton.put(states.get(i),i);

		}

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

		visited = new boolean[states.size()-1];
		Stack<State> stack = new Stack<State>();
		State firstState = states.get(0);
		stack.push(firstState);
		int i = 0;
		while (!stack.isEmpty() && i<visited.length){
			State curr = stack.pop();
			System.out.println("visi"+i);
			System.out.println("stat"+states.size());
			visited[i] = true;
			states.get(i).setVisited(true);
			i++;
			for (State s : curr.getSuccessors()) {
				if (!s.getVisited()) {
					System.out.println("No entra");
					stack.push(s);
				}
			}

		}
	}


	public void generateMealyAutomaton(String pstates, String prelations) {
		String[] statesStrings = pstates.split(";");
		for(int i=0;i<statesStrings.length;i++) {
			State s = new State(statesStrings[i]);
			states.add(s);
		}

		String[] relationsStrings = prelations.split(";");

		for(int i=0;i<relationsStrings.length;i++) {
			String[] relationsInOut = relationsStrings[i].split(",");
			Relation r = new Relation(relationsInOut[0],relationsInOut[1],searchState(relationsInOut[2]),searchState(relationsInOut[3]));
			relations.add(r);
		}
		connectStatesRelations();

	}

	public void generateMooreAutomaton(String pstates, String prelations) {

		String[] statesStrings = pstates.split(";");

		for(int i=0;i<statesStrings.length;i++) {
			String[] statesNameValue = statesStrings[i].split(",");
			State s = new State(statesNameValue[0],statesNameValue[1]);
			states.add(s);
		}

		String[] relationsStrings = prelations.split(";");

		for(int i=0;i<relationsStrings.length;i++) {
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
					states.get(i).addOutputRelations(relations.get(j));
				}
				if(state == relations.get(j).getDestinationState()) {
					states.get(i).addInputRelations(relations.get(j));
				}

			}
		}
	}

	public void removeInaccessibleStates() {

		dfs();
		for(int i=0;i<visited.length;i++) {
			System.out.println("visitado "+visited[i]);
		}
		for(int i=0;i<states.size();i++) {
			if(states.get(i).getInputs() == null  || !states.get(i).getVisited()) {
				//relations.removeAll(states.get(i).getOutputs());
				states.get(i).getOutputs().clear();
				states.remove(i);
				System.out.println("AKI");
				System.out.println(i);
			}
		}
	}
	/**
	 * Nota importAnte: La creación de este metodo fue influenciado por la explicación de Fernanda Rojas de como funciona el algoritmo <br>
	 * El metodo hace la primera partición para el AFD<br> 
	 * @author Alexander Samacá, Alejandro Arce y Fernanda Rojas <br>
	 * @return Primera partición del AFD<br>
	 */
	public ArrayList<ArrayList<State>> getFirstPartition(){

		removeInaccessibleStates();
		ArrayList<State> cState = states;

		for (int i = 0; i < states.size(); i++) {

			cState.get(i).setVisited(false);
		}


		ArrayList<ArrayList<State>> result = new ArrayList<>();

		ArrayList<State> brick;


		int counter = 0;

		for(int i= 0 ; i < cState.size()-1; i++) {	


			if (!cState.get(i).getVisited()) {	

				cState.get(i).setValue(""+counter);
				///cState.get(i).set(); Aqui deberia cambiar el anterior por si mismo, pero no entendi bien por qué
				cState.get(i).setVisited(true);
				brick = new ArrayList<State>();
				brick.add(cState.get(i));

				for (int j = 0; j < cState.size(); j++) {

					if(!cState.get(j).getVisited()) {
						String prev = String.valueOf(cState.get(i).getInputs());
						String act = String.valueOf(cState.get(j).getInputs());

						if(prev.equals(act)) {

						}

					}

				}
				result.add(brick);
				counter++;



			}
			
		}
		return result;



	}






}
