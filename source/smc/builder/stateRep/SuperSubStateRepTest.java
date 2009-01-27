package smc.builder.stateRep;

import junit.framework.TestCase;

public class SuperSubStateRepTest extends TestCase
{
    private StateRep superSubState;
    public void setUp()
    {
        superSubState = new SuperSubStateRep("SuperSubState","SuperState",null);
    }
    public void testToString() throws Exception
    {
        assertEquals("(SuperSubState) : SuperState",superSubState.toString());
    }
    public void testName() throws Exception
    {
        assertEquals("SuperSubState",superSubState.getStateName());
    }
    public void testEquals() throws Exception
    {
        assertTrue(superSubState.equals(new SuperSubStateRep("SuperSubState","SuperState",null)));
    }
    public void testNotEquals() throws Exception
    {
        assertFalse(superSubState.equals(new SuperSubStateRep("supersubState","SuperState",null)));
        assertFalse(superSubState.equals(new SuperSubStateRep("SuperSubState","NotSameSuperState",null)));
    }
}
