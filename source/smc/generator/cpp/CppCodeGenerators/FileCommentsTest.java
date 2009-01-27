package smc.generator.cpp.CppCodeGenerators;

import junit.framework.TestCase;
import smc.generator.cpp.SMCppGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.StateMap;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class FileCommentsTest extends TestCase
{
    public void testFileComments() throws Exception
    {
        FSMRepresentationBuilder fsmbld = TestCppCodeGeneratorUtils.initBuilderState();
        StateMap map = fsmbld.getStateMap() ;
        SMCppGenerator fsm = new SMCppGenerator();
        fsm.FSMInit(map,"TurnStyle","directory");
        fsm.initialize();
        FileComments comment = new FileComments();

        assertEquals(buildFileComments(),comment.generateCode(fsm));
    }
    public String buildFileComments()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        buff.append( "// TurnStyle.h\n");
        buff.append( "// FSM:       TurnStyle\n");
        buff.append( "// Context:   TurnStyleContext\n");
        buff.append( "// Version:   \n");
        buff.append( "// Generated: " + getDate() + "\n");
        buff.append( "//\n\n\n");
        return buff.toString();
    }
    private String getDate()
    {
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("EEEE MM/dd/yyyy 'at' kk:mm:ss zzz");
        dateFormat.setTimeZone( tz );
        return dateFormat.format( new Date());
    }
}
