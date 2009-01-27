package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;

public class FSMStateClassesTest extends TestCase
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

    public void testFSMStateClasses()
    {
        String actual = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = buildFSMStateClasses();
        assertTrue(actual.indexOf(expected)!= -1);
    }

    private String buildFSMStateClasses()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  //--------------------------------------------\n");
        buff.append("  //\n");
        buff.append("  // class Locked\n");
        buff.append("  //    handles the Locked State and its events\n");
        buff.append("  //\n");
        buff.append("  private class Locked extends State\n");
        buff.append("  {\n");
        buff.append("    public String stateName()\n");
        buff.append("      { return \"Locked\"; }\n");
        buff.append("  }\n\n");
        buff.append("  //--------------------------------------------\n");
        buff.append("  //\n");
        buff.append("  // class NotLocked\n");
        buff.append("  //    handles the NotLocked State and its events\n");
        buff.append("  //\n");
        buff.append("  private class NotLocked extends State\n");
        buff.append("  {\n");
        buff.append("    public String stateName()\n");
        buff.append("      { return \"NotLocked\"; }\n");
        buff.append("  }\n\n");
        buff.append("  //--------------------------------------------\n");
        buff.append("  //\n");
        buff.append("  // class SubState\n");
        buff.append("  //    handles the SubState State and its events\n");
        buff.append("  //\n");
        buff.append("  private class SubState extends State\n");
        buff.append("  {\n");
        buff.append("    public String stateName()\n");
        buff.append("      { return \"SubState\"; }\n");
        buff.append("\n");
        buff.append("    //\n");
        buff.append("    // responds to test event\n");
        buff.append("    //\n");
        buff.append("    public void test()\n");
        buff.append("    {\n\n");
        buff.append("      // change the state\n");
        buff.append("      itsState = itsNotLockedState;\n");
        buff.append("    }\n");

        buff.append("  }\n\n");

        return buff.toString();
    }
}
