package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMClassTest extends TestCase
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

    public void testFSMClass()
    {
        String actual = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = buildFSMClass();
//        assertEquals();
        assertTrue(actual.indexOf(expected)!= -1);
    }
    private String buildFSMClass()
        {
            StringBuffer buff = new StringBuffer();
            buff.append("//----------------------------------------------\n");
            buff.append("//\n");
            buff.append("// class FileName\n");
            buff.append("//    This is the Finite State Machine class\n");
            buff.append("//\n")  ;
            buff.append("public class FileName extends ContextName\n");
            buff.append("{\n");
            buff.append("  private State itsState;\n");
            buff.append("  private static String itsVersion = \"TheVersion\";\n\n");
            buff.append("  // instance variables for each state\n");
            buff.append("  private static Locked itsLockedState;\n");
            buff.append("  private static NotLocked itsNotLockedState;\n");
            buff.append("  private static SubState itsSubStateState;\n");
            buff.append("\n");
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
            buff.append("\n");
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
            buff.append("\n");
            buff.append("  // event functions - forward to the current State\n");
            buff.append("\n");
            buff.append("  public void Test() throws Exception\n");
            buff.append("  {\n");
            buff.append("    itsState.test();\n");
            buff.append("  }\n");
            buff.append("\n");

            return buff.toString();
        }

}
