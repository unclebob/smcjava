package smc;

import java.io.File;
import java.lang.*;
import smc.generator.FSMGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.parser.FSMParser;
import smc.fsmrep.StateMap;
import java.io.IOException;
import java.util.Vector;
import java.util.Iterator;

public class Smc
{
    private String itsInputFile;
    private String itsOutputDir;
    private String itsFSMGeneratorName;
    private boolean itsForceOverwrite;

    public static void main(String[] args)
    {
        Smc smc = new Smc();
        smc.execute(args);
    }
    private boolean checkForOverwrite( Vector files )
    {
        Vector existingFiles = new Vector();

        boolean overwrite = true;
        Iterator iname = files.iterator();
        while( iname.hasNext() )
        {
            try
            {
                String ofName = (String)(iname.next());
                File ofile = new File( ofName );
                if( ofile.canRead() )
                    existingFiles.add( ofName );
            }
            catch( Exception e )
            { System.out.print( e ); }
        }

        if( existingFiles.size() > 0 )
        {
            System.out.println("The following files will be overwritten" +
                    " if you choose to continue:");
            Iterator ei = existingFiles.iterator();
            while( ei.hasNext() )
            {
                String ofName = (String)ei.next();
                System.out.println( "   " + ofName );
            }
            System.out.print( "overwrite files and continue? (Y/N): " );
            System.out.flush();
            byte[] b = new byte[10];
            try
            {
                System.in.read(b);
                System.out.println();
                if( b[0] != 'Y' && b[0] != 'y' )
                {
                    overwrite = false;
                    System.out.println("please delete or rename " + "the file and try again.");
                }
            }
            catch( Exception ie)
            { System.out.println(ie); }

        }
        return overwrite;
    }

    public void parseCommandLine( String[] args  )
    {
        int ac = args.length;
        for( int i= 0; i < ac; i++ )
        {
            String s = args[i];

            if( s.startsWith("-") == true )
            {
                if( s.equalsIgnoreCase("-f") )
                {
                    itsForceOverwrite = true;
                }
                else if( s.regionMatches(true, 0,"-o", 0, 2) == true )
                {
                    int found = 0;
                    try
                    {
                        itsOutputDir = s.substring(2);
                        if( itsOutputDir.length() > 0 )
                            found = 1;
                    }
                    catch( StringIndexOutOfBoundsException e )
                    {}
                    if( found == 0 )
                    {
                        i++;
                        if( i < ac )
                            itsOutputDir = args[i];
                    }
                }
                else if( s.regionMatches(true, 0, "-g", 0, 2) == true )
                {
                    int found = 0;
                    try
                    {
                        itsFSMGeneratorName = s.substring(2);
                        if( itsFSMGeneratorName.length() > 0 )
                            found = 1;
                    }
                    catch( StringIndexOutOfBoundsException e )
                    {}
                    if( found == 0 )
                    {
                        i++;
                        if( i < ac )
                            itsFSMGeneratorName = args[i];
                    }
                }
                else
                    System.out.println("Unknown flag: " +  s );
            }
            else
                itsInputFile = s;
        }
    }

    private void printUsage()
    {
        System.out.println( "Usage: Smc [-g generator] [-f] [-o output_dir] file");
        System.out.println( "       -g is optional, " + "it overrides the code generator class" );
        System.out.println( "          specified in the State" + " machine definition");
        System.out.println( "       -f is optional, " + "it forces existing generated files to be overwritten");
        System.out.println( "       -o is optional, " + " the default output directory is the current directory" );
        System.out.println( "     file is required, " + "it should contain the State machine definition");
    }
    public void execute(String[] args)
    {
        itsOutputDir = new String("./");
        itsInputFile = new String("");
        itsFSMGeneratorName = new String("");
        itsForceOverwrite = false;

        parseCommandLine( args );

        if( itsInputFile.length() > 0 )
        {
            File iFile = new File( itsInputFile );
            if( iFile.canRead() )
            {
                hasInputFile();
            }
            else
            {
                System.out.println("Cannot read state machine definition " + "file: " + itsInputFile );
                System.out.println( "Aborting due to invalid state machine file." );
            }
        }
        else
        {
            printUsage();
        }
    }

    private void hasInputFile()
    {
        if( itsOutputDir.endsWith("/") == false && itsOutputDir.endsWith("\\") == false )
            itsOutputDir = itsOutputDir.concat("/");

        FSMRepresentationBuilder fsmbld = new FSMRepresentationBuilder();
        FSMParser parser = new FSMParser( fsmbld, itsInputFile );

        boolean status = parser.parse();

        if( status == true )
            generateCode(fsmbld, parser);
    }

    private void generateCode(FSMRepresentationBuilder fsmbld, FSMParser parser)
    {
        StateMap sm = fsmbld.getStateMap();

        if( itsFSMGeneratorName.length() == 0 )
        {
            itsFSMGeneratorName = parser.getFSMGeneratorName();
            if( itsFSMGeneratorName.length() == 0 )
            {
                System.out.println("Using default Java Generator.");
                itsFSMGeneratorName = "smc.generator.java.SMJavaGenerator";
            }
        }
        try
        {
            FSMGenerator generator = (FSMGenerator)Class.forName(itsFSMGeneratorName ).newInstance();
            generator.FSMInit( sm, itsInputFile, itsOutputDir );

            boolean overwrite = itsForceOverwrite;
            if( overwrite == false )
                overwrite = checkForOverwrite(generator.getGeneratedFileNames());

            if( overwrite == true )
            {
                try
                {
                    generator.initialize();
                    generator.generate();
                }
                catch( IOException e )
                { System.out.print( e );}
            }
        }
        catch( Exception cnf )
        {
            System.out.println( itsFSMGeneratorName + " is not a valid FSMGenerator");
            System.out.println("Aborting due to invalid generator.");
            cnf.printStackTrace();
        }
    }

    public String getInputFilename()
    {
        return itsInputFile;
    }
    public String getOutputDir()
    {
        return itsOutputDir;
    }
    public String getFSMGeneratorName()
    {
        return itsFSMGeneratorName;
    }
    public boolean getForcedOverwrite()
    {
        return itsForceOverwrite;
    }
}
