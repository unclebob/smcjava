package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class HeaderTest extends TestCase
{
    public void testHeaderFile() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        Header header = new Header();
        String actual = header.generateCode(fsm);

        assertTrue(actual.indexOf("#ifndef ")!=-1);
        assertTrue(actual.indexOf("#define ")!=-1);
        assertTrue(actual.indexOf("namespace ")!=-1);
        assertTrue(actual.indexOf("}  // end namespace ")!=-1);
        assertTrue(actual.indexOf("#endif /* ")!=-1);
    }



}
