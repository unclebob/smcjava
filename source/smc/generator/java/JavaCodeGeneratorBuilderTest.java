package smc.generator.java;

import junit.framework.TestCase;

public class JavaCodeGeneratorBuilderTest extends TestCase
{
    public void testBuildCSharpGenerators()
    {
        assertEquals(4,JavaCodeGeneratorBuilder.javaCode.javaInstances().size());
    }
    public void testBuildFSMClassGenerators()
    {
        assertEquals(6,JavaCodeGeneratorBuilder.javaCode.javaFSMInstances().size());
    }
}
