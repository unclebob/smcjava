package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMClassTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void testFSMClass() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
        FSMClass fsmClass = new FSMClass();
        String actual = fsmClass.generateCode(fsm);
        String expected = buildFSMClass();
        assertTrue(actual.indexOf(expected)!=-1);
    }
    public void testFromFile() throws Exception
    {
        String actual = TestFileString.getInstance().getFileContents();
        assertTrue(actual.indexOf("  private Locked itsLockedState;\n")!=-1);
        assertTrue(actual.indexOf("  private Unlocked itsUnlockedState;\n")!=-1);
    }
    private String buildFSMClass()
        {
            StringBuffer buff = new StringBuffer();
            buff.append("//----------------------------------------------\n");
            buff.append("//\n");
            buff.append("// class TurnStyle\n");
            buff.append("//    This is the Finite State Machine class\n");
            buff.append("//\n")  ;
            buff.append("public class TurnStyle : TurnStyleContext\n");
            buff.append("{\n");
            buff.append("  private State itsState;\n");
            buff.append("  private static string itsVersion = \"\";\n\n");
            buff.append("  // instance variables for each state\n");
            buff.append("  private Locked itsLockedState;\n");
            buff.append("  private Unlocked itsUnlockedState;\n");
            buff.append("\n");
            buff.append("  // constructor\n");
            buff.append("  public TurnStyle()\n");
            buff.append("  {\n");
            buff.append("    itsLockedState = new Locked();\n");
            buff.append("    itsUnlockedState = new Unlocked();\n");
            buff.append("\n");
            buff.append("    itsState = itsLockedState;\n");
            buff.append("\n");
            buff.append("    // Entry functions for: Locked\n");
            buff.append("  }\n");
            buff.append("\n");
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
            buff.append("  }\n");
            buff.append("\n");
            buff.append("  // Mutator functions\n\n");
            buff.append("  public void SetState(State value)\n");
            buff.append("  {\n");
            buff.append("    itsState = value;\n");
            buff.append("  }\n");
            buff.append("  // event functions - forward to the current State\n");
            buff.append("\n");
            buff.append("}\n");

            return buff.toString();
        }

}
