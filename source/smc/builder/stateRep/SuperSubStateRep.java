package smc.builder.stateRep;

import smc.builder.SyntaxLocation;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.State;
import smc.fsmrep.SuperState;
import smc.fsmrep.SuperSubStateImpl;

public class SuperSubStateRep extends StateRep
{
    private String itsSuperStateName;

    public SuperSubStateRep( String theName, String theSuper, SyntaxLocation loc)
    {
        super(theName, loc);
        itsSuperStateName = theSuper;
    }

    public String getSuperStateName()
    { return itsSuperStateName; }

    public State build(FSMRepresentationBuilder fb)
    {
        // SuperSub states depend upon other states, and so must request
        // that they be built first.  Also, they are depended upon by other
        // states, so they must ignore duplicate requests for being built.

        State retval = null;

        if (fb.isStateBuilt(getStateName()) == false)
        {
            StateRep sr = fb.getStateRep(getSuperStateName());
            if( sr != null )
            {
                sr.build(fb);
                SuperState superState = fb.getBuiltSuperState(getSuperStateName());
                if( superState != null)
                {
                    SuperSubStateImpl s = new SuperSubStateImpl(getStateName(), superState);
                    retval = (State)s;
                    fb.addBuiltSuperState(s);
                }
                else
                {
                    String e = "Could not build super sub state (" + getStateName() +
                            ") because super state (" + getSuperStateName() + ") had an error.";
                    fb.setError();
                    fb.error( sr.getSyntaxLocation(), e);
                }
            }
            else
            {
                String e = "Super state (" + getSuperStateName() + ") was not declared.";
                fb.setError();
                fb.error( sr.getSyntaxLocation(), e );
            }
        }
        else
        {
            retval = fb.getBuiltState(getStateName());
        }
        return retval;
    }

    public boolean equals( StateRep s )
    {
        if( s.getStateName() == getStateName() && (s instanceof SuperSubStateRep) )
        {
            SuperSubStateRep sr = (SuperSubStateRep)s;
            if( sr.getSuperStateName() == getSuperStateName())
                return true;
        }
        return false;
    }

    public String toString()
    {
        String buf = "(" + getStateName() + ") : " + getSuperStateName();
        return buf;
    }
}
