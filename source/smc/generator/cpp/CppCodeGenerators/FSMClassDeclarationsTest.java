package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class FSMClassDeclarationsTest extends TestCase
{
    public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        FSMClassDeclarations fsmClass = new FSMClassDeclarations();

        assertEquals(buildFileComments(),fsmClass.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// TurnStyle: The Finite State Machine class\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "class TurnStyle: public TurnStyleContext\n");
        buff.append( "{\n");
        buff.append( "  public: \n");
        buff.append( "    // Static State variables\n");
        buff.append( "    static TurnStyleLockedState Locked;\n");
        buff.append( "    static TurnStyleUnlockedState Unlocked;\n\n");
        buff.append( "    TurnStyle(); // default Constructor\n\n");
        buff.append( "    // Event functions\n\n");
        buff.append( "    // State Accessor functions\n");
        buff.append( "    void SetState( TurnStyleState& theState ) { itsState = &theState; }\n");
        buff.append( "    TurnStyleState& GetState() const { return *itsState; }\n\n");
        buff.append( "    const char* GetCurrentStateName() const { return itsState->StateName(); }\n");
        buff.append( "    const char* GetVersion() const;\n\n");
        buff.append( "  private: \n");
        buff.append( "    TurnStyleState* itsState;\n");
        buff.append( "};\n");


        return buff.toString();
    }

}
