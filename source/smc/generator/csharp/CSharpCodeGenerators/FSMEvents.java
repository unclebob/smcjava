package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class FSMEvents  extends CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        buff.append("  // event functions - forward to the current State\n");
        buff.append("\n");

        HashSet events = gen.getStateMap().getEvents();
        Iterator evi = events.iterator();

        while( evi.hasNext() )
        {
            String evName = (String)evi.next();
            buff.append("  public void " + createMethodName(evName) + "()" );
            buff.append("\n");
            buff.append("  {\n");
            buff.append("    itsState." + createMethodName(evName)   + "(this);\n");
            buff.append("  }\n" );
            buff.append("\n");
        }

        return buff.toString();
    }
}
