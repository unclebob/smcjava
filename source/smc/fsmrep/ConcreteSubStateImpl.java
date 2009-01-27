package smc.fsmrep;

//----------------------------------
// Name
//  ConcreteSubStateImpl
//
// Description
//  This class represents a substate which is concrete, i.e. is not
//  also a super state.  Thus it can be the target of a transition.
//
public class ConcreteSubStateImpl extends StateImpl 
                                  implements ConcreteState, SubState
{
    private SuperState itsSuperState;

    public ConcreteSubStateImpl(String theName, SuperState ss)
    { 
        super( theName );  
        itsSuperState = ss; 
    }

    public SuperState getSuperState()
    {
        return itsSuperState;
    }
};
