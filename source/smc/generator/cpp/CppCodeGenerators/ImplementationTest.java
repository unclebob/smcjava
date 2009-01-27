package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class ImplementationTest extends TestCase
{
    public void testFileConstructor() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        Implementation impl = new Implementation();

        assertTrue(impl.generateCode(fsm).indexOf("static char _versID[]  =")!= -1);
        assertTrue(impl.generateCode(fsm).indexOf("//  Get version information")!= -1);
        assertTrue(impl.generateCode(fsm).indexOf("}  // end namespace ")!= -1);
    }

}
