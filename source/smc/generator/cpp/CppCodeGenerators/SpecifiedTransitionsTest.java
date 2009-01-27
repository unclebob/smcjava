package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

public class SpecifiedTransitionsTest  extends TestCase
{
     public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        SpecifiedTransitions specifiedTransitions = new SpecifiedTransitions();

        assertEquals(buildFileComments(),specifiedTransitions.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// The States and their Transitions\n");
        buff.append( "//----------------------------------------------\n\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "// Locked Actions and Transitions\n");
        buff.append( "//----------------------------------------------\n\n");
        buff.append( "//----------------------------------------------\n");
        buff.append( "// Unlocked Actions and Transitions\n");
        buff.append( "//----------------------------------------------\n\n");
        return buff.toString();
    }
}
