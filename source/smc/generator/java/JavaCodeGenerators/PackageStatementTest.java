package smc.generator.java.JavaCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.generator.FSMGenerator;
import smc.generator.java.SMJavaGenerator;
import smc.fsmrep.StateMap;

public class PackageStatementTest extends TestCase
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
    public void testPackageStatement()
    {
        String answer = ((SMJavaGenerator)fsm).generateStringForCode();
        String expected = "package testPackage;";
        assertTrue(answer.indexOf(expected)!= -1);
    }
}
