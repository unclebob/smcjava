package smc;

import junit.framework.TestCase;

public class SmcTest extends TestCase
{
    private Smc smc;
    public void setUp()
    {
        smc = new Smc();
    }
    public void testParseCommandLine() throws Exception
    {
        String[] args = {"Smc","-f", "-ggeneratorName","-oC:\\dir", "file"};

        smc.parseCommandLine(args);
        assertTrue(smc.getForcedOverwrite());
        assertEquals("file",smc.getInputFilename());
        assertEquals("C:\\dir",smc.getOutputDir());
        assertEquals("generatorName",smc.getFSMGeneratorName());
    }
    public void testParseCommandLineWithoutForcedOverwrite() throws Exception
    {
        String[] args = {"Smc", "-ggeneratorName","-oC:\\dir", "file"};
        smc.parseCommandLine(args);
        assertFalse(smc.getForcedOverwrite());
    }
}
