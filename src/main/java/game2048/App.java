package game2048;

import game2048.andreas.CrazyOlderBrother;
import game2048.andreas.CrazySpiner;
import game2048.andreas.NoUpAlgorithm;
import game2048.andreas.RandomAlgorithm;
import game2048.andreas.Spinbot;
import game2048.andreas.TheWise;
import game2048.andreas.UpperMan;
import game2048.leo.DumbAlgo;
import game2048.leo.SmarterAlgo;

public class App {
    public static void main(String[] args) {
        Arena.compete(new RandomAlgorithm(), new NoUpAlgorithm(), new Spinbot(), new CrazySpiner(),new UpperMan(), new CrazyOlderBrother(),new DumbAlgo(), new TheWise(), new SmarterAlgo());
    }

}