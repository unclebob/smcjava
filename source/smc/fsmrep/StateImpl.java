package smc.fsmrep;

import java.util.Vector;
import java.util.HashSet;

//----------------------------------
// Name
//  StateImpl
//
// Description
//  This is the abstract base class which represents
//  a state in the finite state machine.
//
public abstract class StateImpl implements State
{
    private String itsName;
    private HashSet itsTransitions;         // holds Transitions
    private Vector itsEntryActions;         // holds Strings
    private Vector itsExitActions;          // holds Strings

    public StateImpl( String theName)
    {
        itsName = theName;
        itsTransitions = new HashSet();
        itsEntryActions = new Vector();
        itsExitActions = new Vector();
    }
    public String getName()
	{
	    return itsName;
	}
    public HashSet getTransitions()  
	{
	    return itsTransitions;
	}
    public Vector getEntryActions() 
	{
	    return itsEntryActions;
    }
    public Vector getExitActions() 
    {
        return itsExitActions;
    }
    public void addTransition(Transition t)
    {
        itsTransitions.add(t);
    }
    public void setEntryActions(Vector theActions)
    {
        itsEntryActions = theActions;
    }
    public void setExitActions(Vector theActions)
    {
        itsExitActions = theActions;
    }
};
