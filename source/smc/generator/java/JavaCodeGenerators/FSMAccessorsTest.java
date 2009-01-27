package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMAccessorsTest extends TestCase
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

    public void testFSMConstructor()
    {
        String actual = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = buildFSMAccessors();
        assertTrue(actual.indexOf(expected)!= -1);
    }
    public String buildFSMAccessors()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // accessor functions\n");
        buff.append("\n");
        buff.append("  public String getVersion()\n");
        buff.append("  {\n");
        buff.append("    return itsVersion;\n");
        buff.append("  }\n");
        buff.append("\n");
        buff.append("  public String getCurrentStateName()\n");
        buff.append("  {\n");
        buff.append("    return itsState.stateName();\n");
        buff.append("  }\n");
        return buff.toString();
    }

}
