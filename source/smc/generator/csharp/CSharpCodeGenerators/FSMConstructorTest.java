package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMConstructorTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void testFSMConstructor()    throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
        FSMConstructor constructor = new FSMConstructor();
        String actual = constructor.generateCode(fsm);
        String expected = buildFSMConstructor();
        assertEquals(expected,actual);
    }
    public void testFromFile() throws Exception
    {
        String actual = TestFileString.getInstance().getFileContents();
        assertTrue(actual.indexOf("    itsLockedState = new Locked();\n")!=-1);
        assertTrue(actual.indexOf("    itsUnlockedState = new Unlocked();\n")!=-1);
    }
    public String buildFSMConstructor()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // constructor\n");
        buff.append("  public TurnStyle()\n");
        buff.append("  {\n");
        buff.append("    itsLockedState = new Locked();\n");
        buff.append("    itsUnlockedState = new Unlocked();\n");
        buff.append("\n");
        buff.append("    itsState = itsLockedState;\n");
        buff.append("\n");
        buff.append("    // Entry functions for: Locked\n");
        buff.append("  }\n\n");
        return buff.toString();
    }

}
