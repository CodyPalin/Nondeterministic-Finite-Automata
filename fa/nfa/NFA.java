package fa.nfa;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
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
		//alphabet.add('e');
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
		
		if(!alphabet.contains(onSymb) && onSymb != 'e'){
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
	
	//queue for getDFA
	private Queue<Set<NFAState>> dfaStates = new LinkedList<>();
	
	/**
	 * Searches for and returns the set of states that will be in the
	 * DFA that is equivilent to this NFA
	 * @param s Set of states to base the search off of
	 */
	private void findDFAStates(Set<NFAState> s){
		for(char a : alphabet){ //for every alphabet symbol
			if(a != 'e'){
			Set<NFAState> closureSet = new LinkedHashSet<NFAState>();
			for(NFAState state : s){ //get the union of the closure of each element in the set
				Set<NFAState> toState = getToState(state, a); //could be multiple states a single alphabet symbol goes to
				if(toState != null){
				for(NFAState t : toState)
				{
					closureSet.addAll(eClosure(t));
				}}
			}
			if(!dfaStates.contains(closureSet) && !closureSet.isEmpty()) //if its a new set for the queue, need to make another row for this set as a state.
			{
				dfaStates.add(closureSet);
				findDFAStates(closureSet);
			}
			}
		}
	}
	@Override
	public DFA getDFA() { 
		//TODO: THEOREM 1.39
		DFA retVal = new DFA();
		//BFS on the NFA for this; loop over a queue (where the queue elements are sets of NFAStates. 
		//set up start state as first element in queue
		//find DFAStartState = eClosure(getStartState)
		Set<NFAState> DFAStartState = eClosure(startState);
		dfaStates.add(DFAStartState);
		retVal.addStartState(DFAStartState.toString());
		//find DFAStates ((all subsets of states from NFA = state list for DFA))
		findDFAStates(DFAStartState); //queue is now set up
		
		//find DFAFinalStates = any member of DFA's new set of state subset's from the NFA that contain a state that was final in the NFA
		Set<Set<NFAState>> DFAFinalStates = new LinkedHashSet<Set<NFAState>>();
		Queue<Set<NFAState>> q = dfaStates;
		for(Set<NFAState> set: q)
		{
			boolean isFinal = false;
			for(NFAState state : set)
			{
				if (state.isFinal()){
					DFAFinalStates.add(set);
					retVal.addFinalState(set.toString());
					isFinal = true;
					break;
				}
			}
			if(!isFinal && set!=DFAStartState){
				retVal.addState(set.toString());
			}
				
		}
		
		String trapState = "[]";
		//transition function ((!)) 
		//take list of DFA states from earlier; for each:
				//check each alphabet symbol for a transition
				//if no transition for a symbol, go to trap state T
				//else, verify which state it will go to in the DFAStates list
		for(Set<NFAState> set: q)
		{
			for(char abc : alphabet)
			{
				if(abc != 'e'){
				String nextStateString = trapState;
				Set<NFAState> nextState = new LinkedHashSet<NFAState>();
				for(NFAState state : set)
				{
					Set<NFAState> toStates=  getToState(state, abc);
					if(state != null&&toStates!=null){
						
						for(NFAState toState :toStates)
						{
							if(toState!= null)
							nextState.addAll(eClosure(toState));
						}}
				}
				if(!nextState.isEmpty())
				{
					nextStateString = nextState.toString();
				}
				if(nextState.isEmpty() && !retVal.getStates().contains(trapState))
				{
					
					retVal.addState(trapState);//add trap state
					for(char a : alphabet)
					{
						retVal.addTransition(trapState, a, trapState);
						
					}
				}
				retVal.addTransition(set.toString(), abc, nextStateString);
				}}
		}
			
		
		
		return retVal;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getTo(onSymb);
	}
	//private Set<NFAState> closureStates = new LinkedHashSet<NFAState>();
	@Override
	public Set<NFAState> eClosure(NFAState s) {
		Set<NFAState> states= new LinkedHashSet<NFAState>();
		if(s.getTo('e') != null)
		{
			for(NFAState next : s.getTo('e'))
			{
				
				states.addAll(eClosure(next)); 
				
					
			}
		}
		states.add(s);
		return states;
		
	}

}
