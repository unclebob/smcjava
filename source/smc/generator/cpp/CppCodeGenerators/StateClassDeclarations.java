package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;
import smc.fsmrep.*;
import java.util.*;

public class StateClassDeclarations extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        List states = gen.getConcreteStates();

        for(int i=0;i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);

            String stateName = "State: " + cs.getName();
            buff.append(printSeparator( stateName ));

            buff.append("class " + gen.makeStateName( cs.getName() ) + " : public " + gen.makeStateName("") + "\n");
            buff.append("{"+ "\n");
            buff.append("  public: " + "\n");
            buff.append("    virtual const char* StateName() " + "const"+ "\n");
            buff.append("        { return \"" + cs.getName() +  "\"; }"+ "\n");

            gen.clearOverRiddenEvents();
            buff.append(generateTransitionDeclarations(cs,gen));
            buff.append("};"+ "\n");
        }
        return buff.toString();
    }

    private String generateTransitionDeclarations(State s, SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        HashSet transitions = s.getTransitions();
        Iterator ti = transitions.iterator();
        while( ti.hasNext() )
        {
            Transition t = (Transition)(ti.next());
            if( gen.getOverriddenEvents().contains( t.getEvent() ) == false )
            {
                gen.getOverriddenEvents().add( t.getEvent());
                buff.append("    virtual void " + t.getEvent() + "( " + gen.getStateMap().getName() + "& );" + "\n" );
            }
        }

        if( s instanceof SubState )
        {
            SubState ss = (SubState)s;
            buff.append(generateTransitionDeclarations( ss.getSuperState(),gen ));
        }
        return buff.toString();
    }
}
