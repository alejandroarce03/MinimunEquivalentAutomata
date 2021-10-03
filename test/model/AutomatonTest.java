package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AutomatonTest {
	
	private Automaton automaton;
	
	public void setup1() {	
		automaton = new Automaton("Mealy","q0;q1;q2","0,0,q0,q1;1,0,q0,q2;0,0,q1,q1;1,1,q1,q2;0,1,q2,q1;1,0,q2,q2");
	}
	
	public void setup2() {	
		automaton = new Automaton("Moore","q0,0;q1,1;q2,0;q3,0","0,q0,q1;1,q0,q2;0,q1,q1;1,q1,q2;0,q2,q1;1,q2,q2;0,q3,q0;1,q3,q2");
	}
	public void setup3() {
		automaton = new Automaton("Mealy","A;B;C;D;E;F;G;H","0,0,A,B;1,0,A,C;0,0,B,A;1,0,B,D;0,0,C,E;1,0,C,C;0,0,D,E;1,0,D,C;0,0,E,A;1,0,E,G;0,0,F,D;1,0,F,A;0,0,G,E;1,1,G,H;0,1,H,H;1,1,H,H");
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
	@Test
	void testMooreAutomaton() {
		setup2();
		
		assertEquals(automaton.getStates().get(0).getName(),"q0");
		assertEquals(automaton.getStates().get(0).getValue(),"0");
		assertEquals(automaton.getStates().get(1).getName(),"q1");
		assertEquals(automaton.getStates().get(1).getValue(),"1");
		assertEquals(automaton.getStates().get(2).getName(),"q2");
		assertEquals(automaton.getStates().get(2).getValue(),"0");
		
		assertEquals(automaton.getRelations().get(0).getInput(),"0");
		assertEquals(automaton.getRelations().get(0).getOutput(),null);
		
		assertEquals(automaton.getStates().get(1).getInputs().size(),3);
		assertEquals(automaton.getStates().get(0).getOutputs().get(0).getSourceState().getName(),"q0");
		assertEquals(automaton.getStates().get(1).getOutputs().get(0).getSourceState().getName(),"q1");
		assertEquals(automaton.getStates().get(1).getOutputs().get(0).getDestinationState().getName(),"q1");
		
	}
	
	@Test
	void testEliminateInaccessibleStates() {
		setup2();
		
		automaton.removeInaccessibleStates();
		assertEquals(automaton.getStates().get(0).getName(),"q0");
		assertEquals(automaton.getStates().size(),3);
	}
	/**
	@Test
	void testFirstPartitionMoore() {
		setup2();
		
		ArrayList<ArrayList<State>> r = automaton.getFirstPartition();
		System.out.println(r);
	}
	*/
	@Test 
	void testFirstPartitionMealy() {
		setup3();
		
		ArrayList<ArrayList<State>> r = automaton.getFirstPartition();
		System.out.println(r);
	}
	


}
