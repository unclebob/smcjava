package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;
import smc.generator.csharp.SMCSharpGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class InitialCommentsTest extends TestCase
{
    private SMCSharpGenerator fsm;

    public void setUp() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCSharpCodeGeneratorUtils.initBuilderStateWithoutExceptions();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }
    public void testCommentsAtBeginningOfFileWithError()
    {
        CSharpCodeGenerator initial = new InitialComments();
        String actual = initial.generateCode(fsm);
        String expected = buildComment();
        assertEquals(expected,actual);
    }
    private String buildComment( )
    {
        StringBuffer buff = new StringBuffer();
        buff.append("//----------------------------------------------\n");
        buff.append("//\n");
        buff.append("// FSM:       TurnStyle\n");
        buff.append("// Context:   TurnStyleContext\n");
        buff.append("// Err Func:  FSMError\n");
        buff.append("// Version:   \n");
        buff.append("// Generated: " + generateDate());
        buff.append("//\n");
        buff.append("//----------------------------------------------\n");
        buff.append("\n\n");
        return buff.toString();
    }
    private String generateDate()
    {
        Date genDate = new Date();
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("EEEE MM/dd/yyyy 'at' kk:mm:ss zzz\n");
        dateFormat.setTimeZone( tz );
        return dateFormat.format( genDate );
    }


}
