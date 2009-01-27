package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class StateClassDeclarationsTest extends TestCase
{
     public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        StateClassDeclarations state = new StateClassDeclarations();

        assertEquals(buildFileComments(),state.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// State: Locked\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "class TurnStyleLockedState : public TurnStyleState\n");
        buff.append( "{\n");
        buff.append( "  public: \n");
        buff.append( "    virtual const char* StateName() const\n");
        buff.append( "        { return \"Locked\"; }\n");
        buff.append( "};\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "// State: Unlocked\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "class TurnStyleUnlockedState : public TurnStyleState\n");
        buff.append( "{\n");
        buff.append( "  public: \n");
        buff.append( "    virtual const char* StateName() const\n");
        buff.append( "        { return \"Unlocked\"; }\n");
        buff.append( "};\n");
        return buff.toString();
    }
}
