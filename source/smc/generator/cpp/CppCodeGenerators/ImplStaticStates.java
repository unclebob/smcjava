package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;
import smc.fsmrep.ConcreteState;
import java.util.List;

public class ImplStaticStates extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append(printSeparator("Definitions of static state objects" + "\n"));

        List states = gen.getConcreteStates();
        for(int i  = 0;states.size()!=i ; i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            buff.append( gen.makeStateName(cs.getName()) + " " + gen.getStateMap().getName() + "::" + cs.getName() + ";"+ "\n");
        }
        buff.append("\n");
        return buff.toString();
    }
}
