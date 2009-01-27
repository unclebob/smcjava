package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;

public class HeaderForwardDeclarations extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("// Forward Declarations" + "\n");
        buff.append("\n");
        buff.append("class " + gen.getStateMap().getName() + ";" + "\n");
        buff.append("\n");
        return buff.toString();
    }
}
