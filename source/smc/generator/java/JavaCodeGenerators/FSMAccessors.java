package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;

public class FSMAccessors extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        buff.append("\n");
        buff.append("  // accessor functions\n");
        buff.append("\n");
        buff.append("  public String getVersion()\n");
        buff.append("  {\n");
        buff.append("    return itsVersion;\n");
        buff.append("  }\n");
        buff.append("\n");
        buff.append("  public String getCurrentStateName()\n");
        buff.append("  {\n");
        buff.append("    return itsState.stateName();\n");
        buff.append("  }\n");
        buff.append("\n");

        return buff.toString();
    }
}
