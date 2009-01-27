package smc.builder.stateRep;

import smc.builder.SyntaxLocation;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.State;
import java.util.*;

public abstract class StateRep
{
    private String itsName;
    private SyntaxLocation itsSyntaxLocation;
    private HashSet itsBuiltEvents;
    private Vector itsEntryActions;
    private Vector itsExitActions;

    public StateRep( String theName,  SyntaxLocation loc)
    {
        itsName = theName;
        itsSyntaxLocation = loc;
        itsBuiltEvents = new HashSet();
        itsEntryActions = new Vector();
        itsExitActions = new Vector();
    }
    
    public void addBuiltEvent( String theEvent)
    { itsBuiltEvents.add(theEvent); }
    public void addEntryAction( String a)
    { itsEntryActions.add(a); }
    public void addExitAction( String a)
    { itsExitActions.add(a); }

    public String getStateName()
    { return itsName; }
    public SyntaxLocation getSyntaxLocation()
    { return itsSyntaxLocation; }
    public Vector getEntryActions()
    { return itsEntryActions; }
    public Vector getExitActions()
    { return itsExitActions; }

    public boolean isEventBuilt( String e)
    { return( itsBuiltEvents.contains(e) ); }

    public abstract State build(FSMRepresentationBuilder fb);
    public abstract boolean equals(StateRep s );
}
