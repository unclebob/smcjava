package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;
import smc.fsmrep.ConcreteState;
import java.util.List;

public class FSMAccessors extends CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        buff.append("  // accessor functions\n");
        buff.append("\n");
        buff.append("  public string GetVersion()\n");
        buff.append("  {\n");
        buff.append("    return itsVersion;\n");
        buff.append("  }\n");
        buff.append("  public string GetCurrentStateName()\n");
        buff.append("  {\n");
        buff.append("    return itsState.StateName();\n");
        buff.append("  }\n");
        buff.append("  public State GetCurrentState()\n");
        buff.append("  {\n");
        buff.append("    return itsState;\n");
        buff.append("  }\n");

        List states = gen.getConcreteStates();
        for(int i = 0; i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
            String cName = createMethodName( cs );
            buff.append("  public State GetIts" + cName + "State()\n");
            buff.append("  {\n");
            buff.append("    return its" + cName + "State;\n");
            buff.append("  }\n");
        }
        buff.append("\n");

        return buff.toString();
    }
}
