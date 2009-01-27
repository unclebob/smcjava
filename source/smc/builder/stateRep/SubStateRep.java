package smc.builder.stateRep;

import smc.builder.*;
import smc.fsmrep.*;

public class SubStateRep extends StateRep
{
    private String itsSuperStateName;

    public SubStateRep( String theName, String theSuper, SyntaxLocation loc)
    {
        super(theName, loc);
        itsSuperStateName = theSuper;
    }

    public String getSuperStateName()
    { return itsSuperStateName; }

    public State build(FSMRepresentationBuilder fb)
    {
        ConcreteSubStateImpl retval = null;
        StateRep sr = fb.getStateRep(getSuperStateName());

        if( sr != null)
        {
            sr.build(fb);
            SuperState superState = fb.getBuiltSuperState(getSuperStateName());
            if( superState != null)
            {
                retval = new ConcreteSubStateImpl(getStateName(),superState);
                fb.addBuiltConcreteState(retval);
            }
            else
            {
                String e = "Could not build sub state (" + getStateName() +
                        ") because super state (" + getSuperStateName() + ") had an error.";
                fb.setError();
                fb.error( sr.getSyntaxLocation(), e );
            }
        }
        else
        {
            String e = "Super state (" + getSuperStateName() + ") was not declared.";
            fb.setError();
            fb.error(null, e );
        }
        return retval;
    }

    public boolean equals(StateRep s )
    {
        if( s.getStateName() == getStateName() &&(s instanceof SubStateRep))
        {
            SubStateRep sr = (SubStateRep)s;
            if( sr.getSuperStateName() == getSuperStateName() )
                return true;
        }
        return false;
    }

    public String toString()
    {
        String buf = getStateName() + ":" + getSuperStateName();
        return buf;
    }
}
