package smc.stripper;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;


public class StripperContext
{
    private int itsChar;
    private InputStream itsInStream;
    private OutputStream itsOutStream;

    public StripperContext()
    {}

    public void SetStreams(InputStream iStream, OutputStream oStream)
    {
      itsInStream = iStream;
      itsOutStream = oStream;
    }

    public int ReadChar()
    {
      int c = -1;
      try
      {
        c = itsInStream.read();
        itsChar = c;
      }
      catch (IOException ioException)
      {
        System.err.println("Read - IOExeception");
      }
      return c;
    }

    public void PutChar()
    {
      try
      {
        itsOutStream.write(itsChar);
      }
      catch (IOException exception)
      {
        System.err.println("PutChar - IOExeception");
      }
    }

    public void PutSlash()
    {
      try
      {
        itsOutStream.write('/');
      }
      catch (IOException exception)
      {
        System.err.println("PutSlash - IOExeception");
      }
    }

    public void OutsideIn()
    {
      System.err.println("OutsideIn");
    }

    public void OutsideOut()
    {
      System.err.println("OutsideOut");
    }

    public void StartingSlashIn()
    {
      System.err.println("StartingSlashIn");
    }

    public void StartingSlashOut()
    {
      System.err.println("StartingSlashOut");
    }

    public void SecondSlashIn()
    {
      System.err.println("SecondSlashIn");
    }

    public void SecondSlashOut()
    {
      System.err.println("SecondSlashOut");
    }

    public void InCommentIn()
    {
      System.err.println("InCommentIn");
    }

    public void InCommentOut()
    {
      System.err.println("InCommentOut");
    }

    public void StarAfterSlashIn()
    {
      System.err.println("StarAfterSlashIn");
    }

    public void StarAfterSlashOut()
    {
      System.err.println("StarAfterSlashOut");
    }

    public void StartingStarIn()
    {
      System.err.println("StartingStarIn");
    }

    public void StartingStarOut()
    {
      System.err.println("StartingStarOut");
    }

    public void FSMError(String t, String s)
    {
      System.err.println("Transition error: " + t + " in state " + s );
    }
   
}
