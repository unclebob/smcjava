package smc.builder.stateRep;

import smc.builder.SyntaxLocation;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.State;
import smc.fsmrep.SuperState;
import smc.fsmrep.SuperStateImpl;

public class SuperStateRep extends StateRep
{
    public SuperStateRep( String theName,  SyntaxLocation loc)
    {
        super(theName, loc);
    }
    public State build(FSMRepresentationBuilder fb)
    {
        // Many other states may depend upon super states.  Thus there may
        // be many requests for them to be built.  Thus we have to check to
        // see if its has already been built, and ignore any subsequent
        // requests.
        //
        // Also, since super states do not depend upon anyone, they can be
        // built as soon as they are seen.

        SuperState retval;
        if (fb.isStateBuilt(getStateName()) == false)
        {
            SuperStateImpl ss = new SuperStateImpl( getStateName() );
            fb.addBuiltSuperState( ss );
            retval = (SuperState)ss;
        }
        else
        {
            retval = fb.getBuiltSuperState( getStateName());
        }

        State s = (State)retval;
        return s;
    }

    public boolean equals(StateRep s )
    {
        if( s.getStateName() == getStateName() && (s instanceof SuperStateRep) )
            return true;
        else
            return false;
    }

    public String toString()
    {
        String buf = "(" + getStateName() + ")";
        return buf;
    }
}
