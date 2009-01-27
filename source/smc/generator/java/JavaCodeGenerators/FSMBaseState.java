package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class FSMBaseState extends  JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        buff.append(printSeparator(1));
        buff.append(initialDeclarations());

        HashSet events = gen.getStateMap().getEvents();
        Iterator evi = events.iterator();

        while( evi.hasNext() )
            addEvent(evi, buff, gen);

        buff.append("  }\n");
        return buff.toString();
    }

    private String initialDeclarations()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("//\n");
        buff.append("// private class State\n");
        buff.append("//    This is the base State class\n");
        buff.append("//\n");
        buff.append("  private abstract class State\n");
        buff.append("  {\n");
        buff.append("    public abstract String stateName();\n");
        buff.append("\n") ;
        buff.append("    // default event functions\n");
        buff.append("\n") ;
        return buff.toString();
    }

    private void addEvent(Iterator evi, StringBuffer buff, SMJavaGenerator gen)
    {
        String evName = (String)evi.next();

        buff.append("    public void " + evName + "()");

        if(gen.usesExceptions(gen.getStateMap()) )
            buff.append(addEventWithExceptions(gen, evName));
        else
            buff.append(addEventWithErrors( gen, evName));

        buff.append("    }\n" );
        buff.append("\n");
    }

    private String addEventWithErrors(SMJavaGenerator gen, String evName)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("\n\n");
        buff.append("    {\n");
        buff.append("      " + gen.getStateMap().getErrorFunctionName() + "( \"" +
                evName + "\", itsState.stateName());\n" );
        return buff.toString();
    }

    private String addEventWithExceptions( SMJavaGenerator gen, String evName)
    {
        StringBuffer buff = new StringBuffer();
        buff.append(" throws " + gen.getStateMap().getExceptionName() + "\n");
        buff.append("    {\n");
        buff.append("      throw new " + gen.getStateMap().getExceptionName() + "( \"" +
                evName + "\", itsState.stateName());\n" );
        return buff.toString();
    }
}
