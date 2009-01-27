package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;

import java.util.*;

public class FSMBaseState   extends  CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        buff.append(printSeparator(1));
        buff.append("//\n");
        buff.append("// public class State\n");
        buff.append("//    This is the base State class\n");
        buff.append("//\n");
        buff.append("public abstract class State\n");
        buff.append("{\n");
        buff.append("  public abstract string StateName();\n");
        buff.append("\n") ;
        buff.append("  // default event functions\n");
        buff.append("\n") ;

        HashSet events = gen.getStateMap().getEvents();
        Iterator evi = events.iterator();
        while( evi.hasNext() )
        {
            String evName = (String)evi.next();

            buff.append("  public virtual void " + createMethodName(evName) + "(" +gen.getStateMap().getName() + " name)");
            if(gen.usesExceptions(gen.getStateMap()) )
            {
                buff.append("\n");
                buff.append("  {\n");
                buff.append("      throw new " + gen.getStateMap().getExceptionName() + "( \"" +
                        evName + "\", name.GetCurrentState());\n" );
            }
            else
            {
                buff.append("\n");
                buff.append("  {\n");
                buff.append("    name." + gen.getStateMap().getErrorFunctionName() + "( \"" +
                        evName + "\", name.GetCurrentState());\n" );
            }

            buff.append("  }\n" );
        }
        buff.append("}\n");
        return buff.toString();
    }
}
