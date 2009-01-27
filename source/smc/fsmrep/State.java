package smc.fsmrep;

import java.util.HashSet;
import java.util.Vector;
// import java.util.Vector;  // with jdk 1.2
// import java.util.HashSet;  // with jdk 1.2 

//----------------------------------
// Name
//  State
//
// Description
//  This is the interface which represents
//  a state in the finite state machine.
//
public interface State
{
    public abstract String getName();
    public abstract HashSet getTransitions(); 
    public abstract Vector getEntryActions();
    public abstract Vector getExitActions();
    public abstract void addTransition(Transition t);
    public abstract void setEntryActions(Vector theActions);
    public abstract void setExitActions(Vector theActions);
};
