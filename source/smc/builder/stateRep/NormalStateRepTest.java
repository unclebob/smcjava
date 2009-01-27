package smc.builder.stateRep;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.State;

public class NormalStateRepTest extends TestCase
{
    private StateRep stateRep ;
    public void setUp()
    {
        stateRep = new NormalStateRep("StateName",null);
    }
    public void testNormalStateName() throws Exception
    {
        State state = stateRep.build(new FSMRepresentationBuilder());
        assertEquals("StateName",state.getName());
    }
    public void testNormalStateEntryActions() throws Exception
    {
        stateRep.addEntryAction("EntryAction");
        stateRep.build(new FSMRepresentationBuilder());
        assertEquals(1,stateRep.getEntryActions().size());
    }
    public void testToString() throws Exception
    {
        assertEquals("StateName",stateRep.toString());
    }
    public void testEquals()  throws Exception
    {
        StateRep tempStateRep = new NormalStateRep("StateName",null);
        assertTrue(tempStateRep.equals(stateRep));

    }
}
