package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class UsingStatementsTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void setUp() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuildStateWithUsing();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }
    public void testManyUsingStatements()
    {
        UsingStatements use = new UsingStatements();
        String actual = use.generateCode(fsm);
        String expected = "using aClass;\nusing bClass;\n";
        assertEquals(expected,actual);
    }
}
