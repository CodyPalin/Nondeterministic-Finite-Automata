package fa.nfa;
import java.util.Set;
import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

/**
 * 
 * @author Cody Palin, Dominick Edmonds
 *
 */
public class NFA implements NFAInterface{
	private Set<NFAState> states;
	private NFAState startState;
	private Set<Character> alphabet;
	
	public NFA() {
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DFA getDFA() { //TODO: THEOREM 1.39
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		// TODO Auto-generated method stub
		return null;
	}

}
