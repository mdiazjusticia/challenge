package challenge;

import challenge.actions.Action;
import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

/**
 * Created by mario on 08/12/2016.
 */
public class Test {
    public static void main(String argv[]) {
        try {
            FileReader f = new FileReader("test/test.txt");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(f);


                String line = reader.readLine();

                while (line != null) {
                    //do whatever here
                    parser p = new parser(new Lexer(new StringReader(line)));
                    Symbol s = p.parse();
                    System.out.println(((Action)s.value).execute());
                    line = reader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
