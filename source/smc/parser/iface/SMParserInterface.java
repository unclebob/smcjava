package smc.parser.iface;

public abstract interface SMParserInterface
{

    public abstract void setFSMName(String s);      
    // Set the name of the Finite State Machine

    public abstract void setContextName(String s);  
    // Set the name of the Context class that the finite state machine 
    // will inherit from

    public abstract void setException(String s);  
    // Set the name of the Exception class that is used by the finite
    // state machine

    public abstract void setInitialState(String s);
    // Set the initial state of the FSM

    public abstract void setVersion(String s);      
    // Set the version string;

    public abstract void setFSMGenerator(String s );
    // Set the name of the class that represents the FSM code generator

    public abstract void addPragma(String s );
    // add a pragma statement for the FSM code generator

    public abstract void processFSM();           
    // The FSM data structures are built, so do whatever needs to be 
    // done to process them.

    public abstract void addSuperSubState(String name, String sup, int lNum);
    // Add a SuperSubState to the FSM

    public abstract void addSuperState(String name, int lNum);
    // Add A SuperState to the FSM

    public abstract void addSubState(String name, String sup, int lNum);
    // Add A SubState to the FSM

    public abstract void addState(String name, int lNum);   
    // Add a regular state to the FSM

    public abstract void addTransition(String event, 
                                    String nextState, int lNum);
    // Add a transition to the FSM

    public abstract void addInternalTransition(String event, int lNum);
    // Add an internal transition to the FSM

    public abstract void addAction(String action, int lNum);
    // Add an action to the last transition that was added to the FSM

    public abstract void addEntryAction(String action, int lNum); 
    // Add an action to the list of entry actions for the current state.

    public abstract void addExitAction(String action, int lNum); 
    // Add an action to the list of exit actions for the current state.
}


