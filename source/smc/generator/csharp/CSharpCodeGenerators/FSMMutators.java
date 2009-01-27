package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;

public class FSMMutators extends CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // Mutator functions\n\n");
        buff.append("  public void SetState(State value)\n");
        buff.append("  {\n");
        buff.append("    itsState = value;\n");
        buff.append("  }\n");
        return buff.toString();
    }
}
