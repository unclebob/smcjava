package smc.fsmrep;

//----------------------------------
// Name
//  SubState
//
// Description
//  This interface represents a state
//  which inherits some transitions from a super state.
//
public interface SubState extends State
{
    public abstract SuperState getSuperState();
};
