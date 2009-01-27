package smc.generator.java;

import smc.generator.FSMGenerator;
import smc.generator.java.JavaCodeGenerators.JavaCodeGenerator;
import smc.fsmrep.*;

import java.io.*;
import java.util.*;

public class SMJavaGenerator extends FSMGenerator
{
    private String itsOutputFileName, itsPackage;
    private final static String itsSuffix = new String(".java");
    private List itsImports;
    private HashSet itsOverriddenEvents;
    ConcreteState   itsSourceState;

    public SMJavaGenerator()
    {
        itsOverriddenEvents = new HashSet();
        itsPackage = "";
        itsImports = new ArrayList();
    }

    public void initialize() throws IOException
    {
        itsOutputFileName = createOutputFileName();
        initPackageAndImports();
        usesExceptions(getStateMap());
    }
    public void generate() throws Exception
    {
        writeToStream();
    }
    private void writeToStream()
    {
        try
        {
            writeFile();
        }
        catch( IOException e )
        {
            System.out.println( "Error: could not create output file" );
        }
    }

    public String generateStringForCode()
    {
        StringBuffer buff = new StringBuffer();
        try
        {
            List generators = JavaCodeGeneratorBuilder.javaCode.javaInstances();
            for(int i = 0 ; i !=generators.size();i++)
            {
                JavaCodeGenerator code = (JavaCodeGenerator)generators.get(i);
                buff.append(code.generateCode(this));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return buff.toString();
    }
    private void writeFile() throws FileNotFoundException
    {
        PrintWriter itsStream;
        FileOutputStream fout = new FileOutputStream( itsOutputFileName );
        itsStream = new PrintWriter( fout, true );
        itsStream.print(generateStringForCode());
        itsStream.close();
    }

    public String getPackage()
    {
        return itsPackage;
    }
    public List getImports()
    {
        return itsImports;
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
    private void initPackageAndImports()
    {
        String impString = "Import";
        int impLen = impString.length();
        String pkgString = "Package";
        int pkgLen = pkgString.length();

        Vector pragmas = getStateMap().getPragma();
        Iterator ip = pragmas.iterator();
        while( ip.hasNext() )
        {
            try
            {
                String p = (String)ip.next();
                if( p.regionMatches(true,0,impString,0,impLen) )
                {
                    String value = p.substring( impLen );
                    itsImports.add(value.trim() );
                }
                else if( p.regionMatches(true,0,pkgString,0,pkgLen) )
                {
                    String value = p.substring( pkgLen );
                    itsPackage = value.trim();
                }
            }
            catch( StringIndexOutOfBoundsException e )
            {}
        }
    }

    public boolean usesExceptions(StateMap map)
    {
        if( map.getExceptionName().length() > 0 )
            return  true;
        else
            return  false;
    }
    public String createOutputFileName()
    {
        return getDirectory() + getStateMap().getName() + itsSuffix;
    }

    public Vector getGeneratedFileNames()
    {
        Vector names = new Vector();
        names.addElement( createOutputFileName() );
        return names;
    }

}

