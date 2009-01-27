package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;

public class Implementation extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();

        String name = gen.getFilePrefix() + itsImplSuffix;
        buff.append(generateFileComment(name,gen.getStateMap()));

        buff.append( "static char _versID[]  = \"" + gen.getStateMap().getVersion() + "\";"+ "\n");
        buff.append("\n");

        buff.append( "#include \"" + gen.getFilePrefix() +  itsHdrSuffix + "\""+ "\n");
        buff.append("\n");

        if( gen.getNamespace().length() > 0 )
        {
            buff.append("namespace " + gen.getNamespace() + "\n");
            buff.append("{"+ "\n");
            buff.append("\n");
        }

        buff.append(new ImplStaticStates().generateCode(gen));
        buff.append(new DefaultEvents().generateCode(gen));
        buff.append(new SpecifiedTransitions().generateCode(gen));
        buff.append(new FSMConstructor().generateCode(gen));

        buff.append("//  Get version information"+ "\n");
        buff.append("//"+ "\n");
        buff.append("const char* " + gen.getStateMap().getName() + "::GetVersion() const"+ "\n");
        buff.append("{ return _versID; }"+ "\n");
        buff.append("\n");

        if( gen.getNamespace().length() > 0 )
        {
            buff.append("}  // end namespace " + gen.getNamespace() + "\n");
            buff.append("\n");
        }
        return buff.toString();
    }
}
