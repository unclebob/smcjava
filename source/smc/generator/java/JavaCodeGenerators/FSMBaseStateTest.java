package smc.generator.java.JavaCodeGenerators;

import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import junit.framework.TestCase;

public class FSMBaseStateTest  extends TestCase
{
    private FSMGenerator fsm;

    public void setUp() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestJavaCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMJavaGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }

    public void testFSMBaseState()
    {
        String answer = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = buildFSMBaseState();
        assertTrue(answer.indexOf(expected)!= -1);
    }
    public String buildFSMBaseState()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("//--------------------------------------------\n");
        buff.append("//\n");
        buff.append("// private class State\n") ;
        buff.append("//    This is the base State class\n");
        buff.append("//\n");
        buff.append("  private abstract class State\n");
        buff.append("  {\n");
        buff.append("    public abstract String stateName();\n");
        buff.append("\n");
        buff.append("    // default event functions\n");
        buff.append("\n");
        buff.append("    public void test() throws Exception\n");
        buff.append("    {\n");
        buff.append("      throw new Exception( \"test\", itsState.stateName());\n");
        buff.append("    }\n");
        buff.append("\n");
        buff.append("  }\n");

        return buff.toString();
    }
}
