package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class ImplStaticStatesTest extends TestCase
{
    public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        ImplStaticStates staticStates = new ImplStaticStates();

        assertEquals(buildFileComments(),staticStates.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// Definitions of static state objects\n\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "TurnStyleLockedState TurnStyle::Locked;\n");
        buff.append( "TurnStyleUnlockedState TurnStyle::Unlocked;\n\n");
        return buff.toString();
    }
}
