package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class FSMEvents  extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        buff.append("  // event functions - forward to the current State\n");
        buff.append("\n");

        HashSet events = gen.getStateMap().getEvents();
        Iterator evi = events.iterator();
        while( evi.hasNext() )
        {
            String evName = (String)evi.next();
            buff.append("  public void " + createUpperCaseName(evName) + "()" );
            if( gen.usesExceptions(gen.getStateMap()) )
                buff.append(" throws " + gen.getStateMap().getExceptionName() );
            buff.append("\n");
            buff.append("  {\n");
            buff.append("    itsState." + evName   + "();\n");
            buff.append("  }\n" );
            buff.append("\n");
        }
        return buff.toString();
    }
}
