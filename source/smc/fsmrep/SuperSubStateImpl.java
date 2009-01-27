package smc.fsmrep;

//----------------------------------
// Name
//  SuperSubStateImpl
//
// Description
//  This class represents a superstate which is both a super
//  state and a sub state. Thus it can not be the target of a transition.
//
public class SuperSubStateImpl extends StateImpl 
                               implements SubState, SuperState
{
    private SuperState itsSuperState;

    public SuperSubStateImpl(String theName, SuperState ss)
    { 
        super( theName );  
        itsSuperState = ss; 
    }

    public SuperState getSuperState()
    {
        return itsSuperState;
    }
};
