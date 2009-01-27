package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;
import smc.fsmrep.*;

import java.util.Vector;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;

public class FSMStateClasses extends JavaCodeGenerator
{
    private SMJavaGenerator gen;
    public String generateCode(SMJavaGenerator gen)
    {
        this.gen=gen;

        StringBuffer buff = new StringBuffer();

        List states = gen.getConcreteStates();
        for(int i = 0; i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            buff.append("  " + printSeparator(1));
            buff.append("  //\n");
            buff.append("  // class " + createUpperCaseName(cs) + "\n");
            buff.append("  //    handles the " + cs.getName() +
                    " State and its events\n" );
            buff.append("  //\n");

            buff.append("  private class " + createUpperCaseName(cs) +
                    " extends State\n" );
            buff.append("  {\n");
            buff.append("    public String stateName()\n");
            buff.append("      { return \"" + cs.getName() + "\"; }\n");

            gen.setItsSourceState(cs);
            gen.clearItsOverRiddenEvents();

            buff.append(generateTransitions(cs));

            buff.append("  }\n");
            buff.append("\n");
        }
        return buff.toString();

    }
    private String generateTransitions(State s)
    {
        StringBuffer buff = new StringBuffer();
        HashSet transitions = s.getTransitions();
        Iterator ti = transitions.iterator();

        while( ti.hasNext() )
        {
            Transition t = (Transition)ti.next();
            String event = t.getEvent();


            if( gen.getItsOverriddenEvents().contains( event ) == false )
            {
                gen.getItsOverriddenEvents().add( event );
                boolean noResponse = true;

                buff.append("\n");
                buff.append( "    //\n");
                buff.append( "    // responds to " + event + " event\n" );
                buff.append( "    //\n");
                buff.append( "    public void " + event + "()\n");
                buff.append( "    {\n" );

                // Write out all the transition actions.
                List actions = t.getActions();
                if( actions.size() > 0 )
                {
                    buff.append("\n");
                    noResponse = false;
                }

                Iterator ai = actions.iterator();
                while( ai.hasNext() )
                {
                    String aName = (String)ai.next();
                    buff.append( "      " + aName + "();\n" );
                }

                if( t instanceof ExternalTransition )
                {
                    ExternalTransition et = (ExternalTransition)t;
                    buff.append("\n");
                    noResponse = false;
                    buff.append(generateStateChange(et));
                }
                    buff.append( "    }\n" );
            }
        }

        if( s instanceof SubState )
        {
            SubState ss = (SubState)s;
            buff.append(generateTransitions(ss.getSuperState()));
        }
        return buff.toString();
    }
     private String generateStateChange(ExternalTransition et)
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "      // change the state\n" );
        buff.append( "      itsState = " + "its" + createUpperCaseName(et.getNextState()) + "State;\n" );

        Vector oldHierarchy = new Vector();
        Vector newHierarchy = new Vector();

        gen.getUnsharedHierarchy( oldHierarchy, newHierarchy, gen.getItsSourceState(), et.getNextState() );

        int n = oldHierarchy.size();
        for (n--; n >= 0; n--)
        {
            State exitState = (State)(oldHierarchy.elementAt(n));
            Vector eactions = exitState.getExitActions();
            if( eactions.isEmpty() == false )
            {
                buff.append("\n");
                buff.append( "      // Exit functions for: " + exitState.getName() + "\n");
                Iterator eai = eactions.iterator();
                while( eai.hasNext() )
                {
                    String action = (String)eai.next();
                    buff.append( "      " + action + "();" );
                }
            }
        }
        
        Iterator nsi = newHierarchy.iterator();
        while( nsi.hasNext() )
        {
            State newState = (State)nsi.next();
            Vector eactions = newState.getEntryActions();
            if( eactions.isEmpty() == false )
            {
                buff.append("\n");
                buff.append( "      // Entry functions for: "
                        + newState.getName() + "\n");
                Iterator eai = eactions.iterator();
                while( eai.hasNext() )
                {
                    String action = (String)eai.next();
                    buff.append( "      " + action + "();\n" );
                }
            }
        }
        return buff.toString();
    }
}
