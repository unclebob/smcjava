package smc.generator.csharp.CSharpCodeGenerators;

import junit.framework.TestCase;

public class FSMStateClassesTest extends TestCase
{
    public void testFromFile() throws Exception
    {
        String actual = TestFileString.getInstance().getFileContents();
        assertTrue(actual.indexOf("// class Locked\n") !=-1);
        assertTrue(actual.indexOf("public class Locked : State\n")!=-1);
        assertTrue(actual.indexOf("// class Unlocked\n")!=-1);
        assertTrue(actual.indexOf("public class Unlocked : State\n")!=-1);
        assertTrue(actual.indexOf("    { return \"Locked\"; }\n")!=-1);
        assertTrue(actual.indexOf("    { return \"Unlocked\"; }\n")!=-1);
    }



}
