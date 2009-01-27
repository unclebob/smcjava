package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;
import smc.fsmrep.*;
import java.util.*;

public class SpecifiedTransitions extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append(printSeparator(""));
        buff.append( "// The States and their Transitions"+ "\n");
        buff.append(printSeparator( ""));
        buff.append("\n");

        List states = gen.getConcreteStates();
        for(int i = 0 ; states.size()!=i;i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            buff.append(printSeparator(""));
            buff.append("// " + cs.getName() +" Actions and Transitions"+ "\n" );
            buff.append(printSeparator( ""));
            buff.append("\n");

            gen.setSourceState(cs);
            gen.clearOverRiddenEvents();
            buff.append(generateTransitions(cs, gen));

        }
//        Vector states = gen.getConcreteStates();
//        Iterator si = states.iterator();
//        while( si.hasNext() )
//        {
//            ConcreteState cs = (ConcreteState)si.next();
//            buff.append(printSeparator(""));
//            buff.append("// " + cs.getName() +" Actions and Transitions"+ "\n" );
//            buff.append(printSeparator( ""));
//            buff.append("\n");
//
//            gen.setSourceState(cs);
//            gen.clearOverRiddenEvents();
//            buff.append(generateTransitions(cs, gen));
//        }
        return buff.toString();
    }
    private String generateTransitions(State s, SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        HashSet transitions = s.getTransitions();
        Iterator ti = transitions.iterator();

        while( ti.hasNext() )
        {
            Transition t = (Transition)ti.next();
            String event = t.getEvent();

            // Generate the transition if it has not been overridden
            // by a substate.
            if(gen.getOverriddenEvents().contains( event ) == false )
            {
                gen.addOverriddenEvent(event); // remember the override

                buff.append( "// Starting State: " + gen.getSourceState().getName() + "\n");
                buff.append( "// Event:          " + event+ "\n");
                buff.append( "//" + "\n");
                buff.append( "void " + gen.makeStateName(gen.getSourceState().getName())
                        + "::" + event + "( "  + gen.getStateMap().getName() + "& s )"+ "\n");

                buff.append( "{"+ "\n" );

                // Write out all the transition actions.
                List actions = t.getActions();
                if( actions.size() > 0 )
                    buff.append("\n");

                 Iterator ai = actions.iterator();
                while( ai.hasNext() )
                {
                    String aName = (String)ai.next();
                    buff.append( "    s." + aName + "();" + "\n");
                }

                // Change State if the transition is external
                if( t instanceof ExternalTransition )
                {
                    ExternalTransition et = (ExternalTransition)t;
                    buff.append("\n");
                    buff.append(generateStateChange(et,gen));
                }
                buff.append( "}" + "\n");
                buff.append("\n");
            }
        }

        // Now generate any unoverridden transition for the superclass.
        if( s instanceof SubState )
        {
            SubState ss = (SubState)s;
            buff.append(generateTransitions(ss.getSuperState(),gen));
        }
        return buff.toString();
    }


    private String generateStateChange(ExternalTransition et,SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        buff.append( "    // Change the state" + "\n");
        buff.append( "    s.SetState(" + gen.getStateMap().getName()  + "::" + et.getNextState().getName() + ");" + "\n");

        Vector oldHierarchy = new Vector();
        Vector newHierarchy = new Vector();

        gen.getUnsharedHierarchy( oldHierarchy, newHierarchy, gen.getSourceState(), et.getNextState() );

        // Generate the Exit actions
        int n = oldHierarchy.size();
        for (n--; n >= 0; n--)
        {
            State exitState = (State)(oldHierarchy.elementAt(n));
            Vector eactions = exitState.getExitActions();
            if( eactions.isEmpty() == false )
            {
                buff.append("\n");
                buff.append( "    // Exit functions for: " + exitState.getName() + "\n");
                Iterator eai = eactions.iterator();
                while( eai.hasNext() )
                {
                    String action = (String)eai.next();
                    buff.append( "    s." + action + "();" + "\n");
                }
            }
        }

        // Generate Entry Actions
        Iterator nsi = newHierarchy.iterator();
        while( nsi.hasNext() )
        {
            State newState = (State)nsi.next();
            Vector eactions = newState.getEntryActions();
            if( eactions.isEmpty() == false )
            {
                buff.append("\n");
                buff.append( "    // Entry functions for: "+ newState.getName() + "\n");
                Iterator eai = eactions.iterator();
                while( eai.hasNext() )
                {
                    String action = (String)eai.next();
                    buff.append( "    s." + action + "();" + "\n" );
                }
            }
        }
        return buff.toString();
    }
}
