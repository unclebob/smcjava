package smc.generator.cpp;

import smc.generator.FSMGenerator;
import smc.generator.cpp.CppCodeGenerators.*;
import smc.fsmrep.*;
import java.util.*;
import java.io.*;
import java.util.*;

public class SMCppGenerator extends FSMGenerator
{
    private String itsHeaderFileName;
    private String itsImplFileName;
    public final static String itsHdrSuffix = new String(".h");
    public final static String itsImplSuffix = new String (".cpp");
    private String itsNamespace;
    private List itsHeaders;

    private HashSet itsOverriddenEvents;
    ConcreteState   itsSourceState;
 
    public SMCppGenerator()
    {
        itsOverriddenEvents = new HashSet();
        itsNamespace = "";
        itsHeaders = new ArrayList();
    }
    public void initialize() throws IOException
    {
        itsHeaderFileName = createHdrFileName();
        itsImplFileName = createImplFileName();
 
        String hdrString = "Header";
        int hdrLen = hdrString.length();
        String nspcString = "Namespace";
        int nspcLen = nspcString.length();

        Vector pragmas = getStateMap().getPragma();
        Iterator ip = pragmas.iterator();
        while( ip.hasNext() )
        {
            try
            {
                String p = (String)ip.next();
                if( p.regionMatches(true,0,hdrString,0,hdrLen) )
                {
                    String value = p.substring( hdrLen );
                    itsHeaders.add( value.trim() );
                }
                else if( p.regionMatches(true,0,nspcString,0,nspcLen) )
                {
                    String value = p.substring( nspcLen );
                    itsNamespace = value.trim();
                }
            }
            catch( StringIndexOutOfBoundsException e )
            {}
        }
    }
    private String createHdrFileName()
    {
        String name = getDirectory() + getFilePrefix() + itsHdrSuffix;
        return name;
    }
    private String createImplFileName()
    {
        String name = getDirectory() + getFilePrefix() + itsImplSuffix;
        return name;
    }
    public Vector getGeneratedFileNames()
    {
        Vector names = new Vector();
        names.addElement( createHdrFileName() );
        names.addElement( createImplFileName() );
        return names;
    }
    public void generate() throws IOException
    {
        PrintWriter itsHdrStream;
        PrintWriter itsImplStream;

        try
        {
            FileOutputStream fout =  new FileOutputStream( itsHeaderFileName );
            itsHdrStream = new PrintWriter( fout, true );
            FileOutputStream fout2 = new FileOutputStream( itsImplFileName );
            itsImplStream = new PrintWriter( fout2, true );

            CppCodeGenerator header = new Header();
            CppCodeGenerator impl = new Implementation();

            itsHdrStream.print(header.generateCode(this));
            itsImplStream.print(impl.generateCode(this));

            itsHdrStream.close();
            itsImplStream.close();
        }
        catch( IOException e )
        {
            System.out.println( "Error: could not create output files" );
            throw( e );
        }
    }
    public String makeStateName( String s )
    {
        String buf = new String( getStateMap().getName() );
        buf = buf + s + "State";

        return buf;
    }
    public String getNamespace()
    {
        return itsNamespace;
    }
    public List getHeaders()
    {
        return itsHeaders;
    }
    public void clearOverRiddenEvents()
    {
        itsOverriddenEvents.clear();
    }
    public HashSet getOverriddenEvents()
    {
        return itsOverriddenEvents;
    }
    public void addOverriddenEvent(String event)
    {
        itsOverriddenEvents.add(event);
    }
    public ConcreteState getSourceState()
    {
        return itsSourceState;
    }
    public void setSourceState(ConcreteState value)
    {
        itsSourceState = value;
    }
}
