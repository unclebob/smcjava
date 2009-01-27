package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class ItsStateVariablesTest extends TestCase
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
        String expected = buildItsStateVariables();
        assertTrue(answer.indexOf(expected)!= -1);
    }
    public String buildItsStateVariables()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  private static Locked itsLockedState;\n");
        buff.append("  private static NotLocked itsNotLockedState;\n");
        buff.append("  private static SubState itsSubStateState;\n");
        return buff.toString();
    }
}
