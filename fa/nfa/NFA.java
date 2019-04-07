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
			System.out.println("WARNING: A state who's name = \"" + name + "\" already exists in the NFA");
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
		if( s == null){
			s = new NFAState(name, true);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name \"" + name + "\" already exists in the DFA");
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
			System.err.println("ERROR: No DFA state exists with name " + fromState);
			System.exit(2);
		} else if (to == null){
			System.err.println("ERROR: No DFA state exists with name " + toState);
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
		return null;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		//TODO
		//Will use NFAState.getTo() to get states that can be reached by symb "epsilon".
		//Will then recursively check each of those states for additional epsilon transitions
			//Issue: how to prevent repeats (e.g, an epsilon loop exists between two states-- infinite recursion is bad, mmk)
		return null;
	}

}
