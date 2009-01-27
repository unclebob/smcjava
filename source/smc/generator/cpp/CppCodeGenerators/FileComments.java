package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;
import smc.fsmrep.StateMap;

import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FileComments  extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        StateMap sm = gen.getStateMap();

        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("EEEE MM/dd/yyyy 'at' kk:mm:ss zzz");
        dateFormat.setTimeZone( tz );
        String dateString = dateFormat.format( new Date());

        buff.append(printSeparator(""));
        buff.append("// " + gen.getFilePrefix() + itsHdrSuffix + "\n");
        buff.append("// FSM:       " + sm.getName() + "\n");
        buff.append("// Context:   " + sm.getContextName() + "\n");
        buff.append("// Version:   " + sm.getVersion() + "\n");
        buff.append("// Generated: " + dateString + "\n");
        buff.append("//" + "\n");
        printSeparator("");
        buff.append("\n\n");
        return buff.toString();
    }
}
