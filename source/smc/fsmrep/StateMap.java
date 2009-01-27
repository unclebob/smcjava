package smc.fsmrep;

import java.util.Vector;
import java.util.HashSet;

//----------------------------------
// Name
//  StateMap
//
// Description
//  This interface class represents the structure which holds the
//  representation of states and transitions.  The parser specifies the
//  implementation of this class.
//
public interface StateMap
{
    public abstract ConcreteState getInitialState();
        // Returns the State that the FSM starts up in.

    public abstract Vector  getOrderedStates();  
        // Returns a Vector containing States. The vector
        // is ordered by state dependencies.  i.e.
        // the order guarantees that all substates come
        // after their super states. 
   
    public abstract HashSet getEvents();		 
        // Returns a Set containing Strings representing all the event names.

    public abstract HashSet getActions();        
        // Returns a Set containing Strings representing all the action names.

    public abstract String getName();
        // Returns the name of the FSM.

    public abstract String getContextName();
        // Returns the name of the FSM Context.

    public abstract Vector getPragma();         
        // Returns a Vector containing Strings representing all the 
        // pragma fields that any generated code will be dependent upon.

    public abstract String getVersion();
        // Returns the name of the Version.

    public abstract String getExceptionName();
        // Returns the name of the Exception to be used by the FSM.

    public abstract String getErrorFunctionName();
        // Returns the name of the Error function to be used by the FSM.
};
