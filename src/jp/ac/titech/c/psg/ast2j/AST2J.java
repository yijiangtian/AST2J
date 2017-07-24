/* 
 * AST2J : A simple visitor generator for Java
 * Copyright (c) 2000-2017 Takuo Watanabe <takuo@acm.org>
 */

package jp.ac.titech.c.psg.ast2j;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The main class of AST2J.
 *
 * @author Takuo Watanabe
 * @version 0.5
 **/
public class AST2J {

    // Constants
    private static final String NAME = "AST2J";
    private static final String AUTHOR = "Takuo Watanabe";
    private static final String VERSION = "0.5a";

    private static void showUsage () {
        System.err.println("Usage: ast2j [ -options ] [ file ]");
        System.err.println("where options include:");
        System.err.println("  -t -target <dir>  specify target directory");
        System.err.println("  -d -debug         turn on debug mode");
        System.err.println("  -v -version       print system version");
        System.err.println("  -h -help          print this help message");
        System.exit(0);
    }

    private static void showVersion () {
        System.out.println(NAME + 
                           " by " + AUTHOR + 
                           " (version " + VERSION + 
                           ")");
        System.exit(0);
    }

    public static void main (String[] args) {
        boolean debugmode = false;
        int i = 0;
        while (i < args.length) {
            String arg = args[i];
            if (arg.equals("-h") || arg.equals("-help"))
                showUsage();
            else if (arg.equals("-v") || arg.equals("-version"))
                showVersion();
            else if (arg.equals("-d") || arg.equals("-debug"))
                debugmode = true;
            else if (arg.equals("-t") || arg.equals("-target")) {
                i++;
                if (i < args.length) {
                    System.err.println("This feature is not yet implemented. Sorry!");
                    System.exit(1);
                } else
                    showUsage();
            }
            else break;
            i++;
        }

        TransAST transAST = new TransAST();
        if (debugmode) 
            transAST.setDebug();
        else {
            Date date = GregorianCalendar.getInstance().getTime();
            transAST.setMessage("// This file was generated by " + NAME + 
                                " (" + VERSION + ")" +
                                " on " + date);
        }
        if (i >= args.length) {
            transAST.setSource("stdin");
            try {
                new Parser (new BufferedInputStream(System.in)).
                    parse(transAST);
                transAST.finishUp();
            } catch (Exception e) {
                System.err.println(e.toString());
                System.exit(1);
            }
        } else {
            transAST.setSource(args[i]);
            InputStream in = null;
            try {
                in = new FileInputStream(args[i]);
                new Parser (new BufferedInputStream(in)).parse(transAST);
                transAST.finishUp();
            } catch (Exception e) {
                System.err.println(e.toString());
                System.exit(1);
            }
            finally {
                if (in != null) 
                    try { in.close(); } catch (IOException e) {}
            }
        }
    }
}

