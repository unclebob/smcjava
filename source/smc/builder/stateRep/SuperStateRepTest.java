package smc.builder.stateRep;

import junit.framework.TestCase;

public class SuperStateRepTest extends TestCase
{
    private StateRep superState;
    public void setUp()
    {
        superState = new SuperStateRep("SuperState",null);
    }
    public void testToString() throws Exception
    {
        assertEquals("(SuperState)",superState.toString());
    }
    public void testName() throws Exception
    {
        assertEquals("SuperState",superState.getStateName());
    }
    public void testEquals() throws Exception
    {
        assertTrue(superState.equals(new SuperStateRep("SuperState",null)));
    }
    public void testNotEquals() throws Exception
    {
        assertFalse(superState.equals(new SuperStateRep("notSameSuperState",null)));
    }
}
