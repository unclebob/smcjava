package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;
import smc.fsmrep.ConcreteState;
import java.util.List;

public class ItsStateVariables extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        List states = gen.getConcreteStates();
        for(int i=0;i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            String cName = createUpperCaseName( cs );
            buff.append("  private static " + cName + " its" + cName + "State;\n");   
        }
        buff.append("\n");

        return buff.toString();
    }
}
