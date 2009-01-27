package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;
import smc.fsmrep.State;

import java.util.Vector;
import java.util.Iterator;

public class FSMConstructor extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append(printSeparator("" ));
        buff.append("// State Machine Constructor: " + gen.getStateMap().getName()+ "\n");
        buff.append( "//  set Initial State to: " + gen.getStateMap().getInitialState().getName() + "\n");
        buff.append( "//" + "\n");
        buff.append(printSeparator( "" ));

        buff.append( gen.getStateMap().getName() + "::" + gen.getStateMap().getName() + "() ");
        buff.append( ": itsState(&"+ gen.getStateMap().getInitialState().getName() + ")" + "\n");
        buff.append( "{" + "\n");

        Vector initialHierarchy = new Vector();

        gen.getStateHierarchy( initialHierarchy, gen.getStateMap().getInitialState());

        Iterator i = initialHierarchy.iterator();
        while( i.hasNext() )
        {
            State newState = (State)i.next();
            buff.append( "    // Entry functions for: " + newState.getName() + "\n");

            Vector eactions = newState.getEntryActions();
            Iterator eai = eactions.iterator();
            while( eai.hasNext() )
            {
                String action = (String)eai.next();
                buff.append( "    " + action + "();" + "\n");
            }
        }

        buff.append( "}" + "\n");
        buff.append("\n");
        return buff.toString();
    }
}
