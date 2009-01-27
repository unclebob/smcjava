package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

public class FSMEventsTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void setUp() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }
    public void testEvents()
    {
        FSMEvents event = new FSMEvents();
        String actual = event.generateCode(fsm);
        String expected = buildFSMEvents();
        assertEquals(expected,actual);
    }
    public String buildFSMEvents()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("  // event functions - forward to the current State\n\n");
        return buff.toString();
    }
}
