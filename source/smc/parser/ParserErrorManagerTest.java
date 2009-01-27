package smc.parser;

import junit.framework.TestCase;

public class ParserErrorManagerTest extends TestCase
{
    private ParserErrorManager manager;
    public void setUp()
    {
        manager = initManager();
    }
    public void testErrorManager() throws Exception
    {
        assertEquals(6,manager.getErrors().size());
    }
    public void testErrors() throws Exception
    {
        assertList(0);
        assertList(1);
        assertList(2);
        assertList(3);
        assertList(4);
        assertList(5);
    }

    private void assertList(int num)
    {
        String actual = (String)manager.getErrors().get(num);
        assertEquals("Error" + num,actual);
    }

    private ParserErrorManager initManager()
    {
        ParserErrorManager man = new ParserErrorManager(false);
        man.error("Error0");
        man.error("Error1");
        man.error("Error2");
        man.error("Error3");
        man.error("Error4");
        man.error("Error5");
        return man;
    }
}
