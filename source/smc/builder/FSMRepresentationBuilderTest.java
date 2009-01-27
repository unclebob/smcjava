package smc.builder;

import junit.framework.TestCase;
import smc.parser.ParserSyntaxLocation;
import smc.parser.ParserErrorManager;
import smc.fsmrep.*;

public class FSMRepresentationBuilderTest extends TestCase
{
    private FSMRepresentationBuilder fsm ;

    public void setUp()
    {
        fsm = new FSMRepresentationBuilder();
    }
    public void testStateName()
    {
        fsm.addState("StateName",new ParserSyntaxLocation("",0));
        fsm.build();
        assertEquals("StateName",fsm.getBuiltState("StateName").getName());
    }
    public void testPragmas()
    {
        fsm.addPragma("pragma prag0");
        fsm.addPragma("pragma prag1");
        fsm.addPragma("pragma prag2");
        fsm.addPragma("pragma prag3");
        fsm.build();
        assertEquals("pragma prag0",fsm.getStateMap().getPragma().get(0));
        assertEquals("pragma prag1",fsm.getStateMap().getPragma().get(1));
        assertEquals("pragma prag2",fsm.getStateMap().getPragma().get(2));
        assertEquals("pragma prag3",fsm.getStateMap().getPragma().get(3));
    }
    public void testBuildStates() throws Exception
    {
        initBuildStates();
        fsm.build();
        assertEquals(4,fsm.getStateMap().getOrderedStates().size());
    }
    public void testRetreiveStates() throws Exception
    {
        initBuildStates();
        fsm.build();
        String state4Name = ((ConcreteState)fsm.getStateMap().getOrderedStates().get(0)).getName();
        String state1Name = ((ConcreteState)fsm.getStateMap().getOrderedStates().get(3)).getName();
        assertEquals("State4",state4Name);
        assertEquals("State1",state1Name);
    }
    public void testBuildTransition() throws Exception
    {
        initBuildStates();
        fsm.addTransition("Event","State4",null);
        fsm.build();
        assertEquals(1,((ConcreteState)fsm.getStateMap().getOrderedStates().get(0)).getTransitions().size());
    }
    public void testBuildManyTransition() throws Exception
    {
        initBuildStates();
        fsm.addTransition("Event","State4",null);
        fsm.addTransition("Event2","State4",null);
        fsm.addTransition("Event3","State4",null);
        fsm.addTransition("Event4","State4",null);
        fsm.build();
        assertEquals(4,((ConcreteState)fsm.getStateMap().getOrderedStates().get(0)).getTransitions().size());
    }
    public void testBuildConcreteStates() throws Exception
    {
        fsm.addBuiltConcreteState(new ConcreteStateImpl("ConcreteState1"));
        fsm.build();
        assertNotNull(fsm.getBuiltConcreteState("ConcreteState1"));
        assertNull(fsm.getBuiltConcreteState("ConcreteStat0"));
    }
    public void testBuildSuperStates() throws Exception
    {
        fsm.addBuiltSuperState(new SuperStateImpl("SuperState"));
        fsm.build();
        assertNotNull(fsm.getBuiltSuperState("SuperState"));
        assertNull(fsm.getBuiltSuperState("SuperState12"));
    }
    public void testBuildEntryAction() throws Exception
    {
        fsm.addState("State",null);
        fsm.addEntryAction("EntryAction");
        fsm.build();
        assertEquals(1,fsm.getBuiltState("State").getEntryActions().size());
        assertEquals("EntryAction",fsm.getBuiltState("State").getEntryActions().get(0));
    }
    public void testBuildManyEntryActions() throws Exception
    {
        fsm.addState("State",null);
        fsm.addEntryAction("EntryAction");
        fsm.addEntryAction("EntryAction2");
        fsm.addEntryAction("EntryAction3");
        fsm.addEntryAction("EntryAction4");
        fsm.build();
        assertEquals(4,fsm.getBuiltState("State").getEntryActions().size());
    }
    public void testBuildExitAction() throws Exception
    {
        fsm.addState("State",null);
        fsm.addExitAction("ExitAction");
        fsm.build();
        assertEquals(1,fsm.getBuiltState("State").getExitActions().size());
        assertEquals("ExitAction",fsm.getBuiltState("State").getExitActions().get(0));
    }
    public void testBuildManyExitActions() throws Exception
    {
        fsm.addState("State",null);
        fsm.addExitAction("ExitAction");
        fsm.addExitAction("ExitAction2");
        fsm.addExitAction("ExitAction3");
        fsm.addExitAction("ExitAction4");
        fsm.build();
        assertEquals(4,fsm.getBuiltState("State").getExitActions().size());
    }
    public void testSuperAndSupStates() throws Exception
    {
        fsm.addSuperState("SuperState",null);
        fsm.addSubState("SubState","SuperState",null);
        fsm.build();
        assertEquals("SuperState",((State)fsm.getStateMap().getOrderedStates().get(0)).getName());
        assertEquals("SubState",((State)fsm.getStateMap().getOrderedStates().get(1)).getName());
    }
    public void testContextName() throws Exception
    {
        fsm.setContextName("ContextName");
        fsm.build();
        assertEquals("ContextName",fsm.getStateMap().getContextName());
    }
    public void testErrors() throws Exception
    {
        fsm.setError();
        fsm.build();
        assertEquals("FSMError",fsm.getStateMap().getErrorFunctionName());
    }
    public void testExceptions() throws Exception
    {
        fsm.setException("Exception");
        fsm.build();
        assertEquals("Exception",fsm.getStateMap().getExceptionName());
    }
    public void testInitialState() throws Exception
    {
        fsm.addState("InitialState",null);
        fsm.setInitialState("InitialState");
        fsm.build();
        assertEquals("InitialState",fsm.getStateMap().getInitialState().getName());
    }
    public void testName() throws Exception
    {
        fsm.setName("Name");
        fsm.build();
        assertEquals("Name",fsm.getStateMap().getName());
    }
    public void testVersion() throws Exception
    {
        fsm.setVersion("Version");
        fsm.build();
        assertEquals("Version",fsm.getStateMap().getVersion());
    }
    public void testSetTransitionsError() throws Exception
    {
        FSMBuilderErrorManager errorManager = new ParserErrorManager(false);
        fsm.setErrorManager(errorManager);
        fsm.build();
        assertEquals(2,((ParserErrorManager)errorManager).getErrors().size());
    }
    public void testSetTransitionsErrorTypes() throws Exception
    {
        FSMBuilderErrorManager errorManager = new ParserErrorManager(false);
        fsm.setErrorManager(errorManager);
        fsm.build();
        String actual = (String)((ParserErrorManager)errorManager).getErrors().get(0);
        String actual1 = (String)((ParserErrorManager)errorManager).getErrors().get(1);
        assertEquals("Initial state (null) is not concrete.",actual);
        assertEquals("Aborting due to inconsistent input.",actual1);
    }
     public void testSetDuplicantEventsTypes() throws Exception
    {
        FSMBuilderErrorManager errorManager = new ParserErrorManager(false);
        fsm.setErrorManager(errorManager);
        fsm.addBuiltConcreteState(new ConcreteStateImpl("State1"));
        fsm.addBuiltConcreteState(new ConcreteStateImpl("State2"));
        fsm.setError();
        fsm.setInitialState("State1");
        fsm.addTransition("Event","State2",null);
        fsm.addTransition("Event","State2",null);
        fsm.addTransition("Event","State2",null);
        fsm.addTransition("Event","State2",null);
        assertFalse(fsm.build());
        assertEquals(1,((ParserErrorManager)errorManager).getErrors().size());
    }
    private void initBuildStates()
    {
        fsm.addState("State1",null);
        fsm.addState("State2",null);
        fsm.addState("State3",null);
        fsm.addState("State4",null);
    }
}
