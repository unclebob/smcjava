package smc.stripper;

import java.io.PrintStream;


public class FSMException extends Exception
{
    private String itsTransition;
    private String itsState;

    public FSMException(String transition, String state)
    {
      super("Transition Error: " + transition + " in State: "
                     + state);
      itsTransition = transition;
      itsState = state;
    }

    public void Error(PrintStream out)
    {
        out.println("State: " + itsState + ", Event: "
                 + itsTransition + "(Error)");
    }
    public String toString()
    {
        StringBuffer buf = new StringBuffer().append("*** Error in State: ")
            .append(itsState).append(", Unknown event: ")
            .append(itsTransition);
        return buf.toString(); 
    }       
}
