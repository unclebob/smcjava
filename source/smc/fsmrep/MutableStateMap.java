package smc.fsmrep;

import java.util.Vector;
import java.util.HashSet;
import java.util.Iterator;

// in jdk 1.2
//import java.util.Vector;
//import java.util.HashSet;
//import java.util.Iterator;

//-----------------------------------------------------
// Name
//  MutableStateMap
//
// Description
//  This class represents the structure which holds the
//  representation of states and transitions.  
//

public class MutableStateMap implements StateMap
{
    ConcreteState itsInitialState;
    Vector itsOrderedStates;		// holds States
    HashSet itsEvents;              // holds Strings
    HashSet itsActions;             // holds Strings
    String itsName;
    String itsContextName;
    String itsExceptionName;
    String itsErrorFunctionName;
    String itsVersion;
    Vector itsPragma;              // holds Strings

    public MutableStateMap()
    {
        itsOrderedStates = new Vector();
        itsEvents = new HashSet();
        itsActions = new HashSet();
        itsPragma = new Vector();
    }
    public void setInitialState(ConcreteState s)         
        { itsInitialState = s; }
    public void setName(String theName)
        { itsName = theName; }
    public void setContextName(String theName)     
        { itsContextName = theName; }
    public void setExceptionName( String theName )
        { itsExceptionName = theName; }
    public void setErrorFunctionName( String theName )
        { itsErrorFunctionName = theName; }
    public void addPragma(String theName)
        { itsPragma.addElement(theName); }
    public void setVersion(String theVersion)      
        { itsVersion = theVersion; }

    public void addOrderedState(State s)
    {
        // if the state isn't already in itsOrderedStates, add it at the end
        if( itsOrderedStates.contains(s) == false )
            itsOrderedStates.addElement(s);
    }
    public void addTransition(State s, Transition t)
    {
        s.addTransition(t);
        itsEvents.add(t.getEvent());

	  Vector actions = t.getActions();
	  Iterator i = actions.iterator();
	  while( i.hasNext() )
            itsActions.add( i.next() );
    }

    public ConcreteState getInitialState()
        { return itsInitialState; }
    public Vector getOrderedStates()
        { return (itsOrderedStates); }
    public HashSet getEvents() 
        { return (itsEvents); }
    public HashSet getActions() 
        { return (itsActions); }
    public String getName() 
        { return itsName; }
    public String getContextName() 
        { return itsContextName; }
    public Vector getPragma() 
        { return itsPragma; }
    public String getVersion() 
        { return itsVersion; }
    public String getExceptionName()
        { return itsExceptionName; }
    public String getErrorFunctionName()
        { return itsErrorFunctionName; }
};
