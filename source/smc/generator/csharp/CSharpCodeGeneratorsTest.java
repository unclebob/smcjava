package smc.generator.csharp;

import junit.framework.TestCase;

public class CSharpCodeGeneratorsTest extends TestCase
{
    public void testBuildCSharpGenerators()
    {
        assertEquals(4,CSharpCodeGeneratorBuilder.cSharpCode.cSharpInstances().size());
    }
    public void testBuildFSMClassGenerators()
    {
        assertEquals(5,CSharpCodeGeneratorBuilder.cSharpCode.cSharpFSMInstances().size());
    }
    public void testBuildFSMClassesGenerators()
    {
        assertEquals(2,CSharpCodeGeneratorBuilder.cSharpCode.cSharpFSMClassesInstances().size());
    }
}
