package smc.generator;

import junit.framework.TestCase;
import smc.generator.cpp.SMCppGenerator;
import smc.generator.csharp.SMCSharpGenerator;
import smc.fsmrep.*;
import smc.builder.FSMRepresentationBuilder;

import java.util.Vector;

public class FSMGeneratorTest extends TestCase
{
    public void testConcreteStates() throws Exception
    {
        FSMRepresentationBuilder fsmbld = initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        FSMGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        assertEquals(2,fsm.getConcreteStates().size());
    }
    public void testParamaters() throws Exception
    {
        FSMGenerator fsm = new SMCSharpGenerator();
        fsm.FSMInit(new MutableStateMap(),"TurnStyle","directory");
        fsm.initialize();
        assertEquals("directory",fsm.getDirectory());
        assertEquals("TurnStyle",fsm.getInputFileName());
        assertEquals("TurnStyle",fsm.getFilePrefix());
    }
    private FSMRepresentationBuilder initBuilderState()
    {
        FSMRepresentationBuilder builder = new FSMRepresentationBuilder();

        builder.addBuiltConcreteState(new ConcreteStateImpl("Locked"));
        builder.addBuiltConcreteState(new ConcreteStateImpl("Unlocked"));

        builder.build();
        return builder;
    }


}
