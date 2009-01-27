package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;

import java.util.Iterator;

public class HeaderIncludes extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "// Included header files" + "\n");
        buff.append("\n");

        Iterator i = gen.getHeaders().iterator();
        while( i.hasNext() )
            buff.append( "#include \"" + i.next() + "\"" + "\n");

        buff.append("\n");
        return buff.toString();
    }
}
