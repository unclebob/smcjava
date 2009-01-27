package smc.builder.stateRep;

import smc.builder.SyntaxLocation;

import java.util.Vector;

public class TransitionRep
{
    private String itsStartingState;
    private String itsEvent;
    private String itsEndingState;
    private Vector itsActions;
    private SyntaxLocation itsSyntaxLocation;
    
    public TransitionRep(  String startState,  String event, String endState,  SyntaxLocation loc )
    {
        itsStartingState = startState;
        itsEvent = event;
        itsEndingState = endState;
        itsSyntaxLocation = loc;
        itsActions = new Vector();
    }

    public void addAction( String a )
    { itsActions.addElement( a ); }
    public String getEvent()
    { return itsEvent; }
    public String getStartingState()
    { return itsStartingState; }
    public String getEndingState()
    { return itsEndingState; }
    public SyntaxLocation getSyntaxLocation()
    { return itsSyntaxLocation; }
    public Vector getActions()
    { return itsActions; }
}
