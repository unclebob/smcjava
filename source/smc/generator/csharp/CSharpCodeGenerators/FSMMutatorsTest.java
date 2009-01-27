package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMMutatorsTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void testFSMConstructor() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
        FSMMutators mutator = new FSMMutators();
        String answer = mutator.generateCode(fsm);
        String expected = buildFSMMutator();
        assertEquals(expected,answer);
    }
    public String buildFSMMutator()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // Mutator functions\n");
        buff.append("\n");
        buff.append("  public void SetState(State value)\n");
        buff.append("  {\n");
        buff.append("    itsState = value;\n");
        buff.append("  }\n");
        return buff.toString();
    }

}
