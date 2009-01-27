package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class HeaderIncludesTest extends TestCase
{
    public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        HeaderIncludes headers = new HeaderIncludes();

        assertEquals(buildFileComments(),headers.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "// Included header files\n");
        buff.append( "\n");
        buff.append( "#include \"stContext.h\"\n");
        buff.append( "\n");
        return buff.toString();
    }
}
