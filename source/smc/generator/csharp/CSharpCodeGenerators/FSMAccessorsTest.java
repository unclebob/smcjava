package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMAccessorsTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void testFSMConstructor() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
        FSMAccessors accessor = new FSMAccessors();
        String answer = accessor.generateCode(fsm);
        String expected = buildFSMAccessors();
        assertEquals(expected,answer);
    }
    public String buildFSMAccessors()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // accessor functions\n");
        buff.append("\n");
        buff.append("  public string GetVersion()\n");
        buff.append("  {\n");
        buff.append("    return itsVersion;\n");
        buff.append("  }\n");
        buff.append("  public string GetCurrentStateName()\n");
        buff.append("  {\n");
        buff.append("    return itsState.StateName();\n");
        buff.append("  }\n");
        buff.append("  public State GetCurrentState()\n");
        buff.append("  {\n");
        buff.append("    return itsState;\n");
        buff.append("  }\n");
        buff.append("  public State GetItsLockedState()\n");
        buff.append("  {\n");
        buff.append("    return itsLockedState;\n");
        buff.append("  }\n");
        buff.append("  public State GetItsUnlockedState()\n");
        buff.append("  {\n");
        buff.append("    return itsUnlockedState;\n");
        buff.append("  }\n\n");

        return buff.toString();
    }

}
