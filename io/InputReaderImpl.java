package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputReaderImpl implements InputReader {
    private BufferedReader reader;
    private CommandProcessor processor;

    public InputReaderImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.processor = new CommandProcessor();
    }

    @Override
    public String readLine() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        List<String> parameters = Arrays.stream(tokens).skip(1).collect(Collectors.toList());

        return this.processor.executeCommand(command, parameters);
    }
}
