package smc.fsmrep;

import java.util.Vector;

//-------------------------------
// Name
//  ExternalTransition
//
// Description
//  This class represents a transition from one state to another and
//  the event that causes this to happen.
//
public class ExternalTransition extends Transition
{
    private ConcreteState itsNextState;

    public ExternalTransition(String theEvent, 
                   State theSourceState, ConcreteState nextState)
    {
        super(theEvent, theSourceState);
        itsNextState = nextState;
    }

    public void setNextState( ConcreteState nextState )
    {
        itsNextState = nextState;
    }

    public ConcreteState getNextState()
    {
        return itsNextState;
    }
};
