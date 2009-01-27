package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.generator.java.SMJavaGenerator;
import smc.generator.FSMGenerator;
import smc.fsmrep.StateMap;
import smc.builder.FSMRepresentationBuilder;

public class ImportStatementsTest extends TestCase
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
    public void testSingleUsingStatement()
    {
        String answer = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = "import aClass;\n";
        assertTrue(answer.indexOf(expected)!= -1);
    }
    public void testManyUsingStatements()
    {
        String answer = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = "import aClass;\nimport bClass;\n\n";
        assertTrue(answer.indexOf(expected)!= -1);
    }
}
