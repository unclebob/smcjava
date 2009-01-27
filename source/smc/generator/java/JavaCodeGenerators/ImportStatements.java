package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;

import java.util.Iterator;

public class ImportStatements extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        Iterator ii = gen.getImports().iterator();
        if( ii.hasNext() )
        {
            buff.append("// imports\n" );
            while( ii.hasNext() )
            {
                String name = (String)(ii.next());
                buff.append("import " + name + ";\n");
            }
            buff.append("\n");
        }
        return buff.toString();
    }
}
