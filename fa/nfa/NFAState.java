package fa.nfa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public NFAState(String name){
		initialize(name);
		this.isFinal = false;
	}
	
	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param isFinal the type of state: true - final, false - non-final.
	 */
	public NFAState(String name, Boolean isFinal) {
		initialize(name);
		this.isFinal = isFinal;
	}
	
	/**
	 * Add the transition from <code> this </code> object
	 * @param onSymb the alphabet symbol
	 * @param toState to DFA state
	 */
	public void addTransition(char onSymb, NFAState to){
		if(delta.containsKey(onSymb)) { //has a list already
			//get that list
			List<NFAState> tempList = delta.get(onSymb);
			tempList.add(to);
			delta.remove(onSymb);
			delta.put(onSymb, tempList);
		} else { //no list exists for this symbol
			List<NFAState> newList = new ArrayList<NFAState>(); //TODO had to choose an implementation of List. Correct choice?
			newList.add(to);
			delta.put(onSymb, newList);
		}

	}
	
	/**
	 * Helper function for object creation
	 * @param name Name of the NFA
	 */
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
		Set<NFAState> retval = new HashSet<NFAState>();
		retval.add(this);
		List<NFAState> list = delta.get(symb);
			if(list != null) { 
				retval = new HashSet<NFAState>(); //I}TODO: Determine if HashSet is best option here
				for(NFAState s : list) { 
					retval.add(s);
				}
			}
		return retval;
	}
}
