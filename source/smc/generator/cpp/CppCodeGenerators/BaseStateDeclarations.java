package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class BaseStateDeclarations extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        String className = gen.makeStateName("") + ": The base state class";

        buff.append(printSeparator(className));

        buff.append( "class " + gen.makeStateName("") + "\n");
        buff.append( "{"+ "\n");
        buff.append( "  public: "+ "\n");
        buff.append("    virtual const char* StateName() const = 0;"+ "\n");

        HashSet events = gen.getStateMap().getEvents();
        Iterator ie = events.iterator();
        while( ie.hasNext() )
            buff.append("    virtual void "+(String)ie.next()+"( "+ gen.getStateMap().getName()+"& );\n" );

        buff.append("};" + "\n");
        buff.append("\n");
        return buff.toString();
    }
}
