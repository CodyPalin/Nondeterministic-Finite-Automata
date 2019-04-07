package fa.nfa;
import fa.State;

/**
 * Implementation of an NFA state.
 * 
 * @author Cody Palin, Dominick Edmonds
 */
public class NFAState extends State {
	private Boolean isFinal; //To remember it's type, ala DFA State
	//delta
	
	public NFAState(String name){
		initialize(name);
		isFinal = false;
	}
	
	
	
	public NFAState(String name, Boolean isFinal) {
		initialize(name);
		this.isFinal = isFinal;
	}
	
	private void initialize(String name){
		this.name = name;
		//delta
	}
	
	/**
	 * Getter for state finality
	 * @return True if state is final
	 */
	public boolean isFinal(){
		return isFinal;
	}
	
}
