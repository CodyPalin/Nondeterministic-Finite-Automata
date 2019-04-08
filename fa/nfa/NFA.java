package fa.nfa;
import java.util.LinkedHashSet;
import java.util.Set;
import fa.State;
import fa.dfa.DFA;


/**
 * Implementation of an NFA
 * @author Cody Palin, Dominick Edmonds
 */
public class NFA implements NFAInterface{
	private Set<NFAState> states;
	private NFAState startState;
	private Set<Character> alphabet;
	
	public NFA() {
		states = new LinkedHashSet<NFAState>();
		alphabet = new LinkedHashSet<Character>();
	}
	
	@Override
	public void addStartState(String name) {
		NFAState s = checkIfExists(name);
		if(s == null){
			s = new NFAState(name);
			addState(s);
		} else {
			if(s.isFinal()){}
			else System.out.println("WARNING: A state who's name = \"" + name + "\" already exists in the NFA and is not final");
		}
		startState = s;
	}

	@Override
	public void addState(String name) {
		NFAState s = checkIfExists(name);
		if( s == null){
			s = new NFAState(name);
			addState(s);
		} else {
			System.out.println("WARNING: A state who's name = \"" + name + "\" already exists in the NFA");
		}
	}
	
	/**
	 * Adds a NFAState to the list of states
	 * @param s NFA State to add
	 */
	private void addState(NFAState s){
		states.add(s);
	}

	@Override
	public void addFinalState(String name) {
		NFAState s = checkIfExists(name);
		if( s == null ){
			s = new NFAState(name, true);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name \"" + name + "\" already exists in the NFA");
		}
	}
	
	/**
	 * Checks if the given state exits in this NFA. Very similar to the 
	 * DFA.java implementation.
	 * @param name The name of the state we're looking for
	 * @return null if the state does not exist, returns the state otherwise
	 */
	private NFAState checkIfExists(String name) {
		NFAState rv = null;
		for(NFAState s : states) {
			if(s.getName().equals(name)) { //State w/ this name already exists
				rv = s;
				break;
			} 
		}
	
		return rv;
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		NFAState from = checkIfExists(fromState);
		NFAState to = checkIfExists(toState);
		if(from == null){
			System.err.println("ERROR: No NFA state exists with name " + fromState);
			System.exit(2);
		} else if (to == null){
			System.err.println("ERROR: No NFA state exists with name " + toState);
			System.exit(2);
		}
		from.addTransition(onSymb, to);
		
		if(!alphabet.contains(onSymb)){
			alphabet.add(onSymb);
		}
	}

	@Override
	public Set<NFAState> getStates() {
		return states;
	}

	@Override
	public Set<NFAState> getFinalStates() {
		Set<NFAState> ret = new LinkedHashSet<NFAState>();
		for(NFAState s : states){
			if(s.isFinal()){
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public State getStartState() {
		return startState;
	}

	@Override
	public Set<Character> getABC() {
		return alphabet;
	}

	@Override
	public DFA getDFA() { 
		//TODO: THEOREM 1.39
		DFA retVal = new DFA();
		
		//BFS on the NFA for this; loop over a queue (where the queue elements are sets of NFAStates. 
		
		//find DFAStates ((all subsets of states from NFA = state list for DFA))
		//find DFAStartState = eClosure(getStartState)
		/*find DFAFinalStates = any member of DFA's new set of state subset's from the NFA that contain 
		 * a state that was final in the NFA
		 */
		
		//transition function ((!))
			//take list of DFA states from earlier; for each:
				//check each alphabet symbol for a transition
				//if no transition for a symbol, go to trap state T
				//else, verify which state it will go to in the DFAStates list
					//After reading an input symbol, check the state you go to for epsilon closure
					//Do this for any transitions from the state you're looking at that use the same symbol to transition
					/*once you have everything, look at the states you've touched in this iteration of the loop.
					 * The subset in DFAStates that contains all of those states is the one you want
					 */
		
		
		return retVal;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		Set<NFAState> states = new LinkedHashSet<NFAState>();
		Set<NFAState> newStates = s.getTo('e');
		if(states.addAll(newStates)) //if set changed
			for(NFAState t : newStates){
				Set<NFAState> lowerstates = eClosure(t);
				if(lowerstates != null)
					states.addAll(lowerstates);
			}
		else
			return null;
		return states;
	}

}
