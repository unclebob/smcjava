package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.csharp.SMCSharpGenerator;

public class NamespaceStatementTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void setUp()   throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }

    public void testNamespaceStatements() throws Exception
    {
        NamespaceStatement name = new NamespaceStatement();
        String actual = name.generateCode(fsm);
        String expected = "namespace TurnStyleExample\n{\n";
        assertEquals(expected,actual);
    }
}
