package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;
import smc.fsmrep.*;
import java.util.*;

public class FSMStateClasses extends CSharpCodeGenerator
{
    private SMCSharpGenerator gen;

    public String generateCode(SMCSharpGenerator gen)
    {
        this.gen=gen;
        StringBuffer buff = new StringBuffer();
        List states = gen.getConcreteStates();

        for(int i=0;i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);

            buff.append(printSeparator(1));
            buff.append("//\n");
            buff.append("// class " + createMethodName(cs) + "\n");
            buff.append("//    handles the " + cs.getName() + " State and its events\n" );
            buff.append("//\n");

            buff.append("public class " + createMethodName(cs) + " : State\n" );
            buff.append("{\n");
            buff.append("  public override string StateName()\n");
            buff.append("    { return \"" + cs.getName() + "\"; }\n");

            gen.setItsSourceState(cs);
            gen.clearItsOverRiddenEvents();

            buff.append(generateTransitions(cs));

            buff.append("}\n");
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
                buff.append( "  //\n");
                buff.append( "  // responds to " + createMethodName(event) + " event\n" );
                buff.append( "  //\n");
                buff.append( "  public override void " + createMethodName(event) + "(" + gen.getStateMap().getName() + " name)\n");
                buff.append( "  {\n" );

                List actions = t.getActions();
                if( actions.size() > 0 )
                {
                    noResponse = false;
                }

                Iterator ai = actions.iterator();
                while( ai.hasNext() )
                {
                    String aName = (String)ai.next();
                    buff.append( "    name." + aName + "();\n" );
                }

                if( t instanceof ExternalTransition )
                {
                    ExternalTransition et = (ExternalTransition)t;
                    buff.append("\n");
                    noResponse = false;
                    buff.append(generateStateChange(et));
                }
                if( noResponse == true )
                    buff.append("}\n");
                else
                    buff.append( "  }\n" );
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
        buff.append( "    // change the state\n" );
        buff.append( "    name.SetState(name.GetIts" + createMethodName(et.getNextState()) + "State());\n" );

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
                buff.append( "  // Exit functions for: " + exitState.getName()  + "\n");
                Iterator eai = eactions.iterator();
                while( eai.hasNext() )
                {
                    String action = (String)eai.next();
                    buff.append( "      name." + action + "();" );
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
                buff.append( "  // Entry functions for: "+ newState.getName() + "\n" );
                Iterator eai = eactions.iterator();
                while( eai.hasNext() )
                {
                    String action = (String)eai.next();
                    buff.append( "      name." + action + "();" );
                }
            }
        }
        return buff.toString();
    }
}
