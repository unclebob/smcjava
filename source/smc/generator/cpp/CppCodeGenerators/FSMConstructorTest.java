package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class FSMConstructorTest extends TestCase
{
    public void testFileConstructor() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        FSMConstructor constructor = new FSMConstructor();

        assertEquals(buildFileComments(),constructor.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// State Machine Constructor: TurnStyle\n");
        buff.append( "//  set Initial State to: Locked\n");
        buff.append( "//\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "TurnStyle::TurnStyle() : itsState(&Locked)\n");
        buff.append( "{\n");
        buff.append( "    // Entry functions for: Locked\n");
        buff.append( "}\n\n");
        return buff.toString();
    }
}
