package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class BaseStateDeclarationsTest extends TestCase
{
     public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        BaseStateDeclarations base = new BaseStateDeclarations();

        assertEquals(buildFileComments(),base.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// TurnStyleState: The base state class\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "class TurnStyleState\n");
        buff.append( "{\n");
        buff.append( "  public: \n");
        buff.append( "    virtual const char* StateName() const = 0;\n");
        buff.append( "};\n\n");
        return buff.toString();
    }
}
