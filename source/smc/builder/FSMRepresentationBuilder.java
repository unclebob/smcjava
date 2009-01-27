package smc.builder;

import smc.fsmrep.*;
import smc.builder.stateRep.*;

import java.util.*;

//----------------------------------
// Name
//  FSMRepresentationBuilder
//
// Description
//  This is the class derived from FSMBuilder which stores the
//  parsed FSM into the intermediate representation and builds
//  the final State machine representation
//
public class FSMRepresentationBuilder extends FSMBuilder
{
    private boolean itsError;
    private MutableStateMap itsStateMap;
    private String itsInitialState;
    private List itsTransitions;
    private TransitionRep itsCurrentTransition;
    private String itsCurrentState;
    private HashSet itsStates;
    private HashMap itsStateReps;
    private HashMap itsSuperStateDictionary;
    private HashMap itsConcreteStateDictionary;
    private HashMap itsStateDictionary;

    public FSMRepresentationBuilder()
    {
        itsStateMap = new MutableStateMap(); 
        itsTransitions = new ArrayList();
        itsStates = new HashSet();
        itsStateReps = new HashMap();
        itsSuperStateDictionary = new HashMap();
        itsConcreteStateDictionary = new HashMap();
        itsStateDictionary = new HashMap();

        itsError = false;

        itsStateMap.setName( "FSMName" );
        itsStateMap.setContextName( "FSMContext" );
        itsStateMap.setExceptionName( "" );
        itsStateMap.setErrorFunctionName("FSMError");
        itsStateMap.setVersion( "" );
    }

    public StateMap getStateMap()
    { return itsStateMap; }
    public List getTransitions()
    { return itsTransitions; }
    public HashSet getStates()
    { return itsStates; }

    public void setInitialState( String theName)
    { itsInitialState = theName; }

    public void setName( String theName)
    { itsStateMap.setName(theName); }
    public void setContextName( String theName)
    { itsStateMap.setContextName(theName); }
    public void setException( String e)
    { itsStateMap.setExceptionName(e); }
    public void setVersion( String theVersion)
    { itsStateMap.setVersion( theVersion); }
    public void addPragma( String theName)
    { itsStateMap.addPragma(theName); }

    public void addSuperSubState( String theName, String theSuperState, SyntaxLocation loc)
    { 
        addStateRep(new SuperSubStateRep(theName, theSuperState, loc));
    }
    public void addSubState( String theName, String theSuperState, SyntaxLocation loc)
    {    
        addStateRep(new SubStateRep(theName, theSuperState, loc));
    }
    public void addSuperState( String theName,  SyntaxLocation loc)
    { 
        addStateRep(new SuperStateRep(theName, loc));
    }
    public void addState( String theName,  SyntaxLocation  loc)
    {
        addStateRep(new NormalStateRep(theName, loc));
    }
    public void addTransition( String theEvent, String theNextState, SyntaxLocation loc)
    {
        itsCurrentTransition = new TransitionRep( itsCurrentState,theEvent, theNextState, loc);
        itsTransitions.add(itsCurrentTransition);
    }
    public void addInternalTransition( String theEvent, SyntaxLocation loc)
    {
        // An internal transition is simply a transition which stays inside
        // its current state.  We are modeling it here as a regular transition
        // whose "theNextState" field is blank.

        addTransition( theEvent, "", loc);
    }
    public void addAction( String theAction)
    {
        itsCurrentTransition.addAction( theAction );
    }
    public void addEntryAction( String theAction)
    {
        StateRep sr = getStateRep( itsCurrentState );
        sr.addEntryAction(theAction);
    }
    public void addExitAction( String theAction)
    {
        StateRep sr = getStateRep( itsCurrentState );
        sr.addExitAction(theAction);
    }
    public void addBuiltSuperState(SuperState s)
    {
        itsSuperStateDictionary.put( s.getName(), s);
        addBuiltState((State)s);
    }
    public void addBuiltConcreteState(ConcreteState s)
    {
        itsConcreteStateDictionary.put( s.getName(), s);
        addBuiltState(s);
    }
    public StateRep getStateRep( String stateName)
    {
        return (StateRep)(itsStateReps.get( stateName ));
    }
    public boolean isStateBuilt( String stateName)
    {
        return( itsStateDictionary.containsKey( stateName ) );
    }
    public State getBuiltState( String stateName )
    {
        return (State)(itsStateDictionary.get( stateName ));
    }
    public SuperState getBuiltSuperState( String stateName)
    {
        return (SuperState)( itsSuperStateDictionary.get( stateName ) );
    }
    public ConcreteState getBuiltConcreteState( String stateName)
    {
        return (ConcreteState)(itsConcreteStateDictionary.get( stateName ));
    }
    public boolean build()
    {
        if (itsError == false )
        {
            buildStateMap();
            if (itsError == true)
            {
                error("Aborting due to inconsistent input.");
                return false;
            }
        }
        else
        {
            error("Aborting due to semantic errors");
            return false;
        }
        return true;
    }
    public void setError() 
    { itsError = true; }

