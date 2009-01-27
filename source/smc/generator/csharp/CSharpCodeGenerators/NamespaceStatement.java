package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;

public class NamespaceStatement extends CSharpCodeGenerator
{
    public String generateCode(SMCSharpGenerator gen)
    {
        if(gen.hasNamespace())
            return "namespace " + gen.getNamespace() + "\n{\n";
        return "";
    }
}
