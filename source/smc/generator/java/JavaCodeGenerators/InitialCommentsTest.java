package smc.generator.java.JavaCodeGenerators;

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
    public void setUp()   throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestJavaCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        fsm = new SMCSharpGenerator();
        fsm.FSMInit(map,"fileName","directory");
        fsm.initialize();
    }
    public void testCommentsAtBeginningOfFileWithError()
    {
        String answer = ((SMCSharpGenerator)fsm).generateStringForCode();
        String expected = buildComment(true);
        assertTrue(answer.indexOf(expected)!= -1);
    }
    private String buildComment(boolean hasException)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("//----------------------------------------------" + "\n");
        buff.append("//\n");
        buff.append("// FSM:       FileName\n");
        buff.append("// Context:   ContextName\n" );
        if(hasException)
            buff.append("// Exception: Exception\n");
        else
            buff.append("// Err Func:  FSMError\n");
        buff.append("// Version:   TheVersion\n") ;
        buff.append("// Generated: " + generateDate() + "\n");
        buff.append("//\n");
        buff.append("//----------------------------------------------" + "\n\n\n");

        return buff.toString();
    }
    private String generateDate()
    {
        Date genDate = new Date();
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("EEEE MM/dd/yyyy 'at' kk:mm:ss zzz");
        dateFormat.setTimeZone( tz );
        return dateFormat.format( genDate );
    }


}
