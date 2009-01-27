package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;
import smc.fsmrep.ConcreteState;
import smc.fsmrep.State;

import java.util.Vector;
import java.util.Iterator;
import java.util.List;

public class FSMConstructor extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // constructor\n");
        buff.append("  public " + gen.getStateMap().getName() + "()\n");
        buff.append("  {\n");

        List states = gen.getConcreteStates();
        for(int i=0;i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            String cName = createUpperCaseName( cs );
            buff.append("    its" + cName + "State = new " + cName + "();\n");
        }

        buff.append("\n");
        String iName = createUpperCaseName(gen.getStateMap().getInitialState());
        buff.append("    itsState = its" + iName + "State;\n") ;
        buff.append("\n");

        Vector initialHierarchy = new Vector();
        gen.getStateHierarchy( initialHierarchy,gen.getStateMap().getInitialState());

        Iterator i = initialHierarchy.iterator();
        while( i.hasNext() )
        {
            State newState = (State)i.next();
            buff.append( "    // Entry functions for: "
                    + newState.getName() + "\n");

            Vector eactions = newState.getEntryActions();
            Iterator eai = eactions.iterator();
            while( eai.hasNext() )
            {
                String action = (String)eai.next();
                buff.append( "    " + action + "();\n" );
            }
        }
        buff.append("  }\n");

        return buff.toString();
    }
}
