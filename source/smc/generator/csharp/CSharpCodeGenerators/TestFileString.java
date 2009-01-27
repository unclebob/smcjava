package smc.generator.csharp.CSharpCodeGenerators;

import smc.Smc;

import java.io.*;

public class TestFileString
{
    private static TestFileString ourInstance = new TestFileString();
    private String fileContents;

    public static TestFileString getInstance()
    {
        return ourInstance;
    }

    private TestFileString()
    {
        try
        {
            writeFile();
            String[] args = new String[]{"", "-gsmc.generator.csharp.SMCSharpGenerator", "turn.sm"};
            Smc.main(args);
            fileContents = readFile();
            File turnSM = new File("turn.sm");
            File generatedFile = new File("TurnStyle.cs");
            turnSM.delete();
            generatedFile.delete();
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }

    }
    private static void writeFile()  throws Exception
    {
        File file = new File("turn.sm");
        FileWriter out = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(out);
        writer.write(buildSMFile());
        writer.close();
        out.close();
    }
    private static String buildSMFile()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("Context TurnStyleContext     // the name of the context class\n");
        buff.append("FSMName TurnStyle            // the name of the FSM to create\n");
        buff.append("Initial Locked               // the name of the initial state\n");
        buff.append("                             // for C# output\n");
        buff.append("pragma namespace TurnStyleExample\n");
        buff.append("{\n");
        buff.append("    Locked\n");
        buff.append("    {\n");
        buff.append("        Coin     Unlocked    Unlock\n");
        buff.append("        Pass     Locked      Alarm\n");
        buff.append("    }\n");
        buff.append("    Unlocked\n");
        buff.append("    {\n");
        buff.append("	    Coin    Unlocked    Thankyou\n");
        buff.append("	    Pass    Locked      Lock\n");
        buff.append("    }\n");
        buff.append("}\n");

        return buff.toString();
    }
    private static String readFile() throws Exception
    {
        StringBuffer buff = new StringBuffer();
        File file = new File("TurnStyle.cs");
        FileReader in = new FileReader(file);
        BufferedReader reader = new BufferedReader(in);
        String line = "";
        while((line = reader.readLine())!=null)
        {
            buff.append(line + "\n");
        }
        reader.close();
        in.close();
        return buff.toString();
    }
    public String getFileContents()
    {
        return fileContents;
    }
}
