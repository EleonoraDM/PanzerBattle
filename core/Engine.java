package core;

import io.*;

import java.io.IOException;
import java.util.NoSuchElementException;

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

            } catch (IOException e) {
                result = e.getMessage();
            }
            this.writer.println(result);
        }
    }
}