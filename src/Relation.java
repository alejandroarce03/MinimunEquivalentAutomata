
public class Relation {
	
	private String input;
	private String output;
	private State sourceState;
	private State destinationState;
	
	public Relation(String i,String o,State srcState,State dstState) {
		input = i;
		output = o;
		sourceState = srcState;
		destinationState = dstState;
	}
	
	public Relation(String i,State srcState,State dstState) {
		input = i;
		sourceState = srcState;
		destinationState = dstState;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public State getSourceState() {
		return sourceState;
	}

	public State getDestinationState() {
		return destinationState;
	}

}
