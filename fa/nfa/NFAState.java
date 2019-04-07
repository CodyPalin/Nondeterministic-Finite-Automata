package fa.nfa;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fa.State;

/**
 * Implementation of an NFA state.
 * 
 * @author Cody Palin, Dominick Edmonds
 */
public class NFAState extends State {
	private Boolean isFinal; //To remember it's type, ala DFAState
	private HashMap<Character,List<NFAState>> delta; //set of transitions for this state
	
	public NFAState(String name){
		initialize(name);
		this.isFinal = false;
	}
	
	public void addTransition(char onSymb, NFAState to){
		//look up key
			//has a list already?
				//add that shit
			//no list?
				//fuckin make one bud
				//add that shit
		//delta.put(onSymb, to);
	}
	
	public NFAState(String name, Boolean isFinal) {
		initialize(name);
		this.isFinal = isFinal;
	}
	
	private void initialize(String name){
		this.name = name;
		delta = new HashMap<Character, List<NFAState>>();
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
	 * @return the list of all states the symbol leads to
	 */
	public Set<NFAState> getTo(Character symb){ 
		//TODO
		Set<NFAState>retval = null;
		//look up the key's list
			//if the list exists
				//return the list stored there as a Set<NFAState>
			//if the list does not exist
				//fuck. A B O R T!
		return retval;
			
	}

	
	
}
