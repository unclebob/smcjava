package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class ItsStateVariablesTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void setUp() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }

    public void testItsStateVariables()
    {
        ItsStateVariables state = new ItsStateVariables();
        String actual = state.generateCode(fsm);
        String expected = buildItsStateVariables();
        assertEquals(expected,actual);
    }

    public void testFromFile()     throws Exception
    {
        String actual = TestFileString.getInstance().getFileContents();
        assertTrue(actual.indexOf("  private Locked itsLockedState;\n")!=-1);
        assertTrue(actual.indexOf("  private Unlocked itsUnlockedState;\n")!=-1);

    }
    public String buildItsStateVariables()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  private Locked itsLockedState;\n");
        buff.append("  private Unlocked itsUnlockedState;\n");
        buff.append("\n");
        return buff.toString();
    }
}
