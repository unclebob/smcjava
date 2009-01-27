package smc.generator.cpp;

import junit.framework.TestCase;

public class SMCppGeneratorTest extends TestCase
{
    public void testCreation() throws Exception
    {
        SMCppGenerator cpp = new SMCppGenerator();
        assertNotNull(cpp);
    }
}
