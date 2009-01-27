package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMEventsTest extends TestCase
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

    public void testItsStateVariables()
    {
        String answer = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = buildFSMEvents();
        assertTrue(answer.indexOf(expected)!= -1);
    }
    public String buildFSMEvents()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // event functions - forward to the current State\n");
        buff.append("\n");
        buff.append("  public void Test() throws Exception\n");
        buff.append("  {\n");
        buff.append("    itsState.test();\n");
        buff.append("  }\n");
        return buff.toString();
    }
}
