package model;
import java.util.ArrayList;

public class State {
	
	private String name;
	private String value;
	private ArrayList<Relation> inputs;
	private ArrayList<Relation> outputs;
	//private ArrayList<String> inputs;
	//private ArrayList<String> outputs;
	
	private boolean visited;
	
	public State(String n,String v) {
		//Moore
		name = n;
		value = v;
		inputs = new ArrayList<>();
		outputs = new ArrayList<>();
	}

	public State(String n) {
		//Mealy
		name = n;
		inputs = new ArrayList<>();
		outputs = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList<Relation> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Relation> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<Relation> getOutputs() {
		return outputs;
	}

	public void setOutputs(ArrayList<Relation> outputs) {
		this.outputs = outputs;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited=visited;
	}
	
	public ArrayList<State> getSuccessors(){
		ArrayList<State> successors = new ArrayList<>();
		for(int i=0;i<outputs.size();i++) {
			Relation r = outputs.get(i);
			successors.add(r.getDestinationState());
		}
		
		return successors;
	}
	public void addSuccesor(Relation s) {
		outputs.add(s);
		
	}
	
	public void addInputRelations(Relation r) {
		inputs.add(r);
	}
	public void addOutputRelations(Relation r) {
		outputs.add(r);
	}
}
