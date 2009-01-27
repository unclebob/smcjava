package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;
import smc.fsmrep.ConcreteState;
import java.util.List;

public class ItsStateVariables extends CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        List states = gen.getConcreteStates();
        for(int i=0 ; i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            String cName = createMethodName( cs );
            buff.append("  private " + cName + " its" + cName + "State;\n");
        }
        buff.append("\n");

        return buff.toString();
    }
}
