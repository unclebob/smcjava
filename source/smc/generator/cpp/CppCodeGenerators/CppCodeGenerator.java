package smc.generator.cpp.CppCodeGenerators;

import smc.fsmrep.State;
import smc.fsmrep.StateMap;
import smc.generator.cpp.SMCppGenerator;

import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class CppCodeGenerator
{

    public final static String itsHdrSuffix = new String(".h");
    public final static String itsImplSuffix = new String (".cpp");


    public abstract String generateCode(SMCppGenerator gen);

    public String printSeparator(String str )
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "//----------------------------------------------\n");
        if( str.length() > 0 )
        {
            buff.append( "// " + str + "\n");
            buff.append( "//----------------------------------------------\n");
        }
        return buff.toString();
    }

    public String createMethodName( State s )
    {
        StringBuffer buff = new StringBuffer( s.getName() );
        if( buff.length() > 0 )
            buff.setCharAt(0, Character.toUpperCase( buff.charAt(0) ));
        return( buff.toString() );
    }
    public String createMethodName( String event )
    {
        StringBuffer buff = new StringBuffer( event );
        if( buff.length() > 0 )
            buff.setCharAt(0, Character.toUpperCase( buff.charAt(0) ));
        return( buff.toString() );
    }
    
    public String generateFileComment(String theFileName, StateMap sm )
    {
        StringBuffer buff = new StringBuffer();

        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("EEEE MM/dd/yyyy 'at' kk:mm:ss zzz");
        dateFormat.setTimeZone( tz );
        String dateString = dateFormat.format( new Date() );

        buff.append(printSeparator(""));
        buff.append("// " + theFileName + "\n");
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
