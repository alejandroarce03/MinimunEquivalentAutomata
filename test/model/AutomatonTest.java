package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AutomatonTest {
	
	private Automaton automaton;
	
	public void setup1() {	
		automaton = new Automaton("Mealy","q0;q1;q2","0,0,q0,q1;1,0,q0,q2;1,0,q2,q2;0,0,q1,q1;1,1,q1,q2;0,1,q2,q1");
	}
	
	public void setup2() {	
		automaton = new Automaton("Mealy","q0;q1;q2","0,0,q0,q1;1,0,q0,q2;1,0,q2,q2;0,0,q1,q1;1,1,q1,q2;0,1,q2,q1");
	}

	@Test
	void testMealyAutomaton() {
		setup1();
		
		assertEquals(automaton.getStates().get(0).getName(),"q0");
		assertEquals(automaton.getStates().get(1).getName(),"q1");
		assertEquals(automaton.getStates().get(2).getName(),"q2");
		
		assertEquals(automaton.getRelations().get(0).getInput(),"0");
		assertEquals(automaton.getRelations().get(0).getOutput(),"0");
		
		assertEquals(automaton.getStates().get(0).getInputs().size(),0);
		assertEquals(automaton.getStates().get(0).getOutputs().get(0).getSourceState().getName(),"q0");
		assertEquals(automaton.getStates().get(1).getOutputs().get(0).getSourceState().getName(),"q1");
		assertEquals(automaton.getStates().get(1).getOutputs().get(0).getDestinationState().getName(),"q1");
	}
	


}
