package smc.generator.csharp;

import junit.framework.TestCase;
public class SMCSharpGeneratorTest  extends TestCase
{
    public void testCreation()
    {
        SMCSharpGenerator gen = new SMCSharpGenerator();
        assertNotNull(gen);
    }
}