    private void addStateRep(StateRep theStateRep)
    {
        String name = theStateRep.getStateName();
        StateRep stateRep = getStateRep(name);

        if( stateRep != null )
        {
            if( stateRep != theStateRep)
            {
                String errorStatement = "Redefinition of state (" + name + ")";
                setError();
                error( theStateRep.getSyntaxLocation(), errorStatement );
            }
        }
        else
        {
            itsCurrentState = name;
            itsStateReps.put( name, theStateRep );
            itsStates.add(name);
        }
    }
    private void addBuiltState(State state)
    {
        itsStateDictionary.put(state.getName(), state);
        itsStateMap.addOrderedState(state);
    }
    private void buildStateMap()
    {
        setEntryAndExitActions();
        setTransitions();
        setInitialState();
    }
    private void setInitialState()
    {
        ConcreteState csfound = getBuiltConcreteState(itsInitialState);

        if (csfound != null)
        {
            itsStateMap.setInitialState(csfound);
        }
        else
        {
            String errorStatement = "Initial state (" + itsInitialState + ") is not concrete.";
            setError();
            error(errorStatement);
        }
    }
    private void setTransitions()
    {
        for(int i=0;i!=itsTransitions.size();i++)
        {
            TransitionRep transitionRep = (TransitionRep)itsTransitions.get(i);
            String stateName = transitionRep.getStartingState();
            String eventName = transitionRep.getEvent();
            String endingStateName = transitionRep.getEndingState();

            StateRep stateRep = getStateRep(stateName);
            if( stateRep != null )
            {
                if (stateRep.isEventBuilt(eventName) == false )
                {
                    stateRep.addBuiltEvent(eventName);

                    ConcreteState concreteState = getBuiltConcreteState(endingStateName);
                    if( (concreteState != null) || endingStateName == "")
                    {
                        State state = getBuiltState( stateName );
                        if ( state != null )
                        {
                            Transition transition = null;

                            if (endingStateName != "")
                                transition = new ExternalTransition(eventName, state, concreteState);
                            else
                                transition = new InternalTransition(eventName, state);

                            itsStateMap.addTransition(state, transition);

                            Vector actions = transitionRep.getActions();
                            Iterator ai = actions.iterator();

                            while( ai.hasNext() )
                                transition.addAction( (String)ai.next() );
                        }
                        else
                        {
                            String errorStatement = "Cannot build transitions for " + stateName + " due to previous errors.";
                            error(errorStatement);
                        }
                    }
                    else
                    {
                        callError("Ending state (" + endingStateName +   ") is not a concrete state.",transitionRep);
                    }
                }
                else
                {
                    callError("Event (" + eventName +   ") already defined for state (" + stateName + ").",transitionRep);
                }
            }
            else
            {
                callError("State (" + stateName + ") not declared." , transitionRep);
            }
        }
    }
    private void callError(String errorStatement,TransitionRep transitionRep)
    {
        setError();
        error(transitionRep.getSyntaxLocation(),errorStatement);
    }

    private void setEntryAndExitActions()
    {
        Iterator sri = itsStates.iterator();
        while( sri.hasNext() )
        {
            String stateName = (String)sri.next();
            StateRep stateRep = getStateRep(stateName);

            if( stateRep != null )
            {
                State state = stateRep.build(this);
                if (state != null)
                {
                    state.setEntryActions(stateRep.getEntryActions());
                    state.setExitActions(stateRep.getExitActions());
                }
            }
        }
    }


}
