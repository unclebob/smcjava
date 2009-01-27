package smc.builder.stateRep;

import smc.builder.SyntaxLocation;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.State;
import smc.fsmrep.ConcreteStateImpl;

public class NormalStateRep extends StateRep
{
    public NormalStateRep( String theName,  SyntaxLocation loc)
    {
        super(theName, loc);
    }
    public State build(FSMRepresentationBuilder fb)
    {
        ConcreteStateImpl retval = new ConcreteStateImpl(getStateName());
        fb.addBuiltConcreteState(retval);
        return retval;
    }
    public boolean equals(StateRep s )
    {
        if( s.getStateName() == getStateName() && (s instanceof NormalStateRep) )
            return true;
        else
            return false;
    }
    public String toString()
    {
        return getStateName();
    }
}
