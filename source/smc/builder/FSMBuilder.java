package smc.builder;

//----------------------------------------------
// Name
//  FSMBuilder
//
// Description
//  This class provides the interface that the FSMParser uses
//  to declare errors.  The parsing agent is expected to derive its own
//  implementation of this class.
//
public abstract class FSMBuilder
{
    private FSMBuilderErrorManager itsErrorManager;

    public abstract void setName(String theName);
    public abstract void setContextName(String theName);
    public abstract void setException(String e);
    public abstract void setInitialState(String theName);
    public abstract void setVersion(String theVersion);
    public abstract void addPragma(String theHeader);

    public abstract void addSuperSubState(String theName, String theSuperState, SyntaxLocation loc);
    public abstract void addSuperState(String theName,SyntaxLocation loc);
    public abstract void addSubState(String theName,String theSuperState,SyntaxLocation loc);
    public abstract void addState(String theName, SyntaxLocation loc);
    public abstract void addTransition(String theEvent,String theNextState,SyntaxLocation loc );
    public abstract void addInternalTransition(String theEvent,SyntaxLocation loc);
    public abstract void addAction(String theAction);
    public abstract void addEntryAction(String theAction);
    public abstract void addExitAction(String theAction);
    public abstract boolean build();

    public void setErrorManager(FSMBuilderErrorManager m)
    {itsErrorManager = m;}

    public void error(String theString)
    {
        if( itsErrorManager != null )
            itsErrorManager.error(theString);
    }

    public void error(SyntaxLocation loc, String theString)
    {
        if( itsErrorManager != null )
            itsErrorManager.error(loc, theString);
    }
}

