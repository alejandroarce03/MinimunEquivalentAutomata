package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


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
			this.type=MEALY;
		}else {
			generateMooreAutomaton(pstates,prelations);
			this.type=MOORE;
		}
	}

	public int stateIndex(String s) {
		int result = 0;

		for (State cStat : states) {


			if(cStat.getName().equals(s)) {

				return result;
			}
			result++;

		}

		return -1;


	}
	public void dfs() {

		for (int i=0;i<states.size();i++) {
			states.get(i).setVisited(false);
		}

		visited = new boolean[states.size()];
		//Stack<State> stack = new Stack<State>();
		Queue<State> queue = new LinkedList<State>();

		State firstState = states.get(0);
		queue.add(firstState);
		//stack.push(firstState);
		int i = 0;
		while (!queue.isEmpty() && i<visited.length){
			State curr = queue.poll();
			//State curr = stack.pop();
			System.out.println("visi"+i);
			System.out.println("stat"+states.size());
			int index = stateIndex(curr.getName());
			visited[index] = true;
			states.get(index).setVisited(true);

			for (State s : curr.getSuccessors()) {
				if (!s.getVisited() && !queue.contains(s)) {
					System.out.println("No entra");
					queue.add(s);
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
	public ArrayList<String> outputsOfAutomatonMoore(){

		ArrayList<String> result = new ArrayList<>();

		for (State s : states) {

			if (!result.contains(s.getValue()) ) {

				result.add(s.getValue());

			}

		}

		return result;

	}

	public ArrayList<String> outputsOfAutomatonMealy(){

		ArrayList<String> result = new ArrayList<>();

		for (State s : states) {

			Relation prev = null;



			for(Relation r : relations ) {




				if(s.getName().equals(r.getSourceState().getName()) ) {

					if(prev == null) {

						prev = r;

					}else if (!result.contains(prev.getOutput()+r.getOutput())) {
						result.add(prev.getOutput()+r.getOutput());
					}


					//00,01,10,11

				}




			}

		}
		return result;
	}
	/**
	 * Nota importAnte: La creación de este metodo fue influenciado por la explicación de Fernanda Rojas Y John Ijaji de como funciona el algoritmo <br>
	 * El metodo hace la primera partición para el AFD<br> 
	 * @author Alexander Samacá, Alejandro Arce <br>
	 * @return Primera partición del AFD<br>
	 */
	public ArrayList<ArrayList<State>> getFirstPartition(){
		//Paso 1
		removeInaccessibleStates();


		ArrayList<State> cState = states;

		ArrayList<String> outputsAutom ;

		if(type.equals(MOORE)) {
			outputsAutom =   outputsOfAutomatonMoore();
		}else {
			outputsAutom = outputsOfAutomatonMealy();
		}



		for (int i = 0; i < cState.size(); i++) {

			cState.get(i).setVisited(false);
		}


		ArrayList<ArrayList<State>> result = new ArrayList<>();
		for (int i = 0; i < outputsAutom.size(); i++) {
			result.add(new ArrayList<>());

		}





		//Paso 2a

		if(getType().equals(MOORE)) {

			for(int i= 0 ; i < cState.size(); i++) {	

				for (int j = 0; j < result.size(); j++) {

					if(cState.get(i).getValue().equals(outputsAutom.get(j)))

						result.get(j).add(cState.get(i));


				}
			}	


			return result;

		}else if(getType().equals(MEALY)) {

			for(int i = 0; i < cState.size(); i++) {

				for (int j = 0; j < result.size(); j++) {

					Relation prev = null;

					for (Relation r : relations) {
						if(cState.get(i).getName().equals(r.getSourceState().getName())) {
							if(prev == null) {

								prev = r;

							}else if((prev.getOutput()+r.getOutput()).equals(outputsAutom.get(j))){

								result.get(j).add(cState.get(i));
							}
						}
					}




				}

			}


			return result;

		} else {
			//Esto no pasa si se cumple precondicion
			return null;
		}

	}
	//Paso 2b Y 2c al ser recursivo
	public ArrayList<ArrayList<State>> partitioningN(ArrayList<ArrayList<State>> parts){

		//2B

		ArrayList<ArrayList<State>> result = new ArrayList<>();


		for (ArrayList<State> selectedList : parts) {

			int counter = 0;


			ArrayList<ArrayList<State>> temp = new ArrayList<>();
			temp.add(new ArrayList<>());
			for (int machete = 0; machete < 5; machete++) {

				temp.add(new ArrayList<>());

			}
			if(selectedList.size()>1) {
				for (int i = 0 ; i < selectedList.size()-1; i++) {


					State prev = selectedList.get(i);

					if(!temp.get(counter).contains(prev)) {




						if (temp.get(counter+1).contains(prev)) {

							counter++;


						}else {
							temp.get(counter).add(prev);

						}
					}
					for (int j = i+1 ; j <selectedList.size() ; j++) {
						State current = selectedList.get(j);
						if (verifyPartition(parts,prev,current)) {

							if(!temp.get(counter).contains(current)) {

								temp.get(counter).add(current);

							}
							break;

						}else {




							State aux = current;
							if (temp.get(counter).contains(current)) {
								temp.get(counter).remove(current);
							}
							if (counter == 0) {
								temp.get(counter+1).add(aux);
							}else if (!temp.get(counter-1).contains(aux) && counter > 0) {
								temp.get(counter+1).add(aux);
							}

						}

					}

				}	for (int m = 0; m < temp.size(); m++) {

					if(temp.get(m).size() != 0) {

						result.add(temp.get(m));
					}
				} 
			}else {
				result.add(selectedList);

			}

		}	
		if (parts.equals( result)) {
			return result;
		}else {
			return partitioningN(result);
		}

	}
	public boolean verifyPartition(ArrayList<ArrayList<State>> listOfList, State a, State b) {

		boolean result = false;

		int counter = 0;


		for (ArrayList<State> selectedList : listOfList) {

			for (int i = 0; i < a.getSuccessors().size(); i++) {

				boolean elA = selectedList.contains(a.getSuccessors().get(i));
				boolean elB = selectedList.contains(b.getSuccessors().get(i));
				if ((selectedList.contains(a.getSuccessors().get(i))) && (selectedList.contains(b.getSuccessors().get(i)))){

					counter++;

				}

			}





		}

		if(counter >= a.getSuccessors().size() ) {


			result = true;
		}






		return result;


	}

	public ArrayList<ArrayList<State>> obtainECA(Automaton a){

		ArrayList<ArrayList<State>> result = a.getFirstPartition();
		return result;



	}
	public  ArrayList<ArrayList<State>> partioningFull(Automaton a){

		ArrayList<ArrayList<State>> partsForPartition = a.getFirstPartition();
		ArrayList<ArrayList<State>> result = partitioningN(partsForPartition);

		return result; 



	}
	public boolean compare ( ArrayList<ArrayList<State>> a,  ArrayList<ArrayList<State>> b) {
		boolean result = false;
		
		return result;
		
		
		
			
	}














}
