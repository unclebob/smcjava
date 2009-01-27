package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;

public class PackageStatement extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        if( gen.getPackage().length() > 0 )
            buff.append("package " + gen.getPackage() + ";\n\n" );
        return buff.toString();
    }
}
