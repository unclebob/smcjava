package smc.generator.csharp;

import smc.generator.FSMGenerator;
import smc.generator.csharp.CSharpCodeGenerators.*;
import smc.fsmrep.*;
import java.io.*;
import java.util.*;

public class SMCSharpGenerator  extends FSMGenerator
{
    private boolean hasNamespace,hasUsing;
    private String itsNamespace;
    private List itsUsing;
    private ConcreteState   itsSourceState;
    private HashSet itsOverriddenEvents;

    public SMCSharpGenerator()
    {
        itsOverriddenEvents = new HashSet();
        hasNamespace = hasUsing = false;
        itsUsing = new Vector();
    }
    public void initialize() throws IOException
    {
        initNamespaceAndUsingStatementes();
    }

    public boolean usesExceptions(StateMap map)
    {
        if( map.getExceptionName().length() > 0 )
            return  true;
        return  false;
    }
    public void generate()  throws Exception
    {
        writeToStream();
    }

    private void writeToStream() throws IOException
    {
        PrintWriter itsStream;
        try
        {
            FileOutputStream fout = new FileOutputStream(createOutputFileName() );
            itsStream = new PrintWriter( fout, true );
        }
        catch( IOException e )
        {
            System.out.println( "Error: could not create output file" );
            throw( e );
        }
        itsStream.print(generateStringForCode());
        itsStream.close();
    }

    public String generateStringForCode()
    {
        StringBuffer buff = new StringBuffer();
        try
        {
            List generators = CSharpCodeGeneratorBuilder.cSharpCode.cSharpInstances();
            for(int i = 0 ; i !=generators.size();i++)
            {
                CSharpCodeGenerator code = (CSharpCodeGenerator)generators.get(i);
                buff.append(code.generateCode(this));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        buff.append("\n}\n");
        return buff.toString();
    }
    private void initNamespaceAndUsingStatementes()
    {
        String usingString = "Using";
        int useLen = usingString.length();
        String nspString = "Namespace";
        int nspLen = nspString.length();

        Vector pragmas = getStateMap().getPragma();
        Iterator ip = pragmas.iterator();
        while( ip.hasNext() )
        {
            try
            {
                String p = (String)ip.next();
                if( p.regionMatches(true,0,usingString,0,useLen) )
                {
                    String value = p.substring( useLen );
                    itsUsing.add( value.trim() );
                    hasUsing=true;
                }
                else if( p.regionMatches(true,0,nspString,0,nspLen) )
                {
                    String value = p.substring( nspLen );
                    itsNamespace = value.trim();
                    hasNamespace = true;
                }
            }
            catch( StringIndexOutOfBoundsException e )
            {}
        }
    }
    public boolean hasNamespace()
    {
        return hasNamespace;
    }
    public boolean hasUsing()
    {
        return hasUsing;
    }
    private String createOutputFileName()
    {
        return getDirectory() + getStateMap().getName() + ".cs";
    }
    public Vector getGeneratedFileNames()
    {
        Vector names = new Vector();
        names.addElement( createOutputFileName() );
        return names;
    }
    public  ConcreteState getItsSourceState()
    {
        return itsSourceState;
    }
    public void clearItsOverRiddenEvents()
    {
        itsOverriddenEvents.clear();
    }
    public void setItsSourceState(ConcreteState value)
    {
        itsSourceState = value;
    }
    public HashSet getItsOverriddenEvents()
    {
        return itsOverriddenEvents;
    }
    public String getNamespace()
    {
        return itsNamespace;
    }
    public List getItsUsing()
    {
        return itsUsing;
    }
}
