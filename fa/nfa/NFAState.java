package fa.nfa;
import java.util.HashMap;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

/**
 * Implementation of an NFA state.
 * 
 * @author Cody Palin, Dominick Edmonds
 */
public class NFAState extends State {
	private Boolean isFinal; //To remember it's type, ala DFAState
	private HashMap<Character,NFAState> delta; //set of transitions for this state
	
	public NFAState(String name){
		initialize(name);
		this.isFinal = false;
	}
	
	public void addTransition(char onSymb, NFAState to){
		delta.put(onSymb, to);
	}
	
	public NFAState(String name, Boolean isFinal) {
		initialize(name);
		this.isFinal = isFinal;
	}
	
	private void initialize(String name){
		this.name = name;
		delta = new HashMap<Character, NFAState>();
	}
	
	/**
	 * Getter for state finality
	 * @return True if state is final
	 */
	public boolean isFinal(){
		return isFinal;
	}
	
	/**
	 * Retrieves the set of states that <code>this</code> transitions to
	 * on the given symbol
	 * @param symb - the alphabet symbol
	 * @return the new states the symbol leads to
	 */
	public Set<NFAState> getTo(char symb){ 
		//TODO
	}
	
}
