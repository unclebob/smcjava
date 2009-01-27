package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import junit.framework.TestCase;

public class FSMBaseStateTest  extends TestCase
{
    private SMCSharpGenerator fsm;

    public void testFSMBaseState()  throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
        FSMBaseState base = new FSMBaseState();
        String answer = base.generateCode(fsm);
        String expected = buildFSMBaseState();
        assertEquals(expected + "}\n",answer);
    }

    public String buildFSMBaseState()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("//--------------------------------------------\n");
        buff.append("//\n");
        buff.append("// public class State\n") ;
        buff.append("//    This is the base State class\n");
        buff.append("//\n");
        buff.append("public abstract class State\n");
        buff.append("{\n");
        buff.append("  public abstract string StateName();\n");
        buff.append("\n");
        buff.append("  // default event functions\n");
        buff.append("\n");

        return buff.toString();
    }

}
