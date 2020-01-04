package core;

import io.*;
import io.interfaces.InputReader;
import io.interfaces.OutputWriter;

import java.io.IOException;

public class Engine {
    private InputReader reader;
    private OutputWriter writer;

    public Engine() {
        this.reader = new InputReaderImpl();
        this.writer = new OutputWriterImpl();
    }

    public void run() {
        String result = null;

        while (true) {
            try {
                result = this.reader.readLine();

                if (result.equals(Commands.TERMINATE.name())) {
                    break;
                }

            } catch (IOException | IllegalArgumentException e) {
                result = e.getMessage();
            }
            this.writer.println(result);
        }
    }
}
