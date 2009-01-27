package smc.fsmrep;

import java.util.Vector;
//import java.util.Vector; // jdk 1.2

//-------------------------------
// Name
//  Transition
//
// Description
//  This class represents a transition from one state to another or
//  a transition into the same state based upon some event.
//

public abstract class Transition
{
    private State  itsSourceState;
    private String  itsEvent;
    private Vector itsActions;

    public Transition(String theEvent, State theSourceState)
    {
        itsEvent = theEvent;
        itsSourceState = theSourceState;
        itsActions = new Vector();
    }

    public String getEvent()
	{
	    return itsEvent;
	}
    public State getSourceState()
	{
	    return itsSourceState;
	}
    public Vector getActions()
	{
	    return itsActions;
	}
    public void addAction(String a)
    {
        itsActions.addElement( a );
    }
}
