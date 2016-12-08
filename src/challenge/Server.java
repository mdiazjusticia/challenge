package challenge;

import challenge.actions.Action;
import java_cup.runtime.Symbol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mario on 08/12/2016.
 */
public class Server implements Runnable
    {
        private final Socket mSocket;
        private final int mNum;

        Server( Socket socket, int num )
        {
            mSocket = socket;
            mNum = num;

            Thread handler = new Thread( this, "handler-" + mNum );
            handler.start();
        }

    public void run()
    {
        try
        {
            try
            {
                System.out.println( mNum + " Connected." );
                BufferedReader in = new BufferedReader( new InputStreamReader( mSocket.getInputStream() ) );
                //StringReader in = new StringReader( new InputStreamReader( mSocket.getInputStream() ) );
                OutputStreamWriter out = new OutputStreamWriter( mSocket.getOutputStream() );
                out.write( "Welcome connection #" + mNum + "\n\r" );
                out.flush();

                while ( true )
                {
                    String line = in.readLine().trim();
                    if ( line == null )
                    {
                        System.out.println( mNum + " Closed." );
                        return;
                    }
                    else
                    {
                        parser p = new parser(new Lexer(new StringReader(line)));

                        Symbol s = p.parse();

                        out.write( ((Action)s.value).execute() + "\n\r" );
                        out.flush();
                    }
                }
            } catch (Exception e) {
                System.out.println("Parsing error.");
                System.out.println(e);
            }
            finally
            {
                mSocket.close();
            }
        }
        catch ( IOException e )
        {
            System.out.println( mNum + " Error: " + e.toString() );
        }
    }


    public static void main( String[] args )
            throws Exception
    {
        int port = 5555;

        System.out.println( "Accepting connections on port: " + port );
        int nextNum = 1;
        ServerSocket serverSocket = new ServerSocket( port );
        while ( true )
        {
            Socket socket = serverSocket.accept();
            Server hw = new Server( socket, nextNum++ );
        }
    }
}
