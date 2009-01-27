package smc.builder.stateRep;

import junit.framework.TestCase;
import smc.builder.FSMRepresentationBuilder;

public class SubStateRepTest extends TestCase
{
    private StateRep stateRep ;
    private FSMRepresentationBuilder builder ;
    public void setUp()
    {
        stateRep = new SubStateRep("SubStateName","SuperStateName",null);
        builder = new FSMRepresentationBuilder();
    }
    public void testStateRepName() throws Exception
    {
        assertEquals("SubStateName",stateRep.getStateName());
    }
    public void testEquals() throws Exception
    {
        StateRep rep = new SubStateRep("SubStateName","SuperStateName",null);
        assertTrue(rep.equals(stateRep));
    }
    public void testNotEquals() throws Exception
    {
        StateRep rep = new SubStateRep("SubStateName","SuperStateNameIsDifferent",null);
        assertFalse(rep.equals(stateRep));
    }
    public void testErrors() throws Exception
    {
        assertNull(stateRep.build(builder));
    }
    public void testSuperStateNullErrors() throws Exception
    {
        StateRep rep = new SubStateRep("SubState",null,null);
        assertNull(rep.build(builder));
    }


}
