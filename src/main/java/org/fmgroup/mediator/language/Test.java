package org.fmgroup.mediator.language;

import org.fmgroup.mediator.language.Program;
import org.fmgroup.mediator.plugins.scheduler.Scheduler;
import org.fmgroup.mediator.language.entity.automaton.Automaton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, ValidationException {
//        String filename = "resources/models/testbench.med";
//        String filename = "resources\\models\\test\\hybridsingle.med";
        String filename = "resources/models/test/watertank2.med";
//        List<String> expath = new ArrayList<String>();
        Program prog = Program.parseFile(filename);

        Automaton sysAutomaton = Scheduler.Schedule(prog.getSystems().get("twotanks"));
        System.out.println(sysAutomaton);
    }
}
