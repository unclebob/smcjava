package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;

import java.util.*;

public class DefaultEvents extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        boolean useExceptions;

        if( gen.getStateMap().getExceptionName().length() > 0 )
            useExceptions = true;
        else
            useExceptions = false;

        buff.append(printSeparator( "Default Event Functions" + "\n"));
        buff.append("\n");

        HashSet events = gen.getStateMap().getEvents();
        Iterator evi = events.iterator();
        while( evi.hasNext() )
        {
            String evName = (String)evi.next();

            buff.append( "void " + gen.makeStateName("") + "::" + evName + '(' +
                    gen.getStateMap().getName() + "& s)"+ "\n" );

            if( useExceptions )
            {
                buff.append( "  { throw " +  gen.getStateMap().getExceptionName() +
                        "(\"" + evName + "\", s.GetState().StateName()); }" + "\n");
            }
            else
            {
                buff.append( "  { s." + gen.getStateMap().getErrorFunctionName() +
                        "(\"" + evName + "\", s.GetState().StateName()); }"+ "\n" );
            }
            buff.append("\n");
        }
        return buff.toString();
    }
}
