
public class Relation {
	
	private String input;
	private String output;
	private String sourceState;
	private String destinationState;
	
	public Relation(String i,String o,String srcState,String dstState) {
		input = i;
		output = o;
		sourceState = srcState;
		destinationState = dstState;
	}
	
	public Relation(String i,String srcState,String dstState) {
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

	public String getSourceState() {
		return sourceState;
	}

	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}
	
	
}
