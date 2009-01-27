package smc.generator.java.JavaCodeGenerators;

import smc.fsmrep.StateMap;
import smc.generator.java.SMJavaGenerator;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class InitialComments extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        StateMap map = gen.getStateMap();
        boolean useExceptions = gen.usesExceptions(map);
        StringBuffer buff = new StringBuffer();
        buff.append(printSeparator(0));
        buff.append("//\n");
        buff.append("// FSM:       " + map.getName() + "\n" );
        buff.append("// Context:   " + map.getContextName() + "\n");

        if( useExceptions )
            buff.append("// Exception: " + map.getExceptionName()  + "\n");
        else
            buff.append("// Err Func:  " + map.getErrorFunctionName()  + "\n");
        
        buff.append("// Version:   " + map.getVersion() + "\n" );
        buff.append("// Generated: " + generateDate() + "\n");
        buff.append("//\n");
        buff.append(printSeparator(0) + "\n\n");

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
