package org.fmgroup.mediator.language;

import org.fmgroup.mediator.language.Program;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
//        String filename = "resources\\models\\testbench.med";
        String filename = "resources\\models\\test\\hybridsingle.med";
//        List<String> expath = new ArrayList<String>();
        Program prog = Program.parseFile(filename);
        System.out.println(prog.toString());
    }
}
