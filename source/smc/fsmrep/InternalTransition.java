package smc.fsmrep;

import java.util.Vector;

//-------------------------------
// Name
//  InternalTransition
//
// Description
//  This class represents a transition based upon some event, where
//  the end result is to stay in the original state.
//
public class InternalTransition extends Transition
{
    public InternalTransition(String theEvent, State theSourceState)
    {
        super(theEvent, theSourceState);
    }
};
