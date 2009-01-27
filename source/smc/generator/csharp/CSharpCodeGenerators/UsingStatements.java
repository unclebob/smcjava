package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;

import java.util.Iterator;

public class UsingStatements extends CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        if(gen.hasUsing())
        {
            Iterator iu = gen.getItsUsing().iterator();

            while( iu.hasNext())
                buff.append("using " + (String)iu.next() + ";\n");

            return buff.toString();
        }
        return "";
    }
}
