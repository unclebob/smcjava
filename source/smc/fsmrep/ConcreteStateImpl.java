package smc.fsmrep;

//----------------------------------
// Name
//  ConcreteStateImpl
//
// Description
//  This class represents a concrete state in the finite
//  state machine.  A concrete state can be the target of
//  a transition.
//
public class ConcreteStateImpl extends StateImpl implements ConcreteState
{
    public ConcreteStateImpl(String theName)
    { 
        super( theName );   
    }
};
