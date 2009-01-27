package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class HeaderForwardDeclarationsTest extends TestCase
{
    public void testHeaderForwardDeclarations() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        HeaderForwardDeclarations headDec = new HeaderForwardDeclarations();

        assertEquals(buildFileComments(),headDec.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "// Forward Declarations\n\n");
        buff.append( "class TurnStyle;\n\n");
        return buff.toString();
    }
}
