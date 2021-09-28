
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
}
