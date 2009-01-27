package smc.fsmrep;

//----------------------------------
// Name
//  SuperStateImpl
//
// Description
//  This class represents a super state.  A super state cannot
//  be the target of a transition.  It can, however, have transitions
//  out of itself.  Super states are used by substates which
//  inherit from them.
//
public class SuperStateImpl extends StateImpl implements SuperState
{
    public SuperStateImpl(String theName)
    { 
        super( theName );   
    }
}
