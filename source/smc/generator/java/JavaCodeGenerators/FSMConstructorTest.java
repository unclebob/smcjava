package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMConstructorTest extends TestCase
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
        String expected = buildFSMConstructor();
        assertTrue(actual.indexOf(expected)!= -1);
    }
    public String buildFSMConstructor()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // constructor\n");
        buff.append("  public FileName()\n");
        buff.append("  {\n");
        buff.append("    itsLockedState = new Locked();\n");
        buff.append("    itsNotLockedState = new NotLocked();\n");
        buff.append("    itsSubStateState = new SubState();\n") ;
        buff.append("\n");
        buff.append("    itsState = itsLockedState;\n");
        buff.append("\n");
        buff.append("    // Entry functions for: Locked\n");
        buff.append("    possiblyLocked();\n");
        buff.append("  }\n");
        return buff.toString();
    }

}
