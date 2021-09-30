import java.util.ArrayList;

public class State {
	
	private String name;
	private String value;
	private ArrayList<Relation> inputs;
	private ArrayList<Relation> outputs;
	private boolean visited;
	
	public State(String n,String v) {
		name = n;
		value = v;
	}

	public State(String n) {
		name = n;
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
}
