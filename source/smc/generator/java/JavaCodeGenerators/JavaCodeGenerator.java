package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;
import smc.fsmrep.State;

public abstract class JavaCodeGenerator
{
    public abstract String generateCode(SMJavaGenerator gen);

     public static String printSeparator( int level )
    {
        if( level == 0 )
            return  "//----------------------------------------------\n";
        else
            return  "//--------------------------------------------\n";
    }
    public String createUpperCaseName( State s )
    {
        StringBuffer buf = new StringBuffer( s.getName() );
        if( buf.length() > 0 )
            buf.setCharAt(0, Character.toUpperCase( buf.charAt(0) ));
        return( buf.toString() );
    }
    public String createUpperCaseName( String event )
    {
        StringBuffer buf = new StringBuffer( event );
        if( buf.length() > 0 )
            buf.setCharAt(0, Character.toUpperCase( buf.charAt(0) ));
        return( buf.toString() );
    }
    public String createLowerCaseName( String event )
    {
        StringBuffer buf = new StringBuffer( event );
        if( buf.length() > 0 )
            buf.setCharAt(0, Character.toLowerCase( buf.charAt(0) ));
        return( buf.toString() );
    }
}
